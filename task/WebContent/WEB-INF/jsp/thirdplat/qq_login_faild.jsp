<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>第三方登陆</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
<div class="reg_top"><div class="logo"><img src="images/logo1.jpg" /></div><span><img src="images/pic_01.jpg" /></span></div>
<div class="reg_bg"><div class="qq_nr">
	<p align="center"><img src="images/d_qq.jpg" /></p>
	QQ联合登录失败,请重试<a class="top_nr_qq"  href="/qqLogin.htm">QQ登录</a>
	</div><div class="clear"></div></div>
<%@ include file="../foot.jsp" %>
</body>
</html>