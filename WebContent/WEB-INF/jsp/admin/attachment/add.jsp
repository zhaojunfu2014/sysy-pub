<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp" %>
<html>
<body bgcolor="#E6E6FA">
<form action="${pageContext.request.contextPath}/admin/attachment/add" method="post" enctype="multipart/form-data">
	<input type="file"  name="u"/><input type="submit" value="上传"/>
</form>
已上传附件：
<c:forEach items="${sessionScope.attachments}" var="att">
<br>${att.name}[${att.size/1000}KB]【<a href="#" onclick="javascript:parent.insertAttached('${pageContext.request.contextPath}/${att.path}','${att.type}');">插入</a>|<a href="#" onclick="javascript:window.location.href='${pageContext.request.contextPath}/admin/attachment/delete/${att.id}'">删除</a>】
</c:forEach>
</body>
</html>