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
    		<input type="hidden" id="uc_bar_position" value="delay_score"/>
    	</form>
    	<%@ include file="ucenter_bar.jsp" %>
        <div class="info_right padding_bottom190">
        	<div class="info_right_name font14 color_33 fontbold">我的积分</div>
            <div class="duihuan_info">
            	<p class="color_33 fontbold margin_top15">待返积分：<em id="j_task_score" class="color_hs">--</em>个</p>
                <p class="font12">部分任务等待赠送的积分到账时间为收货后1-5天！ </p>
			</div>
            <div class="jifen_table">
            	<table id="j_task_scorelogs"  width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr class="color_33 fontbold jifen_table_bottom" bgcolor="#f4f4f4">
                    <td width="20%" height="30" align="center">任务时间</td>
                    <td width="10%" class="jifen_table_padding">积分</td>
                    <td width="10%" class="jifen_table_padding">类型</td>
                    <td width="20%" class="jifen_table_padding">备注</td>
                    <td width="10%" class="jifen_table_padding">状态</td>
                </table>
            </div>
            <div id="page" class="pages pages_top"></div>
        </div>
   		<div class="clear"></div>
    </div>
    <div class="clear"></div>
</div>
<script type="text/javascript" src="/js/delay_score.js"></script>
<%@ include file="../foot.jsp" %>
</body>
</html>