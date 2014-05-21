

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.client.RequestWrapper;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.abc.task.lang.HttpClientHelper;

public class TaobaoLoginTest {
	@Test
	public void login() throws Exception {
		AbstractHttpClient client = (AbstractHttpClient) HttpClientHelper
				.getClient();
		client.setRedirectStrategy(new RedirectStrategyManul());
		HttpResponse response = null;
		HttpPost post = new HttpPost();
		post.setURI(new URI("https://login.taobao.com/member/login.jhtml"));
		List<NameValuePair> params1 = new ArrayList<NameValuePair>();
		params1.add(new BasicNameValuePair("TPL_username", "egoutaobao"));
		params1.add(new BasicNameValuePair("TPL_password", "bob5863egou"));
		params1.add(new BasicNameValuePair("newlogin", "1"));
		// nvps.add(new BasicNameValuePair("TPL_redirect_url",
		// "http://login.taobao.com/member/taobaoke/login.htm?is_login=1"));
		params1.add(new BasicNameValuePair("callback", "1"));

		post.setEntity(new UrlEncodedFormEntity(params1, Consts.UTF_8));
		response = client.execute(post);
		String c1 = EntityUtils.toString(response.getEntity());

		Pattern p1 = Pattern.compile("\"token\":\"(.*)\"");
		Matcher m1 = p1.matcher(c1);
		String token = null;
		if (m1.find()) {
			token = m1.group(1);
		}
		// System.out.println(token);
		HttpGet target = new HttpGet();
		target.setURI(new URI(new StringBuilder(
				"https://passport.alipay.com/mini_apply_st.js?site=0&token=")
				.append(token).append("&callback=vstCallback52").toString()));
		response = client.execute(target);
		String c2 = EntityUtils.toString(response.getEntity());

		Pattern pattern = Pattern.compile("\"st\":\"(.*)\"}");
		Matcher matcher = pattern.matcher(c2);
		String st = null;
		if (matcher.find()) {
			st = matcher.group(1);
		}

		target.reset();
		target.setURI(new URI(
				new StringBuilder("https://login.taobao.com/member/vst.htm?st=")
						.append(st)
						.append("&params=style%3Dminisimple%26sub%3D%26TPL_username%3Degoutaobao%26loginsite%3D0%26from_encoding%3D%26not_duplite_str%3D%26guf%3D%26full_redirect%3Dtrue%26isIgnore%3D%26need_sign%3D%26sign%3D%26from%3Dalimama%26TPL_redirect_url%3Dhttp%25253A%25252F%25252Flogin.taobao.com%25252Fmember%25252Ftaobaoke%25252Flogin.htm%25253Fis_login%25253D1&_ksTS=1377156508383_64&callback=jsonp65")
						.toString()));
		response = client.execute(target);
		// String c3 = EntityUtils.toString(response.getEntity());
		// System.out.println(c3);
		target.reset();
		target.setURI(new URI(
				"https://www.alimama.com/membersvc/my.htm?domain=taobao&service=user_on_taobao&sign_account=797ea75d5de40ea6edf1188d90414b49"));
		BasicHttpContext context =  new BasicHttpContext();
		response = client.execute(target,context);
		String cookie2 = (String)context.getAttribute("cookie2");
		System.out.println(cookie2);
//		String c4 = EntityUtils.toString(response.getEntity());
		// Header[] hg = response.getAllHeaders();
		// for (Header header : hg) {
		// System.out.println(header.getName());
		// }
//		System.out.println(c4);
	}
}

class RedirectStrategyManul extends DefaultRedirectStrategy {
	@Override
	public HttpUriRequest getRedirect(HttpRequest request,
			HttpResponse response, HttpContext context)
			throws ProtocolException {
		RequestWrapper wrapper = (RequestWrapper) request;
//		System.out.println("uri:" + wrapper.getURI().toString());
		Header[] cookies = response.getHeaders("Set-Cookie");
		if (ArrayUtils.isNotEmpty(cookies)) {
			for (Header cookie : cookies) {
				String value = cookie.getValue();
				Pattern pattern = Pattern.compile("([^=]*)=([^;]*);");
				Matcher matcher = pattern.matcher(value);
				while (matcher.find()) {
					String cookieName = matcher.group(1);
					if (StringUtils.equals(cookieName, "cookie2")) {
						String cookieValue = matcher.group(2);
						context.setAttribute(cookieName, cookieValue);
//						request.setParams(new BasicHttpParams().setParameter(
//								cookieName, cookieValue));
					}
				}
			}
		}
		return super.getRedirect(request, response, context);
	}

	@Override
	public boolean isRedirected(HttpRequest request, HttpResponse response,
			HttpContext context) throws ProtocolException {
		return super.isRedirected(request, response, context);
	}
}
