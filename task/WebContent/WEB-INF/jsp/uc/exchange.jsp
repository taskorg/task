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
	<div class="info_top_name info_top_name_shezhi">兑换集分宝</div>
    <div class="info_nr_bg">
    <form>
    		<input type="hidden" id="uc_bar_position" value="exchange"/>
    	</form>
    	<%@ include file="ucenter_bar.jsp" %>
        <div class="info_right padding_bottom190">
        	<div class="info_right_name font14 color_33 fontbold">兑换集分宝</div>
            <div class="duihuan_info">
            	<p class="color_33 fontbold">可用积分：<em id="j_task_score" class="color_hs">--</em>个</p>
                <p class="font12">积分可以兑换成集分宝，集分宝可以还信用卡，缴水电煤费，淘宝购物抵现！<a href="#" class="color_lv">查看演示&gt;&gt;</a> </p>
			</div>
            <div class="duihuan_table margin_top15">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tbody><tr>
                    <td height="35" colspan="2" class="duihuan_bottom color_33">您绑定的支付宝真实姓名：<strong>张艺严</strong> <a href="#" class="color_lv">解除绑定</a> </td>
                  </tr>
                  <tr>
                    <td height="45" width="15%" align="right" class="font14">当前积分：</td>
                    <td width="85%" class="font14"><em id="j_task_score_1" class="color_hs fontbold">--</em>个</td>
                  </tr>
                  <tr>
                    <td height="45" align="right" class="font14">兑换集分宝：</td>
                    <td><input name="" value="" type="text" class="duihuan_table_text" onblur="this.className='duihuan_table_text';" onfocus="this.className='duihuan_table_text_hover';"> 个</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td><div class="duihuan_botton"><a href="#">立即兑换<b></b></a></div></td>
                  </tr>
                </tbody></table>
            </div>
        </div>
   		<div class="clear"></div>
    </div>
    <div class="clear"></div>
</div>
<script type="text/javascript" src="/js/exchange.js"></script>
<%@ include file="../foot.jsp" %>
</body>
</html>