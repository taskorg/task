<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html><html lang=zh>
<head>
	<%@ include file="../jscss.jsp" %>
<style type="text/css">body{background:#edf0eb}</style>
</head>
<body>
<%@ include file="../top.jsp" %>
<%@ include file="../head.jsp" %>
<!--  主体部分  -->
<div class="info_imain">
	<div class="info_top_name info_top_name_shezhi">账号设置</div>
    <div class="info_nr_bg">
    <form>
    		<input type="hidden" id="uc_bar_position" value="binding"/>
    	</form>
    	<%@ include file="ucenter_bar.jsp" %>
        <div class="info_right">
        	<div class="info_right_name font14 color_33 fontbold">账号绑定</div>
            <div class="duihuan_info">
           		 <p class="font12 color_99">
           		 帐号绑定后你可以：<span class="color_hs font16">①</span>将分享一键同步给QQ、微博好友 <span class="color_hs font16">②</span>用绑定的帐号直接登录任务平台</p>
                 <table width="100%" border="0" cellspacing="0" cellpadding="0" class="margin_top15">
                  <tbody>
                  <tr id="j_task_no_bind_qq" class="margin_bottom40">
                    <td width="140px"><div class="bind_qq"></div></td>
                    <td><div class="bind_botton"><a href="/qqbind.action">立即绑定<b></b></a></div></td>
                  </tr>
                  <tr id="j_task_bind_qq" class="margin_bottom40" style="display:none;">
                    <td width="140px"><div class="bind_qq"></div></td>
                    <td id="j_task_qq">
	                    <p class="font12">你已绑定QQ帐号 [<a href="javascript:void(0);" onclick="unbind('qq');" class="color_lv">取消绑定</a>]</p>
	                    <p class="bind_fenxiang font12 fontbold">已同步我的分享</p>
                    </td>
                  </tr>
                  <tr id="j_task_no_bind_sina" class="margin_bottom40">
                    <td width="140px"><div class="bind_weibo"></div></td>
                    <td><div class="bind_botton"><a href="/sinabind.action">立即绑定<b></b></a></div></td>
                  </tr>
                  <tr id="j_task_bind_sina" class="margin_bottom40" style="display:none;">
                    <td width="140px"><div class="bind_weibo"></div></td>
                    <td id="j_task_SINA">
	                    <p class="font12">你已绑定新浪微博帐号 [<a href="javascript:void(0);" onclick="unbind('sina');" class="color_lv">取消绑定</a>]</p>
	                    <p class="bind_fenxiang font12 fontbold">已同步我的分享</p>
                    </td>
                  </tr>
                  <tr  id="j_task_no_bind_alipay" class="margin_bottom40">
                    <td width="140px"><div class="bind_zhifubao"></div></td>
                    <td id="j_task_ALIPAY"><div class="bind_botton"><a href="/alipaybind.action">立即绑定<b></b></a></div></td>
                  </tr>
                  <tr id="j_task_bind_alipay" class="margin_bottom40" style="display:none;">
                    <td width="140px"><div class="bind_zhifubao"></div></td>
                    <td id="j_task_SINA">
	                    <p class="font12">你已绑定支付宝帐号 [<a href="javascript:void(0);" onclick="unbind('alipay');" class="color_lv">取消绑定</a>]</p>
	                    <p class="bind_fenxiang font12 fontbold">已同步我的分享</p>
                    </td>
                  </tr>
                </tbody></table>
			</div>
        </div>
   		<div class="clear"></div>
    </div>
    <div class="clear"></div>
</div>
<script type="text/javascript" src="/js/binding.js"></script>
<%@ include file="../foot.jsp" %>
</body>
</html>