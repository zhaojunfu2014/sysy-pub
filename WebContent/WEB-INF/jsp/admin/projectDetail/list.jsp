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
function confirm_delete(id,infoid,type){
	var r=confirm("确认删除吗？");
	if (r==true)
	  {
	  	window.location.href="../delete/"+id+"/"+infoid+"/"+type;
	  }
	else
	  {
	  }
	
}
//跳转到用户选定的菜单
function selectMenu(){
	var index = window.document.getElementById("menuId").selectedIndex; 
    var val = window.document.getElementById("menuId").options[index].value;
    //alert(val);
    window.location.href="listMenu/"+val;
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
                <td width="94%" valign="bottom"><span class="STYLE1">${info.code}-${info.name}项目明细</span>-<a href="<%=request.getContextPath()%>/admin/project/list"><font color=red>返回</font></a></td>
              </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
  	<td>
  	<sf:form  method="post" modelAttribute="projectDetail" action="${pageContext.request.contextPath}/admin/project/detail/add/${info.id}/${type}"> 
  		<table bgcolor="#a8c7ce">
  		<tr>
  			<td>
  			描述
  			</td>
  			<td>
  			操作
  			</td>
  		</tr>
  		<tr>
  			<td width="500">
  			<input type="text" size="100" name="detail">
  			</td>
  			<td>
  			<input type="submit" value="添加">
  			<input type="reset" value="重置">
  			</td>
  		</tr>
  		</table>
  	</sf:form>
  	</td>
  </tr>
  <tr>
    <td>
   	
    <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
      <tr>
      	<td width="50" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">描述</span></div></td>
        <td width="50" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">是否完成</span></div></td>
        <td width="50" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">更新时间</span></div></td>
        <td width="50" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">操作</span></div></td>
      </tr>
      <c:forEach items="${datas}" var="data">
      <tr>
      		<td style= "word-break:break-all; " width="470" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${data.detail}</div></td>
	        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><c:if test="${data.finish==false}"><font color=red>未完成</font></c:if><c:if test="${data.finish==true}"><font color=green>已完成</font></c:if></div></td>
	        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${data.updateDate}</div></td>
	        <td height="20" bgcolor="#FFFFFF"><div align="center" class="STYLE21">
	        <a href="${pageContext.request.contextPath}/admin/project/detail/edit/${data.id}"  target="_blank">详细</a> |
	        <a href="${pageContext.request.contextPath}/admin/project/detail/finish/${data.id}/${info.id}/${type}" >标记为完成</a> |
	        <a href="${pageContext.request.contextPath}/admin/project/detail/unfinish/${data.id}/${info.id}/${type}" >标记为未完成</a> |
	        <c:if test="${data.visible!=false}">
	        <a href="${pageContext.request.contextPath}/admin/project/detail/hide/${data.id}/${info.id}/${type}" >隐藏</a> |
	       </c:if>
	       <c:if test="${data.visible==false}">
	        <a href="${pageContext.request.contextPath}/admin/project/detail/show/${data.id}/${info.id}/${type}" >显示</a> |
	      </c:if>
	        <a href="javascript:confirm_delete(${data.id},${info.id},${type});">删除</a>
	        </div></td>
       
      </tr>
       </c:forEach>
       
    </table></td>
  </tr>

</table>
</body>
</html>

