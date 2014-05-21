package com.abc.task.web.uc;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abc.task.annotation.LoginMember;
import com.abc.task.annotation.RequiredLogin;
import com.abc.task.service.MemberService;
import com.abc.task.vo.Member;

@Controller
public class Ucenter {
	@Resource
	private MemberService memberService;

	@Resource
	private ObjectMapper objectMapper;

	@Resource
	private Map<String, String> config;

	@RequestMapping("/ucenter/info.htm")
	@RequiredLogin()
	public String info(HttpServletRequest request,
			HttpServletResponse response, @LoginMember Member m) {
		request.setAttribute("user", m);
		return "/uc/info";
	}

	@RequiredLogin
	@RequestMapping("/ucenter/pwd.htm")
	public String pwd(HttpServletRequest request, HttpServletResponse response,
			@LoginMember Member m) {
		return "/uc/pwd";
	}

	@RequiredLogin
	@RequestMapping("/ucenter/score.htm")
	public String score(HttpServletRequest request,
			HttpServletResponse response, @LoginMember Member m) {
		return "/uc/score";
	}

	@RequiredLogin
	@RequestMapping("/ucenter/delayscore.htm")
	public String scoredelay(HttpServletRequest request,
			HttpServletResponse response, @LoginMember Member m) {
		return "/uc/delay_score";
	}

	@RequiredLogin
	@RequestMapping("/ucenter/exchange.htm")
	public String exchange(HttpServletRequest request,
			HttpServletResponse response, @LoginMember Member m) {
		return "/uc/exchange";
	}

	@RequiredLogin
	@RequestMapping("/ucenter/binding.htm")
	public String binding(HttpServletRequest request,
			HttpServletResponse response, @LoginMember Member m) {
		return "/uc/binding";
	}

}
