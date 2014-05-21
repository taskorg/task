package com.abc.task.web.thirdplat;

import java.util.Map;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;

import com.abc.task.service.MemberService;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayRequest;
import com.alipay.api.AlipayResponse;

public class MyAlipayClient implements AlipayClient {
	@Resource
	private MemberService memberService;
	@Resource
	private ObjectMapper objectMapper;
	@Resource
	private Map<String, String> config;
	@Override
	public <T extends AlipayResponse> T execute(AlipayRequest<T> arg0)
			throws AlipayApiException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T extends AlipayResponse> T execute(AlipayRequest<T> arg0,
			String arg1) throws AlipayApiException {
		// TODO Auto-generated method stub
		return null;
	}

	
}
