package com.abc.task.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.abc.task.exception.ParameterException;
import com.abc.task.vo.TaskFilter;
import com.abc.task.vo.TaskFilterOption;
import com.abc.task.vo.TaskItem;
import com.abc.task.vo.TaskRule;

@Service("taskService")
public class TaskService {
	@Resource
	private JdbcTemplate jdbcTemplateTask;
	@Resource
	ObjectMapper objectMapper;

	public TaskItem getTaskItem(int itemId) {
		TaskItem t = jdbcTemplateTask.queryForObject(
				"select * from task_item where  id = ?",
				BeanPropertyRowMapper.newInstance(TaskItem.class), itemId);
		return t;
	}

	public Map<String, Object> getTaskType(int typeId) {
		Map<String, Object> t = jdbcTemplateTask.queryForMap(
				"select * from task_type where  id = ?", typeId);
		return t;
	}

	public TaskFilter getTaskFilter(int filterId) {
		TaskFilter t = jdbcTemplateTask.queryForObject(
				"select * from task_filter where  id = ?",
				BeanPropertyRowMapper.newInstance(TaskFilter.class), filterId);
		return t;
	}

	public TaskRule getTaskRule(int ruleId) {
		TaskRule t = jdbcTemplateTask.queryForObject(
				"select * from task_rule where  id = ?",
				BeanPropertyRowMapper.newInstance(TaskRule.class), ruleId);
		return t;
	}

	public TaskFilterOption getTaskFilterOption(int optionId) {
		TaskFilterOption t = jdbcTemplateTask.queryForObject(
				"select * from task_filter_option where  id = ?",
				BeanPropertyRowMapper.newInstance(TaskFilterOption.class),
				optionId);
		return t;
	}

	public List<TaskFilterOption> getTaskFilterOptions(int itemId) {
		List<Integer> t = jdbcTemplateTask.queryForList(
				"select id from task_filter_option where  item_id = ?",
				Integer.class, itemId);
		List<TaskFilterOption> r = new ArrayList<TaskFilterOption>(t.size());
		for (Integer i : t) {
			r.add(getTaskFilterOption(i));
		}
		return r;
	}

	public List<TaskRule> getTaskRules(int itemId) {
		List<Integer> t = jdbcTemplateTask.queryForList(
				"select id from task_rule where  item_id = ?",
				Integer.class, itemId);
		List<TaskRule> r = new ArrayList<TaskRule>(t.size());
		for (Integer i : t) {
			r.add(getTaskRule(i));
		}
		return r;
	}

	public List<TaskItem> taskItemList(int rowCount, int page, int typeId,
			String order) throws ParameterException {

		StringBuilder sql = new StringBuilder(" select id from task_item ");
		if (rowCount <= 0 || page <= 0) {
			throw new ParameterException("rowcount or page must be big than 0!");
		}
		if (typeId > 0) {
			sql.append(" where type_id = ").append(typeId);
		}
		if (StringUtils.isBlank(order)) {
			sql.append(" order by create_time desc");
		} else if (StringUtils.equals("partake", order)) {
			sql.append(" order by partake_count desc");
		}
		sql.append(" limit ").append((page - 1) * rowCount).append(",")
				.append(rowCount);
		List<Map<String, Object>> ids = jdbcTemplateTask.queryForList(sql
				.toString());
		List<TaskItem> result = new ArrayList<TaskItem>(ids.size());
		for (Map<String, Object> idMap : ids) {
			result.add(getTaskItem((Integer) idMap.get("id")));
		}
		return result;
	}
	public int taskCount(){
		return jdbcTemplateTask.queryForInt("select count(id) from task_item");
	}

	public List<Map<String, Object>> taskTypeList() {
		StringBuilder sql = new StringBuilder(" select id from task_type ");
		List<Map<String, Object>> ids = jdbcTemplateTask.queryForList(sql
				.toString());
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>(
				ids.size());
		for (Map<String, Object> idMap : ids) {
			result.add(getTaskType((Integer) idMap.get("id")));
		}
		return result;
	}

	public List<TaskFilter> taskFilters() {
		StringBuilder sql = new StringBuilder(" select id from task_filter ");
		List<Map<String, Object>> ids = jdbcTemplateTask.queryForList(sql
				.toString());
		List<TaskFilter> result = new ArrayList<TaskFilter>(ids.size());
		for (Map<String, Object> idMap : ids) {
			result.add(getTaskFilter((Integer) idMap.get("id")));
		}
		return result;
	}
}
