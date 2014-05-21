package com.abc.task.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.abc.task.coder.DESCoder;
import com.abc.task.enums.MemberStatus;
import com.abc.task.lang.CookieUtils;
import com.abc.task.lang.Function;
import com.abc.task.service.MemberService;
import com.abc.task.vo.Member;

@Controller
/**
 * regist member
 *
 */
public class Regist {

	private static final Log log = LogFactory.getLog(Regist.class);
	@Resource
	private MemberService memberService;
	@Resource
	private Map<String, String> config;
	@Resource
	private ObjectMapper objectMapper;

	@RequestMapping("/regist")
	public String reg(HttpServletRequest req, HttpServletResponse res) {
		return "/regist";
	}
	@RequestMapping("/registsuccess.action")
	public String registSuccess(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "go_url", required = false) String url) {
		if (StringUtils.isBlank(url)) {
			url = "/";
		}
		return "redirect:" + url;
	}
	@ResponseBody
	@RequestMapping("/regist.action")
	public Map<String, Object> reg(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "n", required = false) String name,
			@RequestParam(value = "p", required = false) String password,
			@RequestParam(value = "e", required = false) String email,
			@RequestParam(value = "c", required = false) String code) {
		Map<String, Object> r = Function.checkName(name);
		if (!r.isEmpty()) {
			return r;
		}
		r = Function.checkPassword(password);
		if (!r.isEmpty()) {
			return r;
		}
		r = Function.checkEmail(email);
		if (!r.isEmpty()) {
			return r;
		}
		Member u = new Member(name, password, email,MemberStatus.VALID);
		int id = memberService.createMember(u);
		if(id==0){
			r.put("msg", "000001");
			return r;
		}
		u.setId(id);
		String key = config.get("cookie_key");
		String charset = config.get("charset");
		String domain = config.get("domain");
		try {
			String json = objectMapper.writeValueAsString(u);
			byte[] des = DESCoder.encrypt(json.getBytes(charset),
					key.getBytes(charset));
			byte[] cookie_b = DESCoder.encryptBASE64(des);
			CookieUtils.createCookie(response, domain, "loginuser", new String(
					cookie_b, charset), "/", -1, false);
			r.put("msg", "001000");
			r.put("id", String.valueOf(id));
			r.put("name", u.getName());
		} catch (Exception e) {
			log.error(e,e);
			r.put("msg", "000001");
		}
		return r;
	}
}