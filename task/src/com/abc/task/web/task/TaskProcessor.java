package com.abc.task.web.task;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.abc.task.annotation.LoginMember;
import com.abc.task.annotation.RequiredLogin;
import com.abc.task.lang.SerialNumberGenerator;
import com.abc.task.ruleexecutor.RuleExecutor;
import com.abc.task.rulefilter.RuleFilter;
import com.abc.task.rulefilter.State;
import com.abc.task.service.AccountService;
import com.abc.task.service.TaskService;
import com.abc.task.vo.AccountLog;
import com.abc.task.vo.Member;
import com.abc.task.vo.TaskFilter;
import com.abc.task.vo.TaskFilterOption;
import com.abc.task.vo.TaskItem;
import com.abc.task.vo.TaskRule;

@Controller
public class TaskProcessor {
	@Resource
	private TaskService taskService;
	@Resource
	private AccountService accountService;
	@Resource
	private ObjectMapper objectMapper;
	@Resource
	BeanFactory beanFactory;
	private static final Log log = LogFactory.getLog(TaskProcessor.class);

	@SuppressWarnings("unchecked")
	@RequiredLogin
	@RequestMapping(value = "/processor.action")
	public String taskProcessor(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "itemId", required = false, defaultValue = "0") int itemId,
			@LoginMember Member loginMember) {

		String mName = loginMember.getName();
		int mId = loginMember.getId();
		TaskItem task = taskService.getTaskItem(itemId);
		List<TaskFilterOption> options = taskService
				.getTaskFilterOptions(itemId);

		Map<String, String> params = new HashMap<String, String>();

		Map<String, String> error = new HashMap<String, String>(options.size());
		for (TaskFilterOption option : options) {
			int optionId = option.getId();
			int filterId = option.getFilterId();
			// 过滤器默认参数
			TaskFilter filter = taskService.getTaskFilter(filterId);
			String defaultParams = filter.getDefaultParams();
			if (StringUtils.isNotBlank(defaultParams)) {
				try {
					params.putAll(objectMapper.readValue(defaultParams,
							Map.class));
				} catch (Exception e) {
					log.error("filter defalut params convert to json error,"
							+ filterId, e);
				}
			}
			// 该任务针对该过滤器设置的参数
			String optionParams = option.getParams();
			if (StringUtils.isNotBlank(optionParams)) {
				try {
					params.putAll(objectMapper.readValue(optionParams,
							Map.class));
				} catch (Exception e) {
					log.error("filteroption params convert to json error,"
							+ optionId, e);
				}
			}
			params.put("ip", request.getRemoteAddr());
			String classPath = filter.getClassPath();
			State state = null;
			try {
				RuleFilter instance = (RuleFilter) beanFactory.getBean(classPath);
				state = instance
						.check(mId, itemId, params, filter.getMessage());
			} catch (Exception e) {
				log.error("taskfilter error:" + classPath, e);
				return "/task/error";
			}
			if (state != null && !state.isAllow()) {
				String name = filter.getName();
				error.put(name, state.getMessage());
			}
		}
		if (error.size() > 0) {
			request.setAttribute("error", error);
			return "/task/error";
		}
		String serialNumber = SerialNumberGenerator.generate();
		Date time = Calendar.getInstance().getTime();
		List<AccountLog> logs = null;
		List<TaskRule> rules = taskService.getTaskRules(itemId);
		for (TaskRule rule : rules) {
			String excecutorClass = rule.getClassPath();
			try {
				
				RuleExecutor instance = (RuleExecutor) beanFactory.getBean(excecutorClass);
				logs = instance.execute(task, loginMember, rule, null);
			} catch (Exception e) {
				log.error("rule excecutor error:" + excecutorClass, e);
			}
		}
		// 设置用户,商家,流水号,消费时间
		for (int i = 0; i < logs.size(); i++) {
			AccountLog log = logs.get(i);
			log.setMemberId(mId);
			log.setMemberName(mName);
			log.setMerchantId(task.getMerchantId());
			log.setMerchantName(task.getMerchantName());
			log.setMcAccountId(task.getMcAccountId());
			log.setSerialNumber(serialNumber);
			log.setSubSerialNumber(serialNumber + i);
			log.setCreateTime(time);
			log.setOperator("system");
		}
		accountService.insertAccountTmpLog(logs);
		return "/task/result";
	}
}
