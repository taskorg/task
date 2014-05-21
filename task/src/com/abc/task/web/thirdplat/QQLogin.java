package com.abc.task.web.thirdplat;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.abc.task.annotation.LoginMember;
import com.abc.task.annotation.RequiredLogin;
import com.abc.task.enums.Plat;
import com.abc.task.lang.Function;
import com.abc.task.service.MemberService;
import com.abc.task.vo.Member;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;

@Controller
/**
 * qq第三方登录
 * @author zhangjianyong
 */
public class QQLogin {
	@Resource
	private MemberService memberService;
	@Resource
	private ObjectMapper objectMapper;

	@RequestMapping("/qqbind.action")
	public void bind(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			response.sendRedirect(new Oauth().getAuthorizeURL(request));
		} catch (QQConnectException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/qqbindafter.action")
	public String bindAfter(HttpServletRequest request,
			HttpServletResponse response,
			RedirectAttributes redirectAttributes,
			@RequestParam(value = "target", required = false) String target)
			throws IOException {
		String openId = null, token = null, nick = null;

		// 如果没有目标地址,绑定成功后将跳回首页
		if (StringUtils.isBlank(target)) {
			target = "/";
		}

		// 通过qqconnectconfig.properties文件中app_ID,app_KEY,authorizeURL三部分信息
		// 尝试获取授权,如果失败跳转至qq_login_faild页面
		try {
			AccessToken tokenObj = (new Oauth())
					.getAccessTokenByRequest(request);
			token = tokenObj.getAccessToken();
			if (StringUtils.isBlank(token)) {
				return "/thirdplat/qq_login_faild";
			}
			// long tokenExpireIn = accessTokenObj.getExpireIn();
			openId = new OpenID(token).getUserOpenID();
			UserInfo qzoneUserInfo = new UserInfo(token, openId);
			UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
			if (userInfoBean.getRet() != 0) {
				request.setAttribute("error_msg", userInfoBean.getMsg());
				return "/thirdplat/qq_login_faild";
			}
			nick = userInfoBean.getNickname();
		} catch (QQConnectException e) {
			// TODO
		}

		// 查看该QQ是否绑定过平台账号
		Map<String, String> params = new HashMap<String, String>(3);
		params.put("openId", openId);
		params.put("token", token);
		Member u = memberService.isBindThirdPlat(Plat.QQ, params);

		// 已绑定平台,直接登录即可
		if (u != null) {
			String content = objectMapper.writeValueAsString(u);
			Cookie cookie = null;
			response.addCookie(cookie);
			if (cookie == null) {
				return "/thirdplat/qq_login_faild";
			}
			return "redirect:/";
		}

		// 没有绑定,将信息传入下一步
		redirectAttributes.addAllAttributes(params);
		redirectAttributes.addAttribute("nick", nick);
		redirectAttributes.addAttribute("target", target);

		return "redirect:/qqregist.action";
	}

	@RequestMapping("/qqregist.action")
	public String regist(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "target", required = false) String target,
			@RequestParam(value = "openId", required = false) String openId,
			@RequestParam(value = "token", required = false) String token,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "repassword", required = false) String repassword,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "act", required = false) String action,
			@RequestParam(value = "agree", required = false) String agree)
			throws IOException {
		// 非注册及绑定,转到注册页
		if (!StringUtils.equals(action, "regist")
				&& !StringUtils.equals(action, "bind")) {
			return "/thirdplat/qq_bind";
		}

		// 如果没有目标地址,绑定成功后将跳回首页
		if (StringUtils.isBlank(target)) {
			target = "/index";
		}

		if (StringUtils.equals(action, "regist")) {

			if (StringUtils.isBlank(name)) {
				request.setAttribute("name_error", "name_is_null");
			} else if (name.length() < 6 || name.length() > 8) {
				request.setAttribute("name_error", "name_format_error");
			}

			if (StringUtils.isBlank(email)) {
				request.setAttribute("email_error", "email_is_null");
			} else if (Function.checkEmail(email).isEmpty()) {
				request.setAttribute("email_error", "");
			}

			if (StringUtils.isBlank(password)) {
				request.setAttribute("password_error", "password_is_null");
			} else if (password.length() < 6 || password.length() > 8) {
				request.setAttribute("password_error", "password_format_error");
			} else if (!StringUtils.equals(password, repassword)) {
				request.setAttribute("repassword_error", "not_same");
			}

			if (StringUtils.isBlank(email)) {
				request.setAttribute("email_error", "email_is_null");
			} else if (!Function.checkEmail(email).isEmpty()) {
				request.setAttribute("email_error", "");
			}

			String base64Password;
			try {
				base64Password = null;

				// 创建用户
				Member u = new Member();
				u.setName(name);
				u.setEmail(email);
				u.setPassword(base64Password);
				int id = memberService.createMember(u);
				u.setId(id);
				// 绑定QQ
				Map<String, String> params = new HashMap<String, String>(2);
				params.put("token", token);
				params.put("openId", openId);
				memberService.bindThirdPlat(Plat.QQ, u, params);

				// 绑定完成,登录
				Cookie cookie = null;
				response.addCookie(cookie);
				if (cookie == null) {
					return "/thirdplat/qq_bind";
				}
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("message", "create_user_error");
				return "/thirdplat/qq_bind";
			}
			return "redirect:" + target;
		}
		return "/thirdplat/qq_bind";
	}

	@ResponseBody
	@RequiredLogin
	@RequestMapping("/qqunbind.action")
	public Map<String, String> unbind(HttpServletRequest request,
			HttpServletResponse response, @LoginMember Member loginMember)
			throws IOException {
		int mId = loginMember.getId();
		boolean isBlank = memberService.isBlankPassword(mId);
		Map<String, String> r = new HashMap<String, String>(1);
		if(isBlank){
			r.put("msg", "100009");
			return r;
		}
		int count = memberService.unBindThirdPlat(Plat.QQ, mId);
		if (count == -1) {
			r.put("msg", "000001");
			return r;
		} else {
			r.put("msg", "001000");
			return r;
		}
	}
}
