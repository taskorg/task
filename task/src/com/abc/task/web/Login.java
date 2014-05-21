package com.abc.task.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.abc.task.coder.DESCoder;
import com.abc.task.lang.CookieUtils;
import com.abc.task.service.MemberService;
import com.abc.task.vo.Member;

@Controller
/**
 * 登录,退出
 * @author yong
 *
 */
public class Login {
	private static final Log log = LogFactory.getLog(Login.class);
	@Resource
	private MemberService memberService;

	@Resource
	private ObjectMapper objectMapper;

	@Resource
	private Map<String, String> config;

	@RequestMapping("/login.htm")
	public String login(HttpServletRequest request,
			HttpServletResponse response) {
		return "/login";
	}

	@RequestMapping("/loginin.action")
	@ResponseBody
	public Map<String, Object> loginIn(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "code", required = true) String code,
			@RequestParam(value = "cookie_age", required = true, defaultValue = "86400") int cookie_age) {
		Map<String, Object> r = new HashMap<String, Object>(1);
		Member u = memberService.findUser(email, password);
		if (u == null) {
			r.put("msg", "login_error");
		} else {
			r.put("msg", "login_ok");
		}
		try {
			String key = config.get("cookie_key");
			String charset = config.get("charset");
			String domain = config.get("domain");
			String json = objectMapper.writeValueAsString(u);
			byte[] des = DESCoder.encrypt(json.getBytes(charset), key.getBytes(charset));
			byte[] cookie_b = DESCoder.encryptBASE64(des);
			CookieUtils.createCookie(response, domain, "loginuser", new String(cookie_b,charset),
					"/", cookie_age, false);
			r.put("name",u.getName());
			r.put("id",u.getId());
		} catch (Exception e) {
			log.error(e, e);
			r.put("msg", "system_error");
		}
		return r;
	}

	@RequestMapping("/loginout.action")
	@ResponseBody
	public Map<String, Object> loginOut(HttpServletRequest request,
			HttpServletResponse response) {
		String domain = config.get("domain");
		CookieUtils.deleteCookie(response, domain,"loginuser");
		Map<String, Object> r = new HashMap<String, Object>(1);
		r.put("msg", "loginout_ok");
		return r;
	}

	@RequestMapping("/loginsuccess.action")
	public String loginSuccess(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "go_url", required = false) String url) {
		if (StringUtils.isBlank(url)) {
			url = "/";
		}
		return "redirect:" + url;
	}
	public static void main(String[] args) throws Exception {
		DefaultHttpClient client = new DefaultHttpClient();
		for (int i = 0; i < 1500; i++) {
			client.getCookieStore().clear();
			HttpGet get1=new HttpGet("http://jm.behe.com/tools/201404/368.html");
			HttpGet get2=new HttpGet("http://prom.gome.com.cn/html/prodhtml/topics/201404/4ydfsk.415.html?intcmp=jd-ad-sy-009");
			HttpResponse re = client.execute(get1);
			re.getEntity().getContent().close();
			Thread.sleep(2000+(long)Math.random()*2000); 
			re =client.execute(get2);
			re.getEntity().getContent().close();
			System.out.println(i);
		}
	}
}
