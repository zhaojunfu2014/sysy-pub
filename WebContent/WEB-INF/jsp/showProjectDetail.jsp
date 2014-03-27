<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.zjf.cms.util.ConfigUtil"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp" %>
<% %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${detail.detail}|<%=new ConfigUtil().getValue("siteName") %></title>
<link href="<%=request.getContextPath() %>/resources/css/css.css" rel="stylesheet" type="text/css" />
</head>

<body>
<%@ include file="/top.jsp" %>
<div class="main clearfix">
	<div class="col-left l">
	
    	<div class="common1 bszn">
        	<div class="top"><div class="bor"><div class="l">办事指南</div><span class="more r"><a href="${pageContext.request.contextPath}/list/4">+更多</a></span></div></div>
            <div class="con">
            	<ul>
            	<c:forEach items="${article4}" var="article">
                	<li><div style="padding-left:60px;padding-top:10px;height:100px;background-image:url(<%=request.getContextPath()%>/resources/images/lbtn1.png);background-repeat:no-repeat"><a href="${pageContext.request.contextPath}/detail/${article.id}">${article.title }</a></div></li>
                </c:forEach>
                </ul>
            </div>
        </div>
        <div class="hack10"></div>
    	<div class="common1 xmdt1">
        	<div class="top"><div class="bor"><div class="l">项目动态</div><a href="${pageContext.request.contextPath}/list/5"><span class="r">更多>></span></a></div></div>
            <div class="con clearfix">
            	<ul>
            	 <c:forEach items="${article5}" var="article">
                	<li><a href="${pageContext.request.contextPath}/detail/${article.id}">${article.title }</a></li>
                </c:forEach>
                </ul>
            </div>
        </div>
    </div>
	<div class="col-main l">
    	<div class="col-main-box">
    	<div class="common3">
        	<div class="top"><div class="right-position">当前位置：<a href="${pageContext.request.contextPath}">首页</a> >  <span class="blue1">${detail.detail }</span></div></div>
            <div class="con">
            	<div class="detail">
            	<h1>${detail.detail}</h1>
                <h5>更新时间：${detail.updateDate}</h5>
                <div class="content f14">
					${detail.info}
                </div>
                <div class="newsbtn"><a href="javascript:window.close();" target="_self"><img src="<%=request.getContextPath() %>/resources/images/close.png" /></a></div>
                <div class="hack20"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="hack10"></div>
<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>