<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>第三方登陆</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script src="js/jquery.js" type="text/javascript"></script>
</head>
<body>
<div class="reg_top"><div class="logo"><img src="images/logo1.jpg" /></div><span><img src="images/pic_01.jpg" /></span></div>
<div class="reg_bg"><div class="qq_nr">
	<p align="center"><img src="images/d_qq.jpg" /></p>
	<div class="qq_info">
		<span><img src="images/demoing/tuxiang.gif" /></span>
		<strong class="color_33 font14">Hi,${param.nick }，QQ账号授权成功。<br />10秒钟完成设置</strong></div>
	<div class="reg_table padding_left151">
		<form id="registerForm" action="/qqBind.htm" method="get">
		<input type="hidden"  name="act" value="regist"/>
		<input type="hidden"  name="token" value="${param.token }"/>
		<input type="hidden"  name="openId" value="${param.openId }"/>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr><td width="15%"><em>*</em>登录账号：</td>
			<td width="85%"><input name="name"  type="text" class="reg_table_text" 
	             		onblur="this.className='reg_table_text';" onfocus="this.className='reg_table_text_hover';" />
	             		<div class="reg_table_yz_word color_99 font12">请输入手机/邮箱</div></td></tr>
		<tr><td><em>*</em>登录密码：</td>
			<td width="85%"><input name="password" type="password" class="reg_table_text" 
				onblur="this.className='reg_table_text';" onfocus="this.className='reg_table_text_hover';" />
				<div class="reg_table_text_error font12">密码长度在6到20个字符之间</div></td></tr>
		<tr><td><em>*</em>确认密码：</td>
			<td width="85%"><input name="repassword" type="password" class="reg_table_text" 
				onblur="this.className='reg_table_text';" onfocus="this.className='reg_table_text_hover';" /></td></tr>
		<tr><td><em>*</em>电子邮箱：</td>
			<td width="85%"><input name="email" type="text" class="reg_table_text" 
				onblur="this.className='reg_table_text';" onfocus="this.className='reg_table_text_hover';" /></td></tr>
		<tr><td><em>*</em>验证码：</td>
			<td width="85%"><input name="code" type="text" class="reg_table_text_yz" 
				onblur="this.className='reg_table_text_yz';" onfocus="this.className='reg_table_text_yz_hover';" />
				<div class="reg_table_yz"><img src="images/demoing/yz.jpg" /></div>
				<div class="reg_table_yz_word padding_left5 font12">看不清楚？<a href="#" class="color_lv">换一张</a></div></td></tr>
		<tr><td>&nbsp;</td>
			<td class="color_99 font12"><input name="agree" type="checkbox" value="true" class="input_align" /> 同意
			<span class="color_lv">乐活协议</span></td></tr>
		<tr><td>&nbsp;</td>
			<td><div class="login_qq_botton"><a href="javascript:void(0);" onclick="javascript:$('#registerForm').submit();">完成</a></div></td></tr>
		</table></form></div>
	</div><div class="clear"></div></div>
<%@ include file="../foot.jsp" %>
</body>
</html>