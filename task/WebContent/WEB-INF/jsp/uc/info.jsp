<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html><html lang=zh>
<head>
	<%@ include file="../jscss.jsp" %>
<style type="text/css">
body{
	background:#edf0eb}
</style>
</head>
<body>
<%@ include file="../top.jsp" %>
<%@ include file="../head.jsp" %>
<!--  主体部分  -->
<div class="info_imain">
	<div class="info_top_name info_top_name_shezhi">账号设置</div>
    <div class="info_nr_bg">
    	<form>
    		<input type="hidden" id="uc_bar_position" value="info"/>
    	</form>
    	<%@ include file="ucenter_bar.jsp" %>
        <div class="info_right">
        	<div class="info_right_name font14 color_33 fontbold">基本信息</div>
            <div class="info_table padding_bottom190">
            <div id="msg" class="msg"></div>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td align="right" width="13%" valign="middle">登录账号：</td>
                <td width="87%">${user.email }</td>
              </tr>
             <tr>
                <td align="right">昵&nbsp;称：</td>
                <td width="87%" class="login_table_float"><input id="j_task_name" name="n" value="${user.name }" type="text" class="forgot_table_text J_task_data"  /></td>
              </tr>
              <tr>
                <td align="right">手机号：</td>
                <td width="87%" class="login_table_float"><input id="j_task_mobile"  value="${user.mobile }" type="text" class="login_table_text"  disabled="disabled" />
                <div class="reg_table_yz_word color_lv font12"><a id="j_task_mobile_binding" href="javascript:void(0);">绑定手机号</a></div></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td><div class="info_bc_botton"><a id="j_task_save" href="javascript:void(0);">保存<b></b></a></div></td>
              </tr>
            </table>
		</div>
        </div>
   		<div class="clear"></div>
    </div>
    <div class="clear"></div>
</div>
<script type="text/javascript" src="/js/info.js"></script>
<%@ include file="../foot.jsp" %>
</body>
</html>