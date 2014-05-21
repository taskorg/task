<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html><html lang=zh>
<head>
	<%@ include file="jscss.jsp" %>
	<script type="text/javascript" src="js/sollortwo.js"></script>
</head>
<body>
<%@ include file="top.jsp" %>
<%@ include file="head.jsp" %>
<div class="imain">
	<div class="left">
    	<div class="banner"><img src="" /></div>
        <div class="tash_name font16">任务中心</div>
        <div class="tash_bg">
            <div class="tash_list">
            	<ul id="tasklist"></ul>
 		  		<div class="clear"></div>
            </div>
            <div id="page" class="pages"></div>
        </div>
    </div>
    <div class="right">
    	<!--  个人中心-->
    	<div class="center_bg">
        	<div class="center_name font16">个人中心</div>
         <!--   <div class="center_nr">
            	<p>登陆XXXX<br />玩游戏做任务轻松赚集分宝！</p>
                <p class="center_botton"><a href="#">会员登陆<b></b></a></p>
            </div> -->
            <!--已登陆状态-->
            <div class="center_nr_dl">
            	<p>您好<span> zy81856656</span></p>
                <p>集分宝余额：<strong>41</strong>个</p>
               	<p>我最近参与了<b>乱世隋唐</b></p>
                <p class="center_botton_dl"><a href="#">兑换集分宝<b></b></a></p>
            </div>
        </div>
        <!--  公告  -->
        <div class="guanggao_bg">
        	<div class="guanggao_name font16">公告</div>
        	<div class="guanggao_list">
            	<ul>
                	<li><a href="#">商家报名入口</a></li>
                	<li><a href="#">商家报名入口</a></li>
                	<li><a href="#">商家报名入口</a></li>
                	<li><a href="#">商家报名入口</a></li>
                </ul>
            </div>
        </div>
        <!--  最新兑换  -->
        <div class="guanggao_bg">
        	<div class="guanggao_name font16">最近兑换</div>
        	<div class="duihuan_list">
            	<ul>
                	<li><a href="#">si**小静 兑换522个集分宝</a></li>
                	<li><a href="#">si**小静 兑换522个集分宝</a></li>
                	<li><a href="#">si**小静 兑换522个集分宝</a></li>
                	<li><a href="#">si**小静 兑换522个集分宝</a></li>
                	<li><a href="#">si**小静 兑换522个集分宝</a></li>
                </ul>
            </div>
        </div>
        <!--  积分赚取榜  -->
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
<%@ include file="foot.jsp" %>
<script src="/js/index.js" type="text/javascript"></script>
</body>
</html>