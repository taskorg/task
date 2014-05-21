<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html><html lang=zh>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" ></meta>
<title>登录</title>
<%@ include file="jscss.jsp" %>
</head>

<body>
<div class="reg_top">
	<div class="logo"><img src="images/logo1.jpg" ></img></div>
    <span><img src="images/pic_01.jpg" ></img></span>
</div>
<div class="reg_bg">
	<div class="reg_left">
        <p class="color_33">还没有账号</p>
        <div class="login_reg"><a href="/regist.htm">立即注册<b></b></a></div>
    	<p class="color_33 margin_top30">使用合作网站帐号登录</p>
            <p class="reg_qq"><a href="/qqbind.htm"></a></p>
            <p class="reg_zfb"><a href="/alipaybind.htm"></a></p>
            <p class="reg_weibo"><a href="/sinabind.htm"></a></p>
    </div>
    <div class="reg_right">
    	<p class="padding_bottom font18 color_66">使用任务平台帐号登录</p>
    	<div id="msg_login" class="login-message"></div>
        <div class="login_table">
        	<form>
        	<input type="hidden"  id="go_url" name="go_url" value="${param.ref }"></input>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td width="13%"  valign="top"  class="td_name"><em>*</em>账&nbsp;&nbsp;&nbsp;号：</td>
                <td width="87%"  height="60"  class="login_table_float" valign="top"  >
                	<input id="J_login_email" name="email" type="text" value="" class="login_table_text J_login_data "  style="width:274px;" tabindex="1"></input>
                </td>
              </tr>
              <tr>
                <td  valign="top"  class="td_name"><em>*</em>密&nbsp;&nbsp;&nbsp;码：</td>
                <td width="87%" height="60"  class="login_table_float" valign="top" >
                	<input id="J_login_password" name="password" value="" type="password" class="login_table_text J_login_data "  style="width:274px" tabindex="2"></input>
                </td>
              </tr>
              <tr>
                <td  valign="top"  class="td_name"><em>*</em>验证码：</td>
                <td width="87%" height="60"  class="login_table_float" valign="top" >
                <input id="J_verify_code" maxlength="4" name="code" type="text" class="login_table_text J_login_data" style="width:74px" tabindex="3"></input>
                <div class="reg_table_yz"><img id="codeimg" src="code.htm" ></img></div>
                <div class="reg_table_yz_word padding_left5 font12">看不清楚？<a href="javascript:void(0);" onclick="identifycode();return false;" class="color_lv">换一张</a></div></td>
              </tr>
              <tr>
                <td class="margin_tishi">&nbsp;</td>
                <td class="color_99 font12"><input name="autologin" type="checkbox" value="" class="input_align J_login_data" tabindex="4"/> 下次自动登陆</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td ><div class="login_botton"><a id="J_login_submit">登录<b></b></a></div><div class="reg_table_yz_word color_lv font12"><a href="#">忘记密码</a></div></td>
              </tr>
            </table>
            </form>
		</div>
    </div>
	<div class="clear"></div>
</div>
<%@ include file="foot.jsp" %>
<script src="/js/login.js" type="text/javascript"></script>
</body>
</html>