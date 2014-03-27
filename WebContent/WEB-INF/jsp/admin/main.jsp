<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>后台管理工作平台</title>
</head>
<frameset rows="127,*,11" frameborder="no" border="0" framespacing="0">
	<frame src="<%=request.getContextPath() %>/admin/top" name="topFrame" scrolling="No"
		noresize="noresize" id="topFrame" />
	<frame src="<%=request.getContextPath() %>/admin/center" name="mainFrame" id="mainFrame" />
	<frame src="<%=request.getContextPath() %>/admin/foot" name="bottomFrame" scrolling="No"
		noresize="noresize" id="bottomFrame" />
</frameset>
<noframes>
<body>
</body>
</noframes>
</html>

