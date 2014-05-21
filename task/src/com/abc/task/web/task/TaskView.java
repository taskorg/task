package com.abc.task.web.task;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.abc.task.service.TaskService;
import com.abc.task.vo.TaskItem;

@Controller
public class TaskView {
	@Resource
	private TaskService taskService;
	@Resource
	private ObjectMapper objectMapper;

	@RequestMapping(value = "/task")
	public String taskInfo(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "id", required = false, defaultValue = "0") int itemId) {
		if (itemId < 0) {
			request.setAttribute("msg", "任务不存在");
			return "error.jsp";
		}
		// 读取任务基本信息
		TaskItem task = taskService.getTaskItem(itemId);
		if (task == null) {
			request.setAttribute("msg", "任务不存在");
			return "error.jsp";
		}
		request.setAttribute("task", task);
		
		List<Map<String, Object>> types = taskService.taskTypeList();
		request.setAttribute("types", types);

		return "/task/view";
	}
	@RequestMapping(value="/demo")
	public String demo(){
		return "/task/demo";
	}
}
