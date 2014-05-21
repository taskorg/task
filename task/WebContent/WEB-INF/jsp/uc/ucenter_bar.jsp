<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="info_left">
	<div class="info_menu_name font14">个人资料</div>
	<div class="info_menu">
		<ul>
			<li><a id="info"  href="/ucenter/info.htm" class="J_uc_bar">基本信息</a></li>
			<li><a id="pwd"  href="/ucenter/pwd.htm" class="J_uc_bar">修改密码</a></li>
			<li><a id="binding"  href="/ucenter/binding.htm" class="J_uc_bar">账号绑定</a></li>
		</ul>
	</div>
	<div class="info_menu_name font14">个人资产</div>
	<div class="info_menu">
		<ul>
			<li><a id="score"  href="/ucenter/score.htm" class="J_uc_bar">我的积分</a></li>
			<li><a id="delay_score"  href="/ucenter/delayscore.htm" class="J_uc_bar">我的待返积分</a></li>
			<li><a id="exchange"  href="/ucenter/exchange.htm" class="J_uc_bar">兑换集分宝</a></li>
		</ul>
	</div>
</div>
<script type="text/javascript">
var selector = $("#uc_bar_position").val();
$(".J_uc_bar.hover").removeClass("hover");
$("#"+selector).addClass("hover");
</script>