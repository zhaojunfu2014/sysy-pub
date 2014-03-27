<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Random"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>CMS 后台管理工作平台</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/admin/css/style.css"/>
	<script type="text/javascript">
		var checkcodeUrl;
		function login_submit(){
			document.getElementById("login").submit();
		}
		function login_reset(){
			document.getElementById("login").reset();
		}
		function reloadcheckcode(img,context){
				context+="/";
				context	+= "checkcode.jpg";
				context+="?"+Math.random();
				img.src = context;
		}
	</script>
</head>
<body>
<div id="top"> </div>
<form id="login" action="<%=request.getContextPath() %>/login" method="post">
  <div id="center">
    <div id="center_left"></div>
    <div id="center_middle">
      <div class="user">
        <label>用户名：
        <input type="text" name="username" id="user" /><span><font color=red>${usermessage}</font></span>
        </label>
      </div>
      <div class="user">
        <label>密　码：
        <input type="password" name="password" id="pwd" /><span><font color=red>${passmessage}</font></span>
        </label>
      </div>
      <div class="chknumber">
        <label>验证码：
        <input type="text" name="checkcode1"  maxlength="4" class="chknumber_input"/>
        <img src="<%=request.getContextPath()%>/checkcode.jpg?<%=new Date()%>" onclick="javascript:reloadcheckcode(this,'<%=request.getContextPath()%>');"/> 
        <span><font color=red>${codemessage}</font></span></label>
      </div>
    </div>
    <div id="center_middle_right"></div>
    <div id="center_submit">
      <div class="button"> <img src="<%=request.getContextPath() %>/resources/admin/images/dl.gif" width="57" height="20" onclick="login_submit()" > </div>
      <div class="button"> <img src="<%=request.getContextPath() %>/resources/admin/images/cz.gif" width="57" height="20" onclick="login_reset()"> </div>
    </div>
    <div id="center_right"></div>
  </div>
</form>
</body>
</html>

