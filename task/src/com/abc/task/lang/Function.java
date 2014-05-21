package com.abc.task.lang;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class Function {

	/**
	 * 是否是邮件
	 * 
	 * @param email
	 * @return
	 */
	public static Map<String, Object> checkEmail(String email) {
		Map<String, Object> r = new HashMap<String, Object>(1);
		String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		if (StringUtils.isBlank(email)) {
			r.put("msg", "104003");
			return r;
		}
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(email);
		boolean isMatched = matcher.matches();
		if (!isMatched) {
			r.put("msg", "104002");
			return r;
		}
		return r;
	}
	public static Map<String, Object> checkName(String name) {
		Map<String, Object> r = new HashMap<String, Object>(1);
		String check = "^[\u4e00-\u9fa5a-zA-Z_0-9]{3,25}$";
		if (StringUtils.isBlank(name)) {
			r.put("msg", "103003");
			return r;
		}
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(name);
		boolean isMatched = matcher.matches();
		if (!isMatched) {
			r.put("msg", "103005");
		}
		return r;
	}
	public static Map<String, Object> checkPassword(String password) {
		Map<String, Object> r = new HashMap<String, Object>(1);
		String check = "[^!\"#@$%&\'()*+,-./:;<=>{|}~?/[/]^_`0-9a-zA-Z]+$";
		if (StringUtils.isBlank(password)) {
			r.put("msg", "100007");
			return r;
		}
		if (password.length() < 8 || password.length() > 25) {
			r.put("msg", "100002");
			return r;
		}
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(password);
		boolean isMatched = matcher.matches();
		if (isMatched) {
			r.put("msg", "100008");
		}
		return r;
	}

	/**
	 * 是否是手机号
	 * 
	 * @param mobile
	 * @return
	 */
	public static boolean isMobile(String mobile) {
		return false;
	}

	public static String template(String templateContent,
			Map<String, Object> params) {
		for (String key : params.keySet()) {
			templateContent = templateContent.replaceAll(
					"\\$\\{" + key + "\\}", (String) params.get(key));
		}
		return templateContent;
	}

	public static String joinMap(Map<String, String[]> params) {
		if (params == null || params.size() < 1) {
			return StringUtils.EMPTY;
		}
		StringBuilder sb = new StringBuilder();
		for (String key : params.keySet()) {
			sb.append(key).append("=")
					.append(StringUtils.join(params.get(key), ","));
		}
		return sb.toString();
	}
}
