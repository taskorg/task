package com.abc.task.rulefilter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.abc.task.exception.ParameterException;

@Component("ip")
public class IpFilter implements RuleFilter {
	private static final Log log = LogFactory.getLog(IpFilter.class);

	private static Map<String, Map<String, LinkedList<Long>>> key_ip_time = new HashMap<String, Map<String, LinkedList<Long>>>();
	private static Map<String, Map<String, long[]>> key_ip_punishing = new HashMap<String, Map<String, long[]>>();

	static {
		Thread t = new Thread(new Checker(), "IPController-Checker");
		t.setDaemon(true);
		t.start();
	}

	@Override
	/**
	 * 指定间隔(秒)内的最大访问次数
	 * 
	 * @param params(可以包含 
	 * memberId:会员id.必须,
	 * 	itemId:任务id.必须,
	 * ip:参与任务时的会员ip.必须,
	 * key:.不必须,
	 * seconds=5.不必须,
	 * count:最大访问次数=10.不必须,
	 * punish_time:惩罚时间,0 则无惩罚等待=5.不必须)
	 * @return
	 */
	public State check(int memberId, int itemId, Map<String, String> params,
			String message) throws ParameterException {
		String ip = params.get("ip") == null ? "" : (String) params.get("ip");
		String key = StringUtils.defaultString((String) params.get("key"));
		int seconds = NumberUtils.toInt(params.get("seconds") == null ? ""
				: (String) params.get("seconds"), 5);
		int count = NumberUtils.toInt(params.get("count"), 10);
		int punish_time = NumberUtils.toInt(params.get("punish_time"), 0);
		if (memberId <= 0 || itemId <= 0 || ip == "") {
			throw new ParameterException("必须包含参数:memberId,itemId,ip");
		}
		State state = new State();
		int punish_time_remain = 0;
		// IP：惩罚开始时间点
		Map<String, long[]> ip_pt = key_ip_punishing.get(key);
		if (ip_pt != null) {
			long[] pt = ip_pt.get(ip);
			if (pt != null) {
				punish_time_remain = (int) (pt[0] + pt[1] - System
						.currentTimeMillis() / 1000);
				if (punish_time_remain <= 0) {
					ip_pt.remove(ip);
				} else {
					state.setMessage(message.replace("{0}", String.valueOf(punish_time_remain)));
					state.setAllow(false);
					return state;
				}
			}
		}

		Map<String, LinkedList<Long>> ip_time = key_ip_time.get(key);
		if (ip_time == null) {
			synchronized (key_ip_time) {
				if ((ip_time = key_ip_time.get(key)) == null) {
					key_ip_time.put(key,
							ip_time = new HashMap<String, LinkedList<Long>>());
				}
			}
		}

		LinkedList<Long> list = ip_time.get(ip);
		if (list == null) {
			synchronized (ip_time) {
				if ((list = ip_time.get(ip)) == null) {
					ip_time.put(ip, list = new LinkedList<Long>());
				}
			}
		}

		synchronized (list) {
			long now = System.currentTimeMillis() / 1000;
			list.offer(now);
			if (list.size() < count) {
				state.setAllow(true);
				return state;
			}
			for (int i = 0, j = list.size() - count; i < j; ++i) {
				list.poll();
			}

			long time = list.peek();
			if (now - time <= seconds) {
				if (punish_time > 0) {
					if ((ip_pt = key_ip_punishing.get(key)) == null) {
						synchronized (key_ip_punishing) {
							if ((ip_pt = key_ip_punishing.get(key)) == null) {
								key_ip_punishing.put(key,
										ip_pt = new HashMap<String, long[]>());
							}
						}
					}
					ip_pt.put(ip, new long[] { now, punish_time });
					punish_time_remain = punish_time;
				}
				return state;
			}

		}

		state.setAllow(true);
		return state;
	}

	private static class Checker implements Runnable {
		public void run() {
			while (true) {
				try {
					long now = System.currentTimeMillis() / 1000;
					for (Map<String, long[]> ip_pun : key_ip_punishing.values()) {
						Iterator<Entry<String, long[]>> i = ip_pun.entrySet()
								.iterator();
						while (i.hasNext()) {
							Entry<String, long[]> kv = i.next();
							if (now - kv.getValue()[0] > kv.getValue()[1]) {
								i.remove();
							}
						}
					}
				} catch (Exception e) {
					log.error(e, e);
				}
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					log.error(e, e);
				}
			}
		}
	}

}
