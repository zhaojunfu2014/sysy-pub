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
<title>${info.code}|明细列表|<%=new ConfigUtil().getValue("siteName") %></title>
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
        	<div class="top"><div class="right-position">当前位置：<a href="${pageContext.request.contextPath}/">首页</a> > <a href="${pageContext.request.contextPath}/project/list">项目列表</a> > <span class="blue1">${info.code}明细列表</span></div></div>
            <div class="con">
            	
   <table width="100%" border="1" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
      <tr>
      	<td><div align="center"><span>描述</span></div></td>
        <td><div align="center"><span>是否完成</span></div></td>
        <td><div align="center"><span>更新时间</span></div></td>
        <td><div align="center"><span>操作</span></div></td>
      </tr>
      <c:forEach items="${datas}" var="data">
      
      <tr>
      		<td><div align="center">${data.detail}</div></td>
	        <td><div align="center"><c:if test="${data.finish==false}"><font color=red>未完成</font></c:if><c:if test="${data.finish==true}"><font color=green>已完成</font></c:if></div></td>
	        <td><div align="center"><div align="center">${data.updateDate}</div></td>
	        
	        <td>
	        <c:if test="${data.visible!=false}">
	        <div align="center"><a href="<%=request.getContextPath()%>/project/detail/${data.id}" target="_blank">查看详细</a></div>
	        </c:if>
	        </td>
       		
      </tr>
      
       </c:forEach>
       
    </table>
                
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