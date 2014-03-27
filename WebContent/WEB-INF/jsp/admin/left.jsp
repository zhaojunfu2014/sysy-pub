<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.zjf.cms.entity.Staff"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/admin/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-ui-1.8.custom.min.js"></script>
<script language="javascript">
	jQuery().ready(function(){
		jQuery('#navigation').accordion({
			header: '.head',
			event: 'click',
			fillSpace: true,
			animated: 'bounceslide'
		});
	});
</script>
<style type="text/css">
<!--
body {
	margin:0px;
	padding:0px;
	font-size: 12px;
}
#navigation {
	margin:0px;
	padding:0px;
	width:147px;
}
#navigation a.head {
	cursor:pointer;
	background:url(images/main_34.gif) no-repeat scroll;
	display:block;
	font-weight:bold;
	margin:0px;
	padding:5px 0 5px;
	text-align:center;
	font-size:12px;
	text-decoration:none;
}
#navigation ul {
	border-width:0px;
	margin:0px;
	padding:0px;
	text-indent:0px;
}
#navigation li {
	list-style:none; display:inline;
}
#navigation li li a {
	display:block;
	font-size:12px;
	text-decoration: none;
	text-align:center;
	padding:3px;
}
#navigation li li a:hover {
	background:url(images/tab_bg.gif) repeat-x;
		border:solid 1px #adb9c2;
}
-->
</style>
</head>
<body>
<div  style="height:100%;">
  <ul id="navigation">
    <li> <a class="head">内容管理</a>
      <ul>
        <li><a href="<%=request.getContextPath()%>/admin/menu/list" target="rightFrame">菜单管理</a></li>
	        <!--  <li></li>
	      	<li><a href="<%=request.getContextPath()%>/admin/article/pub" target="rightFrame">添加文章</a></li>
	      	<li><a href="<%=request.getContextPath()%>/admin/staff/add" target="rightFrame">添加员工</a></li>
	      	-->
      	<li><a href="<%=request.getContextPath()%>/admin/article/list" target="rightFrame">文章管理</a></li>
      	<li><a href="<%=request.getContextPath()%>/admin/attachment/list" target="rightFrame">附件管理</a></li>
     	<li><a href="<%=request.getContextPath()%>/admin/staff/list" target="rightFrame">员工管理</a></li>
      </ul>
    </li>
    <!--  
    <li> <a class="head">项目监督情况</a>
      <ul>
        <li><a href="<%=request.getContextPath()%>/admin/project/list" target="rightFrame">项目列表</a></li>
        <li><a href="<%=request.getContextPath()%>/admin/project/add" target="rightFrame">添加项目</a></li>
      </ul>
    </li>
    -->
    <li> <a class="head">系统管理</a>
      <ul>
        <li><a href="<%=request.getContextPath()%>/admin/staff/edit/<%=((Staff)request.getSession().getAttribute("loginUser")).getId()%>" target="rightFrame">修改密码</a></li>
        <li><a href="<%=request.getContextPath()%>/admin/option/list" target="rightFrame">网站设置</a></li>
      </ul>
    </li>
     
  </ul>
</div>
</body>
</html>

