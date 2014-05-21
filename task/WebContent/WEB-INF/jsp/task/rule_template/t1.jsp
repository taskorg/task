<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="left_task_table">
<table width="100%" border="0" cellspacing="1" cellpadding="0" class="margin_top30" bgcolor="#e9e9e9">
  <tr>
	<td bgcolor="#f5f5f5" align="center">步骤</td>
	<td bgcolor="#f5f5f5" align="center">奖励要求</td>
	<td bgcolor="#f5f5f5" align="center">奖励金蛋</td>
	<td bgcolor="#f5f5f5" align="center">奖励积分</td>
  </tr><c:forEach var="detail" items="${rule.details }" varStatus="status">
  <tr>
	<td bgcolor="#ffffff" align="center"><span class="bianma">${status.count }</span></td>
	<td bgcolor="#ffffff" align="center" >${rule.title }达到<fmt:formatNumber value="${detail.value  }" />级</td>
	<td bgcolor="#ffffff" align="center" class="fontbold color_hs font14 number">+<fmt:formatNumber value="${detail.wealth_first }" /></td>
	<td bgcolor="#ffffff" align="center" class="fontbold color_hs font14 number">+<fmt:formatNumber value="${detail.wealth_second }" /></td>
  </tr></c:forEach>
</table>
</div>