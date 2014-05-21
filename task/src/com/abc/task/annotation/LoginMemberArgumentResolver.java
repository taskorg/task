package com.abc.task.annotation;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.abc.task.coder.DESCoder;
import com.abc.task.lang.CookieUtils;
import com.abc.task.vo.Member;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.lang.annotation.Annotation;
import java.util.Map;

public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {

	@Resource
	private Map<String, String> config;
	
	@Resource
	private ObjectMapper objectMapper;
	
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
    	return parameter.hasParameterAnnotation(LoginMember.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        Annotation[] annotations = parameter.getParameterAnnotations();
        for (Annotation annotation : annotations) {
            if (LoginMember.class.isInstance(annotation)) {
                HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest();
                String loginuser_b64 = CookieUtils.readCookie(request, "loginuser");
        		Member loginUser = null;
        		String charset = config.get("charset");
        		String key = config.get("cookie_key");
        		if (loginuser_b64 != null) {
        			try {
        				byte[] loginuser_c = DESCoder.decryptBASE64(loginuser_b64
        						.getBytes(charset));
        				byte[] loginuser = DESCoder.decrypt(loginuser_c,
        						key.getBytes(charset));
        				loginUser = objectMapper.readValue(loginuser, Member.class);
        			} catch (Exception e) {
        				e.printStackTrace();
        			}
        		}
        		return loginUser;
            }
        }
        return null;
    }
}