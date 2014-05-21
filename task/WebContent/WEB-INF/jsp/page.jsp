<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
int t=Integer.valueOf(request.getParameter("t"));
int p=Integer.valueOf(request.getParameter("p"));
int s=4;
int hs=s/2;
int pre = p-hs>2?p-hs:2;
int end = p+hs>t-1?t-1:p+hs;
%>
<div class="pages">
<%if(p>1){ %><a href="#" class="pages_text">上一页</a><%} %>
<a href="#" <%if(1==p){ %>class="hover"<%} %>>1</a>
<%if(pre-1>1){ %><span>...</span><%} %>
<%for(int i =pre;i<=end;i++){ %>
<a href="#" <%if(i==p){ %>class="hover"<%} %>><%=i %></a>
<%} %>
<%if(t-end>1){ %><span>...</span><%} %>
<a href="#" <%if(t==p){ %>class="hover"<%} %>><%=t %></a>
<%if(p<t){ %><a href="#" class="pages_next">下一页</a><%} %>
</div>