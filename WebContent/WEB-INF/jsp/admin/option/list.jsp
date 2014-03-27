<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
<!--
body {
	margin-left: 3px;
	margin-top: 0px;
	margin-right: 3px;
	margin-bottom: 0px;
}
.STYLE1 {
	color: #e1e2e3;
	font-size: 12px;
}
.STYLE6 {color: #000000; font-size: 12; }
.STYLE10 {color: #000000; font-size: 12px; }
.STYLE19 {
	color: #344b50;
	font-size: 12px;
}
.STYLE21 {
	font-size: 12px;
	color: #3b6375;
}
.STYLE22 {
	font-size: 12px;
	color: #295568;
}
A:active,A:visited,A:link {
	COLOR: #0629FD;
	TEXT-DECORATION: none
}

A:hover {
	COLOR: #FF6600;
	TEXT-DECORATION: none
}

A.relatelink:active,A.relatelink:visited,A.relatelink:link { 
	color: white;
	TEXT-DECORATION: none
}

A.relatelink:hover {
	COLOR: #FF6600;
	TEXT-DECORATION: none
}

td {
	font-size: 12px;
	color: #003366;
}

.STYLE1 a{
	COLOR: white;
}
.STYLE1 A:active,.STYLE1 A:visited,.STYLE1 A:link {
	COLOR: white;
	TEXT-DECORATION: none
}
.STYLE1 a:hover{
	COLOR: red;
}
-->
</style>
</head>
<body>
<sf:form modelAttribute="option" method="post" action="${pageContext.request.contextPath}/admin/option/edit"> 
<div style="background-color:#353C44" width="100%" style="padding-top:10px"><span class="STYLE1">网站设置</span></div>
<table>
<tr>
  
  </tr>
	<tr>
		<td>网站名称</td>
		<td><sf:input path="siteName"/><sf:errors path="siteName"/></td>
	</tr>
	<tr>
		<td>允许附件上传类型</td>
		<td width="300"><textarea name="uploadType" cols="20" rows="10">${option.uploadType}</textarea></td>
		<td>
			<font color=red>1以;号分隔<br>
							2必须是文件类型的contentType,而不是后缀	
			</font>
		</td>
	</tr>
	<tr>
	<td><input type="submit" value="保存"></td>
	<td><input type="reset" value="重置"></td>
	</tr>
</table>
</sf:form>
</body>
</html>

