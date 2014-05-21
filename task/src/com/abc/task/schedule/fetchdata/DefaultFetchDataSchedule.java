package com.abc.task.schedule.fetchdata;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import com.abc.task.lang.Function;
import com.abc.task.lang.HttpClientHelper;

public class DefaultFetchDataSchedule {
	private static final Log log = LogFactory
			.getLog(DefaultFetchDataSchedule.class);
	@Resource
	JdbcTemplate jdbcTemplate;
	@Resource
	BlockingQueue<Map<String, Object>> fetchDataQueue;

	@Scheduled(fixedDelay = 180000)
	public void queryMembers() {
		if (fetchDataQueue.size() > 0) {
			log.info("fetchDataQueue still has task:" + fetchDataQueue.size()
					+ ",pass");
			return;
		}

		log.info("loading single_fetch_data...");
		jdbcTemplate.setQueryTimeout(3);
		List<Map<String, Object>> tasks = jdbcTemplate
				.queryForList("select task_id,single_fetch_data_url,fetch_interval from task_item where single_fetch_data_url is not null");
		if (tasks.size() == 0) {
			return;
		}
		for (Map<String, Object> task : tasks) {
			log.info("loading members,task:" + task.get("task_id"));
			List<Map<String, Object>> members = jdbcTemplate
					.queryForList(
							"select member_id,task_member_id,task_member_name from task_member_bind where task_id = ? and current_timestamp()-last_data_time >=?",
							new Object[] {
									task.get("task_id"),
									(Integer) task.get("fetch_data_interval") * 60 });

			log.info("insert fetch_data_url into queue...");
			for (Map<String, Object> member : members) {
				String fetch_data_url = Function.template(
						(String) task.get("single_fetch_data_url"), member);
				member.put("fetch_data_url", fetch_data_url);
				try {
					fetchDataQueue.put(member);
				} catch (InterruptedException e) {
					log.error("put fetch_data_url failed", e);
				}
			}
		}
		log.info("loading single_fetch_data task over.");
	}

	@Scheduled(fixedDelay = 180000)
	public void fetchData() {
		ExecutorService service = Executors.newScheduledThreadPool(10);
		while (true) {
			try {
				final Map<String, Object> data = fetchDataQueue.take();
				service.submit(new Runnable() {
					@Override
					public void run() {
						HttpClient client = HttpClientHelper.getClient();
						try {
							String fetch_data_url = (String) data
									.get("fetch_data_url");
							HttpGet target = new HttpGet(fetch_data_url);
							HttpResponse response = client.execute(target);
							StatusLine status = response.getStatusLine();
							if (status.getStatusCode() == 200) {
								HttpEntity entity = response.getEntity();
								String data_json = EntityUtils.toString(entity,
										"utf-8");
								data.put("data_json", data_json);
								jdbcTemplate
										.update("insert into task_member_data_tmp(task_id,member_id,task_member_id,task_member_name,data_json) values (?,?,?,?,?) ");
							} else {
								log.error("fetch data error:" + fetch_data_url
										+ ",httpcode:" + status.getStatusCode());
							}
						} catch (Exception e) {
							log.error(e, e);
						}
					}
				});
			} catch (Exception e) {
				log.error(e, e);
				continue;
			}
		}
	}
}
