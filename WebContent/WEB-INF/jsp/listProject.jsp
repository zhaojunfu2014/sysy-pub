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
<title>项目列表|<%=new ConfigUtil().getValue("siteName") %></title>
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
            	<c:forEach items="${article4}" var="article" begin="0" end="10">
                	<li><div style="padding-left:60px;padding-top:10px;height:100px;background-image:url(<%=request.getContextPath()%>/resources/images/lbtn1.png);background-repeat:no-repeat"><a href="${pageContext.request.contextPath}/detail/${article.id }">${article.title }</a></div></li>
                </c:forEach>
                </ul>
            </div>
        </div>
        <div class="hack10"></div>
    	<div class="common1 xmdt1">
        	<div class="top"><div class="bor"><div class="l">项目动态</div><span class="r"><a href="${pageContext.request.contextPath}/list/5">更多>></a></span></div></div>
            <div class="con clearfix">
            	<ul>
            	 <c:forEach items="${article5}" var="article" begin="0" end="10">
                	<li><a href="${pageContext.request.contextPath}/detail/${article.id}">${article.title }</a></li>
                </c:forEach>
                </ul>
            </div>
        </div>
    </div>
	<div class="col-main l">
    	<div class="col-main-box">
    	<div class="common3">
        	<div class="top"><div class="right-position">当前位置：<a href="${pageContext.request.contextPath}/">首页</a> > <span class="blue1">项目列表</span></div></div>
            <div class="con">
            	
   <table width="100%" border="1" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
      <tr>
      	<td><div align="center"><span>序号</span></div></td>
        <td><div align="center"><span>项目编号</span></div></td>
        <td><div align="center"><span>项目名称</span></div></td>
        <td><div align="center"><span>质监手续</span></div></td>
        <td><div align="center"><span>项目划分</span></div></td>
        <td><div align="center"><span>监督计划（交底）</span></div></td>
       	<td><div align="center"><span>重要单元核备</span></div></td>
        <td><div align="center"><span>分部核备</span></div></td>
        <td><div align="center"><span>单位工程外观质量核定</span></div></td>
        <td><div align="center"><span>单位核定</span></div></td>
        <td><div align="center"><span>质量监督通报</span></div></td>
        <td><div align="center"><span>质量监督报告</span></div></td>
      </tr>
      <c:forEach items="${datas}" var="data">
      <tr>
      		<td><div align="center">${data.id}</div></td>
	        <td><div align="center">${data.code}</div></td>
	        <td><div align="center"><div align="center">${data.name}</div></td>
	       <td><div align="center"><a href="<%=request.getContextPath()%>/project/${data.id}/1"><c:if test="${data.type1==true}"><img border=0  src="${pageContext.request.contextPath}/resources/images/finish.png"/></c:if><c:if test="${data.type1!=true}"><img  border=0 src="${pageContext.request.contextPath}/resources/images/unfinish.png"/></c:if></a></div></td>
	        <td><div align="center"><a href="<%=request.getContextPath()%>/project/${data.id}/2"><c:if test="${data.type2==true}"><img  border=0 src="${pageContext.request.contextPath}/resources/images/finish.png"/></c:if><c:if test="${data.type2!=true}"><img  border=0 src="${pageContext.request.contextPath}/resources/images/unfinish.png"/></c:if></a></div></td>
	        <td><div align="center"><a href="<%=request.getContextPath()%>/project/${data.id}/3"><c:if test="${data.type3==true}"><img border=0  src="${pageContext.request.contextPath}/resources/images/finish.png"/></c:if><c:if test="${data.type3!=true}"><img  border=0 src="${pageContext.request.contextPath}/resources/images/unfinish.png"/></c:if></a></div></td>
	        <td><div align="center"><a href="<%=request.getContextPath()%>/project/${data.id}/4"><c:if test="${data.type4==true}"><img border=0  src="${pageContext.request.contextPath}/resources/images/finish.png"/></c:if><c:if test="${data.type4!=true}"><img  border=0 src="${pageContext.request.contextPath}/resources/images/unfinish.png"/></c:if></a></div></td>
	        <td><div align="center"><a href="<%=request.getContextPath()%>/project/${data.id}/5"><c:if test="${data.type5==true}"><img border=0  src="${pageContext.request.contextPath}/resources/images/finish.png"/></c:if><c:if test="${data.type5!=true}"><img  border=0 src="${pageContext.request.contextPath}/resources/images/unfinish.png"/></c:if></a></div></td>
	       <td><div align="center"><a href="<%=request.getContextPath()%>/project/${data.id}/6"><c:if test="${data.type6==true}"><img border=0  src="${pageContext.request.contextPath}/resources/images/finish.png"/></c:if><c:if test="${data.type6!=true}"><img  border=0 src="${pageContext.request.contextPath}/resources/images/unfinish.png"/></c:if></a></div></td>
	        <td><div align="center"><a href="<%=request.getContextPath()%>/project/${data.id}/7"><c:if test="${data.type7==true}"><img border=0  src="${pageContext.request.contextPath}/resources/images/finish.png"/></c:if><c:if test="${data.type7!=true}"><img  border=0 src="${pageContext.request.contextPath}/resources/images/unfinish.png"/></c:if></a></div></td>
	        <td><div align="center"><a href="<%=request.getContextPath()%>/project/${data.id}/8"><c:if test="${data.type8==true}"><img border=0  src="${pageContext.request.contextPath}/resources/images/finish.png"/></c:if><c:if test="${data.type8!=true}"><img  border=0 src="${pageContext.request.contextPath}/resources/images/unfinish.png"/></c:if></a></div></td>
	        <td><div align="center"><a href="<%=request.getContextPath()%>/project/${data.id}/9"><c:if test="${data.type9==true}"><img  border=0 src="${pageContext.request.contextPath}/resources/images/finish.png"/></c:if><c:if test="${data.type9!=true}"><img  border=0 src="${pageContext.request.contextPath}/resources/images/unfinish.png"/></c:if></a></div></td>
       
      </tr>
       </c:forEach>
       
    </table>
                
                <div class="hack20"></div>
                <div id="page" class="clearfix">
                 <pg:pager items="${total}" url="${pageContext.request.contextPath}/project/list" maxIndexPages="15"  maxPageItems="20">
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