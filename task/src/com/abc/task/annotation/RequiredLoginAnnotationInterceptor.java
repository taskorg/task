package com.abc.task.annotation;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.abc.task.lang.CookieUtils;
import com.abc.task.lang.Function;

public class RequiredLoginAnnotationInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		HandlerMethod handler2 = (HandlerMethod) handler;
		RequiredLogin login = handler2.getMethodAnnotation(RequiredLogin.class);
		if (null == login) {
			return true;
		}
		String loginuser_b64 = CookieUtils.readCookie(request, "loginuser");
		if (null == loginuser_b64) {
			if (login.value() == ResultTypeEnum.page) {
				Map<String, String[]> paramMap = request.getParameterMap();
				Map<String, String[]> params = new HashMap<String,String[]>();
				params.putAll(paramMap);
				params.put(
						"ref",
						new String[] { StringUtils.defaultIfBlank(
								request.getRequestURI(), "/") });
				String paramStr = Function.joinMap(params);
				response.sendRedirect("/login.htm?"+paramStr);
			} else if (login.value() == ResultTypeEnum.json) {
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=UTF-8");
				OutputStream out = response.getOutputStream();
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(out,
						"utf-8"));
				pw.println("{\"result\":false,\"code\":11,\"errorMessage\":\"您未登录,请先登录\"}");
				pw.flush();
				pw.close();
			}
			return false;
		}
		return true;
	}
}