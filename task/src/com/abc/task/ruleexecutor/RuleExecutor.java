package com.abc.task.ruleexecutor;

import java.util.List;

import com.abc.task.exception.ParameterException;
import com.abc.task.vo.AccountLog;
import com.abc.task.vo.Member;
import com.abc.task.vo.TaskItem;
import com.abc.task.vo.TaskRule;

public interface RuleExecutor {
	public List<AccountLog> execute(TaskItem task, Member member,
			TaskRule rule, String data) throws ParameterException;
}
