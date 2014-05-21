<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html><html lang=zh>
<head>
	<%@ include file="../jscss.jsp" %>
</head>
<body>
<%@ include file="../top.jsp" %>
<%@ include file="../head.jsp" %>
<div class="imain">
	<div class="left">
    	<div class="list_left_name"><a href="#">最新赚分宝</a><c:forEach var="t" items="${types }">|<a href="${t.id }">${t.name }</a></c:forEach></div>
        <div class="tash_bg">
        	<div class="list_left_nr">
            	<b><img style="max-height: 205px;max-width:170px " src="${task.logo }" /></b>
                <span>
                	<p><strong class="font16 color_lv">${task.name }</strong></p>
                    <p class="font14 jfb_line color_hs">${task.award } </p>
                    <p>任务名称：${task.name }</p>
                    <p>体验时间：${task.beginDate }——${task.endDate }</p>
                    <div class="jfb_botton"><a href="${task.taskUrl }">立即参与<b></b></a></div><div class="jfb_guanzhu color_99">${task.partakeCount }人关注</div>
                </span>
            </div>
        	<div class="tash_malnav_name margin_top15">
            	<ul>
                	<li class="fontbold"><a href="#" class="hover">教你完成任务</a></li>
                </ul>
            </div>
            <div class="left_task_name font14 fontbold">任务步骤</div>
			<div class="left_task_nr">
            <c:out value="${ruleTemplate }" escapeXml="false"></c:out>
            </div>
        	<c:if test="${not empty task.description }"><div class="tash_malnav_name margin_top15">
            	<ul><li class="fontbold"><a href="#" class="hover">任务描述</a></li></ul></div>
            <div class="left_task_nr">
            <c:out escapeXml="false" value="${task.description }"></c:out><br />
            <c:if test="${not empty task.require }"><p> <strong class="font14 color_33">任务限制条件</strong></p><br />
           <c:out escapeXml="false" value="${task.require }"></c:out></c:if>
            </div></c:if>
            <c:if test="${not empty task.pictures }"><div class="tash_malnav_name margin_top15">
            	<ul><li class="fontbold"><a href="#" class="hover">任务截图</a></li></ul>
            </div>
            <div class="left_jietu"><c:forTokens  var="pic" items="${task.pictures }" delims=",">
            <img src="${pic }" /></c:forTokens></div></c:if>
        </div>
    </div>
    <div class="right">
    	<div class="guanggao_bg">
        	<div class="xiangsi_name font16">相似任务</div>
        	<div class="xiangsi_list">
            	<ul>
                	<li>	
                    	<b><a href="#"><img src="images/demoing/pic_1.jpg" /></a></b>
                        <span>
                        <p class="title"><a href="#">支付宝派豪礼，签到支付宝派豪礼</a></p>
                        <p class="color_99">可赚：<strong class="color_hs"><em class="number">30-4050</em>个</strong></p>
                        <p class="color_99">201281人关注</p>
                        </span>
                    </li>
                	<li>	
                    	<b><a href="#"><img src="images/demoing/pic_1.jpg" /></a></b>
                        <span>
                        <p class="title"><a href="#">支付宝派豪礼，签到支付宝派豪礼</a></p>
                        <p class="color_99">可赚：<strong class="color_hs"><em class="number">30-4050</em>个</strong></p>
                        <p class="color_99">201281人关注</p>
                        </span>
                    </li>
                	<li>	
                    	<b><a href="#"><img src="images/demoing/pic_1.jpg" /></a></b>
                        <span>
                        <p class="title"><a href="#">支付宝派豪礼，签到支付宝派豪礼</a></p>
                        <p class="color_99">可赚：<strong class="color_hs"><em class="number">30-4050</em>个</strong></p>
                        <p class="color_99">201281人关注</p>
                        </span>
                    </li>
                	<li>	
                    	<b><a href="#"><img src="images/demoing/pic_1.jpg" /></a></b>
                        <span>
                        <p class="title"><a href="#">支付宝派豪礼，签到支付宝派豪礼</a></p>
                        <p class="color_99">可赚：<strong class="color_hs"><em class="number">30-4050</em>个</strong></p>
                        <p class="color_99">201281人关注</p>
                        </span>
                    </li>
                	<li>	
                    	<b><a href="#"><img src="images/demoing/pic_1.jpg" /></a></b>
                        <span>
                        <p class="title"><a href="#">支付宝派豪礼，签到支付宝派豪礼</a></p>
                        <p class="color_99">可赚：<strong class="color_hs"><em class="number">30-4050</em>个</strong></p>
                        <p class="color_99">201281人关注</p>
                        </span>
                    </li>
                </ul>
            </div>
        </div>
        <div class="guanggao_bg">
        	<div class="guanggao_name font16">积分赚取榜</div>
            <div class="jifen_name">
            	<ul>
                	<li><a class="hover" id="one1" onmousemove="zahe_1('one',1,2)">今日</a></li>
                	<li class="next"><a id="one2" onmousemove="zahe_1('one',2,2)">昨日</a></li>
                </ul>
            </div>
        	<div class="jifen_list" id="con_one_1">
            	<ul>
                	<li><span>1</span><strong>64687484</strong><p>65,8956,00</p></li>
                	<li><span>2</span><strong>64687484</strong><p>65,8956,00</p></li>
                	<li><span>3</span><strong>64687484</strong><p>65,8956,00</p></li>
                	<li><span>4</span><strong>64687484</strong><p>65,8956,00</p></li>
                	<li><span>5</span><strong>64687484</strong><p>65,8956,00</p></li>
                	<li><span>6</span><strong>64687484</strong><p>65,8956,00</p></li>
                	<li><span>7</span><strong>64687484</strong><p>65,8956,00</p></li>
                	<li><span>8</span><strong>64687484</strong><p>65,8956,00</p></li>
                	<li><span>9</span><strong>64687484</strong><p>65,8956,00</p></li>
                	<li><span>10</span><strong>64687484</strong><p>65,8956,00</p></li>
                </ul>
                <div class="clear"></div>
            </div>
            <div class="jifen_list display_none" id="con_one_2">
            	<ul>
                	<li><span>1</span><strong>777777</strong><p>65,8956,00</p></li>
                	<li><span>2</span><strong>64687484</strong><p>65,8956,00</p></li>
                	<li><span>3</span><strong>64687484</strong><p>65,8956,00</p></li>
                	<li><span>4</span><strong>64687484</strong><p>65,8956,00</p></li>
                	<li><span>5</span><strong>64687484</strong><p>65,8956,00</p></li>
                	<li><span>6</span><strong>64687484</strong><p>65,8956,00</p></li>
                	<li><span>7</span><strong>64687484</strong><p>65,8956,00</p></li>
                	<li><span>8</span><strong>64687484</strong><p>65,8956,00</p></li>
                	<li><span>9</span><strong>64687484</strong><p>65,8956,00</p></li>
                	<li><span>10</span><strong>64687484</strong><p>65,8956,00</p></li>
                </ul> 
                <div class="clear"></div>
            </div>
        </div>
    </div>
    <div class="clear"></div>
</div>
<%@ include file="../foot.jsp" %>
</body>
</html>