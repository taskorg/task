package com.abc.task.ruleexecutor;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.abc.task.enums.MCAccount;
import com.abc.task.enums.UCAccount;
import com.abc.task.enums.UCAccountLogStatus;
import com.abc.task.enums.WealthType;
import com.abc.task.exception.ParameterException;
import com.abc.task.vo.AccountLog;
import com.abc.task.vo.Member;
import com.abc.task.vo.TaskItem;
import com.abc.task.vo.TaskRule;

@Component("fixed")
public class FixedAwardExecutor implements RuleExecutor {
	@Resource
	JdbcTemplate jdbcTemplateTask;

	@Override
	public List<AccountLog> execute(TaskItem task, Member member,
			TaskRule rule, String data) {

		List<AccountLog> logs = new ArrayList<AccountLog>(4);
		AccountLog log = new AccountLog();
		log.setUcAccount(UCAccount.S1);
		log.setMcAccount(MCAccount.FIRST);
		log.setWealthType(WealthType.TASK);
		log.setStatus(UCAccountLogStatus.UNCHECK);
		log.setRemark(task.getName());
		try {
			int ucwealth = Integer.parseInt(rule.getExt1());
			int mcwealth = Integer.parseInt(rule.getExt2());
			log.setMcWealth(mcwealth);
			log.setUcWealth(ucwealth);
		} catch (Exception e) {
			throw new ParameterException(e);
		}
		logs.add(log);
		return logs;
	}
}
