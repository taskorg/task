package com.abc.task.web.uc;

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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.abc.task.annotation.LoginMember;
import com.abc.task.annotation.RequiredLogin;
import com.abc.task.annotation.ResultTypeEnum;
import com.abc.task.lang.Function;
import com.abc.task.service.MemberService;
import com.abc.task.vo.Member;

@Controller
public class UcenterActions {
	private static final Log log = LogFactory.getLog(UcenterActions.class);
	@Resource
	private MemberService memberService;

	@Resource
	private ObjectMapper objectMapper;

	@Resource
	private Map<String, String> config;

	@ResponseBody
	@RequestMapping("/ucenter/checkname.action")
	public Map<String, String> checkName(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "v", required = true) String name) {
		boolean exist = memberService.checkName(name);
		Map<String, String> r = new HashMap<String, String>(1);
		if (exist) {
			r.put("msg", "103001");
		} else {
			r.put("msg", "001000");
		}
		return r;
	}

	@ResponseBody
	@RequestMapping("/ucenter/checkmobile.action")
	public Map<String, String> checkMobile(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "v", required = true) String mobile) {
		boolean exist = memberService.checkMobile(mobile);
		Map<String, String> r = new HashMap<String, String>(1);
		if (exist) {
			r.put("msg", "105001");
		} else {
			r.put("msg", "001000");
		}
		return r;
	}

	@ResponseBody
	@RequestMapping("/ucenter/checkemail.action")
	public Map<String, String> checkEmail(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "v", required = true) String email) {
		boolean exist = memberService.checkEmail(email);
		Map<String, String> r = new HashMap<String, String>(1);
		if (exist) {
			r.put("msg", "104001");
		} else {
			r.put("msg", "001000");
		}
		return r;
	}

	@ResponseBody
	@RequestMapping("/ucenter/changeinfo.action")
	@RequiredLogin
	public Map<String, String> changeInfo(HttpServletRequest request,
			HttpServletResponse response, @LoginMember Member m,
			@RequestParam(value = "n", required = true) String newpwd) {
		Map<String, String> r = new HashMap<String, String>(1);
		r.put("msg", "103003");
		return r;
	}

	@ResponseBody
	@RequiredLogin
	@RequestMapping("/ucenter/changepwd.action")
	public Map<String, Object> changePwd(HttpServletRequest request,
			HttpServletResponse response, @LoginMember Member loginMember,
			@RequestParam(value = "o", required = true) String oldpwd,
			@RequestParam(value = "n", required = true) String newpwd) {
		Map<String, Object> r = Function.checkPassword(newpwd);
		if(!r.isEmpty()){
			return r;
		}
		boolean isBlank = memberService.isBlankPassword(loginMember.getId());
		// if first set password,skip the check
		if (!isBlank) {
			Member m = memberService.findUser(loginMember.getEmail(), oldpwd);
			if (m == null) {
				r.put("msg", "101001");
				return r;
			}
		}
		int count = 0;
		try {
			count = memberService.updatePassword(loginMember.getId(), newpwd);
		} catch (Exception e) {
			log.error(e, e);
		}
		if (count == 0) {
			r.put("msg", "000001");
		} else {
			r.put("msg", "001000");
		}
		return r;
	}

	@RequiredLogin(ResultTypeEnum.json)
	@ResponseBody
	@RequestMapping("/ucenter/checkpwd.action")
	public Map<String, String> checkpwd(HttpServletRequest request,
			HttpServletResponse response, @LoginMember Member loginMember,
			@RequestParam(value = "v", required = true) String oldpwd) {
		Member m = memberService.findUser(loginMember.getEmail(), oldpwd);
		Map<String, String> r = new HashMap<String, String>(1);
		if (m == null) {
			r.put("msg", "101001");
		} else {
			r.put("msg", "001000");
		}
		return r;
	}

	@RequiredLogin(ResultTypeEnum.json)
	@ResponseBody
	@RequestMapping("/ucenter/bindplats.action")
	public List<String> bindPlats(HttpServletRequest request,
			HttpServletResponse response, @LoginMember Member loginMember) {
		return memberService.bindPlats(loginMember.getId());
	}
}
