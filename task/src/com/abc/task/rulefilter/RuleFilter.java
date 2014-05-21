package com.abc.task.rulefilter;

import java.util.Map;

import com.abc.task.exception.ParameterException;

public interface RuleFilter {
	State check(int memberId, int itemId, Map<String, String> params,
			String message) throws ParameterException;
}