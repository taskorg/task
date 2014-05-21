<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.abc.task.vo.Member,java.util.HashMap,java.util.Map,org.codehaus.jackson.map.ObjectMapper"%>
<%@page import="com.abc.task.lang.CookieUtils,com.abc.task.coder.DESCoder"%>
<%@page import="org.springframework.web.context.WebApplicationContext" %>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%
	WebApplicationContext wac=WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
	ObjectMapper objectMapper = (ObjectMapper)wac.getBean("objectMapper");
	Map<String,String> c = (Map)wac.getBean("config");
	String loginuser_b64 = CookieUtils.readCookie(request, "user");
	Member loginUser = null;
	if(loginuser_b64!=null && c!=null){
		String charset = c.get("charset");
		String key = c.get("cookie_key");
		try{
			byte[] loginuser_c = DESCoder.decryptBASE64(loginuser_b64.getBytes(charset));
			byte[] loginuser = DESCoder.decrypt(loginuser_c, key.getBytes(charset));
			loginUser = objectMapper.readValue(loginuser, Member.class);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
%>
<div class="top_bg">
	<div id="msg_top" ></div>
	<form>
		<input id="go_url" type="hidden" value="/"></input>
	</form>
	<div id="nouser" class="top_nr" >
		您好，欢迎来任务平台！
		<a href="/login.htm">[请登录]</a><a href="/regist.htm" class="top_word">[免费注册]</a> 
		<a class="top_nr_qq"  href="/qqLogin.action">QQ登录</a>
		<a class="top_nr_weibo" href="/sinaLogin.htm">新浪微博</a>
		<a class="top_nr_zfb" href="/alipayLogin.htm">支付宝登录</a>
	</div>
	<div id="haveuser" class="top_nr top_nr_dl" style="display:none">
		<span id="username"></span> 
		<a href="javascript:void(0);" onclick="loginout();return false;">退出</a>
		<a href="/ucenter/score.htm">我的集分宝</a>
		<a href="#">邀请好友</a>
		<a href="/ucenter/info.htm">个人中心</a>
	</div>
</div>
<script type="text/javascript">
var username = "username".getCookie();
if(username){
	$("#nouser").hide();
	$("#username").html(username);
	$("#haveuser").show();
}
loginout_option.success=function(){
	"username".setCookie(this.name, -14, J_task.Config.domain, "/");
	"userid".setCookie(this.name, -14, J_task.Config.domain, "/");
	 var go_url = $("#go_url").val();
     go_url = J_task.Config.website+"loginsuccess.action?go_url=" + encodeURIComponent(go_url);
     window.location.href = go_url;
}
</script>