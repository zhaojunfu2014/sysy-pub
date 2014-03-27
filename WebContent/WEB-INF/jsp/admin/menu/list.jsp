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
<script type="text/javascript">
function confirm_delete(id){
	var r=confirm("确认删除吗？");
	if (r==true)
	  {
	  	window.location.href="delete/"+id;
	  }
	else
	  {
	  }
	
}
</script>
</head>
<body>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="94%" valign="bottom"><span class="STYLE1"> 菜单列表</span></td>
              </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
  	<td>
  	<a href="<%=request.getContextPath()%>/admin/menu/add"><font size="4">添加新菜单</font></a>
  	</td>
  </tr>
  <tr>
    <td>
    <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
      <tr>
      	<td width="50" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">菜单编号</span></div></td>
        <td width="50" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">菜单名称</span></div></td>
        <td width="50" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">菜单等级</span></div></td>
        <td width="50" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">是否显示</span></div></td>
        <td width="100" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">基本操作</span></div></td>
      </tr>
      <c:forEach items="${menus}" var="menu">
      <tr>
      		 <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${menu.id}</div></td>
	        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${menu.name}</div></td>
	        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${menu.level}</div></td>
	        <td bgcolor="#FFFFFF"><center>
	        <c:if test="${menu.visible == true}">显示</c:if>
	        <c:if test="${menu.visible!= true}">隐藏</c:if>
	        </center>
	        </td>
	        <td height="20" bgcolor="#FFFFFF"><div align="center" class="STYLE21">
	        <c:if test="${menu.visible == true}"><a href="<%=request.getContextPath() %>/admin/menu/hide/${menu.id}" >隐藏</a></c:if>
	        <c:if test="${menu.visible!= true}"><a href="<%=request.getContextPath() %>/admin/menu/show/${menu.id}">显示</a></c:if>
	        | 
	        <a href="<%=request.getContextPath() %>/admin/menu/add/${menu.id}" >添加子菜单</a> |
	        （有${fn:length(menu.childs)}个子菜单）<a href="<%=request.getContextPath() %>/admin/menu/sub/${menu.id}" >查看</a> |
	        <a href="<%=request.getContextPath() %>/admin/menu/edit/${menu.id}" >编辑</a> |
	        <!--  <a href="#" onclick="confirm_delete(${menu.id})" >删除</a>-->
	        </div></td>
       
      </tr>
       </c:forEach>
    </table></td>
  </tr>
  
</table>
</body>
</html>

