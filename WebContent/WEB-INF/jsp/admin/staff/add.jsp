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
<sf:form  method="post" modelAttribute="staff"> 
<div style="background-color:#353C44" width="100%" style="padding-top:10px"><span class="STYLE1">添加新员工</span></div>
<table>
<tr>
  
  </tr>
	<tr>
		<td>用户名</td>
		<td><sf:input path="username" /><sf:errors path="username"/><span>${usermessage}</span></td>
		
	</tr>
	<tr>
		<td>密码</td>
		<td><input name="password" type="password" ><sf:errors path="password"/></td>
	</tr>
	<tr>
		<td>权限</td>
		<td><sf:textarea rows="5" cols="40" path="privilege" /><sf:errors path="privilege"/></td>
	</tr>
	<tr>
	<td><font color=red>详细说明</font></td>
	<td>权限说明：通过该设置可以给网站管理员分配相应的权限；<br>
			具体方法为给用户分配访问路径，比如后台的路径为/admin/.. 。只要给用户分配/admin/* 用户就拥有了所有的权限<br>
			<b><font color=blue>具体权限示例：(多个权限中间用分号 ; 隔开)<br></font></b>
			所有权限&nbsp;/admin/* <b>(一般情况下默认使用这个权限就可以了，对权限有特殊需求的看以下内容)</b><br>
			进入后台权限&nbsp;/admin/main;/admin/top;/admin/left;/admin/right;/admin/center<br>
			查看菜单&nbsp;/admin/menu/list&nbsp;&nbsp;
			增加菜单&nbsp;/admin/menu/add&nbsp;&nbsp;
			编辑菜单&nbsp;/admin/menu/edit/*&nbsp;&nbsp;
			删除菜单&nbsp;/admin/menu/delete/*<br>
			查看文章&nbsp;/admin/article/list&nbsp;&nbsp;
			发布文章&nbsp;/admin/article/pub&nbsp;&nbsp;
			修改文章&nbsp;/admin/article/edit/*&nbsp;&nbsp;
			删除文章&nbsp;/admin/article/delete/*<br>
			查看附件&nbsp;/admin/attachment/list&nbsp;&nbsp;
			上传附件&nbsp;/admin/attachment/add&nbsp;&nbsp;
			删除附件&nbsp;/admin/attachment/delete/*<br>
			查看员工&nbsp;/admin/staff/list&nbsp;&nbsp;
			增加员工&nbsp;/admin/staff/add&nbsp;&nbsp;
			编辑员工&nbsp;/admin/staff/edit/*&nbsp;&nbsp;
			删除员工&nbsp;/admin/staff/delete/*<br>
			网站配置&nbsp;/admin/option/list<br>
			<b><font color=blue>！except:用法<br></font></b>
			例  /admin/article/*!except:add,edit<br>
			表示用户拥有目录下除开!except后的权限<br>
			即 拥有文章的除开add和edit的权限：delete,list；<br>
			!except必须放在*后，含有!except的权限必须放在权限集的最前面<br>
			例 /admin/article/*!except:add,delete;/admin/*<br>
			意味着用户拥有/admin/*下所有权限，但/admin/article/add和/admin/article/delete是例外！<br>
		</td>
	</tr>
	<tr>
	<td><input type="submit" value="添加"></td>
	<td><input type="reset" value="重置"></td>
	</tr>
	
</table>
</sf:form>
</body>
</html>

