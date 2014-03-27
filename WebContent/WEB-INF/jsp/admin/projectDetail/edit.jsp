<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<!-- editor -->
<script src="<%=request.getContextPath() %>/resources/kindeditor/kindeditor-min.js"></script>
<script  src="<%=request.getContextPath() %>/resources/kindeditor/en.js"></script>
		<script>
		
			var editor;
			KindEditor.ready(function(K) {
				editor = K.create('textarea[name="info"]', {
					resizeType : 1,
					allowPreviewEmoticons : false,
					allowImageUpload : false
				});
			});
			function insertAttached(src,type){
			
				if(type.search("image/")!=-1){
					var html = "<img src='"+src+"'/>";
				}else{
					var html = "<a href='"+src+"'>【附件：编辑附件名称】</a>";
				}
				
				editor.insertHtml(html);
			}
		</script>
<!-- end of editor -->
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
	height:24px
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
<sf:form modelAttribute="detail" method="post" action="${pageContext.request.contextPath}/admin/project/detail/edit/${detail.id}"> 
<div style="background-color:#353C44" width="100%" style="padding-top:10px"><span class="STYLE1">详细内容</span></div>
<table>
<tr>
  
  </tr>
	<tr>
		<td>描述</td>
		<td><sf:input path="detail"/><sf:errors path="detail"/></td>
		
	</tr>
	<tr>
		<td>具体内容</td>
		<td><textarea  name="info"  style="width:750px;height:300px;visibility:hidden;">${detail.info}</textarea></td>
	</tr>
	
	<tr>
					<td>
						附件
					</td>
					<td>
						<iframe
							src="${pageContext.request.contextPath}/admin/attachment/add"
							style="border: 0pt" width="90%"></iframe>
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

