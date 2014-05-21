<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>注册页</title>
<%@ include file="jscss.jsp" %>
</head>

<body>
<div class="reg_top">
	<div class="logo"><img src="images/logo1.jpg"></img></div>
    <span><img src="images/pic_01.jpg" ></img></span>
</div>
<div class="reg_bg">
	<div class="reg_left">
    	<p class="padding_bottom font18 color_66">直接用以下帐号登录</p>
        <div class="reg_botton">
            <p class="reg_qq"><a href="${config.domain}/qqbind.action"></a></p>
            <p class="reg_zfb"><a href="${config.domain}/alipayLogin.htm"></a></p>
            <p class="reg_weibo"><a href="${config.domain}/sinaLogin.htm"></a></p>
        </div>
        <p class="color_66">已有任务平台账号</p>
        <div class="reg_login"><a href="${config.domain}/login.htm">登录<b></b></a></div>
    </div>
    <div class="reg_right">
    	<p class="padding_bottom font18 color_66"><em class="number">30</em>秒免费注册任务平台帐号</p>
    	<div id="j_task_msg" class="login-message"></div>
        <div class="reg_table">
        	<form id="registForm" >
        	<input type="hidden"  id="go_url" name="go_url" value="${param.ref }"></input>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="15%"><em>*</em><label for="j_task_name">登录账号：</label></td>
                <td width="85%"><input id="j_task_name" name="n"  type="text"  class="reg_table_text J_data" ></input></td>
              </tr>
              <tr>
                <td><em>*</em>登录密码：</td>
                <td width="85%"><input id="j_task_pwd" name="p" type="password" class="reg_table_text J_data" ></input>
              </tr>
              <tr>
                <td><em>*</em>确认密码：</td>
                <td width="85%"><input id="j_task_pwd_re"  name="rp" type="password" class="reg_table_text J_data" ></input></td>
              </tr>
              <tr>
                <td><em>*</em>电子邮箱：</td>
                <td width="85%"><input id="j_task_email"  name="e" type="text" class="reg_table_text J_data" ></input>
              </tr>
              <tr>
                <td><em>*</em>验证码：</td>
                <td width="85%"><input id="J_verify_code"  name="c" type="text" class="reg_table_text J_data" ></input>
                	<div class="reg_table_yz"><img id="codeimg" src="code.htm" ></img></div>
                	<div class="reg_table_yz_word padding_left5 font12">看不清楚？<a href="javascript:void(0);" onclick="identifycode();return false;" class="color_lv">换一张</a></div></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td class="color_99 font12"><input name="agree" type="checkbox" value="" class="input_align" ></input> 同意<span class="color_lv">乐活协议</span></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><div class="register_botton"><a id="j_task_submit">立即注册<b></b></a></div></td>
              </tr>
            </table>
            </form>
		</div>
    </div>
	<div class="clear"></div>
</div>
<%@ include file="foot.jsp" %>
<script src="/js/regist.js" type="text/javascript"></script>
</body>
</html>