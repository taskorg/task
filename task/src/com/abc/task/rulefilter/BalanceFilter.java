package com.abc.task.rulefilter;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.abc.task.exception.ParameterException;

@Component("balance")
public class BalanceFilter implements RuleFilter {
	private static final Log log = LogFactory.getLog(BalanceFilter.class);
	@Resource
	JdbcTemplate jdbcTemplateTask;
	@Resource
	JdbcTemplate jdbcTemplateUc;
	@Override
	public State check(int memberId, int itemId, Map<String, String> params,
			String message) throws ParameterException {
		int leastBalance = Integer.parseInt(params.get("balance"));
		int accountId = jdbcTemplateTask.queryForInt("select mc_account_id from task_item where id=?",itemId);
		int balance = jdbcTemplateUc.queryForInt("select first+second from mc_account where id=?",accountId);
		State state = new State();
		state.setAllow(true);
		if(balance<leastBalance){
			state.setMessage(message);
			state.setAllow(false);
		}
		return state;
	}
}
