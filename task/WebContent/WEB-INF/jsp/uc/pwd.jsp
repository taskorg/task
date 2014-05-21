<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html><html lang=zh><head>
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
    		<input type="hidden" id="uc_bar_position" value="pwd"/>
    		<input type="hidden" id="check_tag" value="false"/>
    	</form>
    	<%@ include file="ucenter_bar.jsp" %>
        <div class="info_right">
        	<div class="info_right_name font14 color_33 fontbold">基本信息</div>
            <div class="info_table padding_bottom190">
            <div id="msg" class="msg"></div>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
             <tr>
                <td align="right">当前密码：</td>
                <td width="87%" class="login_table_float"><input id="j_task_pwd_check" name="o"  type="password"  style="width: 274px;" class="J_changepwd J_pwd_data forgot_table_text" /></td>
              </tr>
             <tr>
                <td align="right">新密码：</td>
                <td width="87%" class="login_table_float"><input id="j_task_pwd" name="n"  type="password"  style="width: 274px;" class="J_changepwd  J_pwd_data forgot_table_text"  /></td>
              </tr>
              <tr>
                <td align="right">确认新密码：</td>
                <td width="87%" class="login_table_float"><input id="j_task_pwd_re"  type="password"  style="width: 274px; float:left;" class="J_changepwd forgot_table_text"  /></td>
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
<script type="text/javascript" src="/js/pwd.js"></script>
<%@ include file="../foot.jsp" %>
</body>
</html>