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
<title>${data.title}|<%=new ConfigUtil().getValue("siteName") %></title>
<link href="<%=request.getContextPath() %>/resources/css/css.css" rel="stylesheet" type="text/css" />
</head>

<body style="background-color: gray;">
<%@ include file="/top.jsp" %>
<div class="main clearfix">
	<div class="col-left l">
	<c:if test="${fn:length(this.childs)>0}">
		<div class="common1 bszn">
	        	<div class="top"><div class="bor"><div class="l">子栏目</div></div></div>
	            
		            <div class="con">
		                <ul>
		                <c:forEach items="${this.childs}" var="m">
		               	 <li><a href="${pageContext.request.contextPath}/list/${m.id }">${m.name }</a></li>
		                </c:forEach>
		                </ul>
		            </div>
		          
	    </div>
	    </c:if>
    	<div class="common1 bszn">
        	<div class="top"><div class="bor"><div class="l">政策法规</div><span class="more r"><a href="${pageContext.request.contextPath}/list/6">+更多</a></span></div></div>
            <div class="con" style="height:280px;">
            	<ul>
            	<c:forEach items="${article6}" var="article" begin="0" end="5">
            	<c:if test="${article.visible==true}">
                	<li><div style="padding-left:60px;padding-top:10px;height:100px;background-image:url(<%=request.getContextPath()%>/resources/images/lbtn1.png);background-repeat:no-repeat"><a href="${pageContext.request.contextPath}/detail/${article.id }">${article.title }</a></div></li>
               	</c:if>
                </c:forEach>
                </ul>
            </div>
        </div>
        <div class="hack10"></div>
    	<div class="common1 flfg" >
        	<div class="top"><div class="bor"><div class="l">入网企业</div><span class="more r"><a href="${pageContext.request.contextPath}/list/10">+更多</a></span></div></div>
            <div class="con clearfix" style="height:280px;">
            	<ul>
            	 <c:forEach items="${article10}" var="article" begin="0" end="10">
            	 <c:if test="${article.visible==true}">
                	<li><a href="${pageContext.request.contextPath}/detail/${article.id}">${article.title }</a></li>
                </c:if>
                </c:forEach>
                </ul>
            </div>
        </div>
    </div>
	<div class="col-main l">
    	<div class="col-main-box">
    	<div class="common3">
        	<div class="top"><div class="right-position">当前位置：<a href="${pageContext.request.contextPath}/">首页</a> > <a href="${pageContext.request.contextPath}/list/${data.menu.id }">${data.menu.name}</a> > <span class="blue1">${data.title }</span></div></div>
            <div class="con">
            	<div class="detail">
            	<h1>${data.title }</h1>
                <h5>发表时间：<fmt:formatDate value="${data.pubDate }" type="date" dateStyle="long"/>&nbsp点击数:<font color=red>${data.count }</font></h5>
                <div class="content f14">
					${data.content}
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