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
<title>${this.name}|<%=new ConfigUtil().getValue("siteName") %></title>
<link href="<%=request.getContextPath() %>/resources/css/css.css" rel="stylesheet" type="text/css" />
</head>

<body style="background-color: gray;">
<%@ include file="/top.jsp" %>
<div class="main clearfix">
	<div class="col-left l">
	<c:if test="${fn:length(this.childs)>0}">
		<div class="common1 dwgk">
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
	<c:if test="${this.parent!=null}">
		<div class="common1 dwgk">
	        	<div class="top"><div class="bor"><div class="l">${this.parent.name}</div></div></div>
	            
		            <div class="con">
		                <ul>
		                <c:forEach items="${this.parent.childs}" var="m">
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
    	<div class="common1 flfg">
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
        	<div class="top"><div class="right-position">当前位置：<a href="${pageContext.request.contextPath}/">首页</a> > <span class="blue1">${this.name }</span></div></div>
            <div class="con">
            	<ul class="list">
            	<c:forEach items="${datas}" var="data">
            	<c:if test="${data.visible==true}">
                <li><a href="${pageContext.request.contextPath}/detail/${data.id }">${data.title }</a><span><fmt:formatDate value="${data.pubDate }" type="date" dateStyle="long"/></span></li>
	               </c:if>
	                
	               
                </c:forEach>
                <c:forEach items="${this.childs}" var="cm">
	                	<c:forEach items="${cm.articles}" var="cmarticle">
	                	<c:if test="${cmarticle.visible==true}">
	                		<li><a href="${pageContext.request.contextPath}/detail/${cmarticle.id }">${cmarticle.title }</a><span><fmt:formatDate value="${cmarticle.pubDate }" type="date" dateStyle="long"/></span></li>
	                	</c:if>
	                	</c:forEach>
	            </c:forEach>
                </ul>
                <div class="hack20"></div>
                <div id="page" class="clearfix">
                 <pg:pager items="${total}" url="${pageContext.request.contextPath}/list/${this.id }" maxIndexPages="15"  maxPageItems="20">
						<pg:first><a href="${pageUrl}">首页</a></pg:first>
						<pg:prev><a href="${pageUrl}">上一页</a></pg:prev>
						<pg:pages>
						<c:if test="${nowPage eq pageNumber}"><font color=red>${pageNumber}</font>&nbsp;</c:if>
						<c:if test="${nowPage != pageNumber}"><a href="${pageUrl}">${pageNumber}</a>&nbsp;</c:if>
						
						
						</pg:pages>
						<pg:next><a href="${pageUrl}">下一页</a></pg:next>
						<pg:last><a href="${pageUrl}">尾页</a></pg:last>
						|共有${total}条数据
					</pg:pager>
                </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="hack10"></div>
<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>