package com.abc.task.ruleexecutor;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.abc.task.exception.ParameterException;
import com.abc.task.vo.AccountLog;
import com.abc.task.vo.Member;
import com.abc.task.vo.TaskItem;
import com.abc.task.vo.TaskRule;

@Component
public class RandomAwardExecutor implements RuleExecutor {
	// private static final Log log =
	// LogFactory.getLog(RandomAwardExecutor.class);
	@Resource
	JdbcTemplate jdbcTemplateTask;

	@Override
	public List<AccountLog> execute(TaskItem task, Member member,
			TaskRule rule, String data) throws ParameterException {
		// TODO Auto-generated method stub
		return null;
	}
}
