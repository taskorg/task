package com.abc.task.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.abc.task.service.TaskService;
import com.abc.task.vo.TaskItem;
import com.caucho.server.http.HttpRequest;
import com.caucho.server.http.HttpResponse;

@Controller
public class Index {

	@Resource
	TaskService taskService;

	@RequestMapping("/index")
	public String index(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<Map<String, Object>> types = taskService.taskTypeList();
		request.setAttribute("types", types);

		List<TaskItem> tasks = taskService.taskItemList(12, 1, 0,
				null);
		request.setAttribute("tasks", tasks);
		return "/index";
	}
	
	@ResponseBody
	@RequestMapping("/loadtasks.action")
	public List<TaskItem> tasks(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "p",defaultValue="1") int page,
			@RequestParam(value = "e" , defaultValue="10") int per,
			@RequestParam(value = "c" , defaultValue="0") int cat){
		return taskService.taskItemList(per, page, cat, "partake");
	}
	@ResponseBody
	@RequestMapping("/taskcount.action")
	public int taskCount(HttpServletRequest request, HttpServletResponse response){
		return taskService.taskCount();
	}
}