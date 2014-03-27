<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<!-- editor -->
		<script
			src="<%=request.getContextPath() %>/resources/kindeditor/kindeditor-min.js"></script>
		<script
			src="<%=request.getContextPath() %>/resources/kindeditor/en.js"></script>
		<script
			src="<%=request.getContextPath() %>/resources/admin/js/jquery-1.4.2.min.js"></script>
		<script
			src="<%=request.getContextPath() %>/resources/admin/js/jquery.json-2.2.min.js"></script>
		<script>
			
			var editor;
			KindEditor.ready(function(K) {
				editor = K.create('textarea[name="content"]', {
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
			function init(){
				$.fn.serializeObject = function() {  
					var o = {}; 
					var a = this.serializeArray();  
					$.each(a, function() { 
					//alert(this.name);
					if (o[this.name]) {  
				      if (!o[this.name].push) {  
				        o[this.name] = [ o[this.name] ];  
				      }  
				      o[this.name].push(this.value || '');  
				    } else {  
				      o[this.name] = this.value || editor.html();  
				    }  
				  });  
				   return o;  
				}; 
				checkTemp();
				tempThread();
			}
			function tempAdd() {  
		        var jsonuserinfo = $.toJSON($('#aform').serializeObject());  
		        //alert(jsonuserinfo);  
		        jQuery.ajax( {  
		          type : 'POST',  
		          contentType : 'application/json',  
		          url : 'temp/add',  
		          data : jsonuserinfo,  
		          dataType : 'json',  
		          success : function(data) {  
		            //alert("新增成功！");  
		            $("#tempArticle").html(data.success);
		          },  
		          error : function(data) {  
		           //alert("error")  
		          }  
		        });  
		     }
			function tempThread(){
				setInterval("tempAdd()",120000);
			}
			function checkTemp(){
			  jQuery.ajax( {  
		        type : 'GET',  
		        contentType : 'application/json',  
		        url : 'temp/fetch?'+Math.random(),  
		        dataType : 'json',  
		        success : function(data) {  
		        	if(data.hasTemp==true){
			        	 var r=confirm("检测到上次有未发表的数据，继续上一次的内容吗？");
			        	 if (r==true)
						 {
						 	$('#title').attr("value",data.title);
						 	editor.html(data.content);
						 }
						else
						 {
						 }
					}
		        },  
		        error : function() {  
		        }  
		      }); 
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

.STYLE6 {
	color: #000000;
	font-size: 12;
}

.STYLE10 {
	color: #000000;
	font-size: 12px;
}

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

tr {
	border: 1pt;
}

table {
	border: 1pt solid;
}

.STYLE1 a {
	COLOR: white;
}

.STYLE1 A:active,.STYLE1 A:visited,.STYLE1 A:link {
	COLOR: white;
	TEXT-DECORATION: none
}

.STYLE1 a:hover {
	COLOR: red;
}
-->
</style>
		<script type="text/javascript">

</script>
	</head>
	<body onload="init();">
		<sf:form id="aform" modelAttribute="article" method="post"
			action="${pageContext.request.contextPath}/admin/article/pub">
			<div style="background-color: #353C44" width="100%"
				style="padding-top:10px">
				<span class="STYLE1">发表文章</span>
			</div>
			<table width="100%">
				<tr>


				</tr>
				<tr>
					<td>
						文章标题*
					</td>
					<td>
						<input type="text" name="title" id="title">
						<sf:errors path="title" />
					</td>
				</tr>
				<tr>
					<td>
						所属菜单*
					</td>
					<td>
						<select id="menuId" name="menuId">
							<c:forEach items="${menus}" var="m">
								<c:if test="${m.id==id}">
									<option value="${m.id}" selected="selected">
										${m.name}
									</option>
								</c:if>
								<c:if test="${m.id!=id}">
									<option value="${m.id}">
										${m.name}
									</option>
								</c:if>

								<c:forEach items="${m.childs}" var="mc">
									<c:if test="${mc.id==id}">
										<option value="${mc.id}" selected="selected">
											---${mc.name}
										</option>
									</c:if>
									<c:if test="${mc.id!=id}">
										<option value="${mc.id}">
											---${mc.name}
										</option>
									</c:if>
								</c:forEach>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						文章内容
					</td>
					<td>
						<textarea name="content" id="content"
							style="width: 750px; height: 300px; visibility: hidden;"></textarea>
						<sf:errors path="content" />
					</td>
				</tr>
				<tr>
					<td>
						缓存
					</td>
					<td>
						<div id="tempArticle">
							
						</div>
					</td>
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
					<td>
						<input type="submit" value="保存">
					</td>
					<td>
						<input type="reset" value="重置">
					</td>
				</tr>
			</table>
		</sf:form>
	</body>
</html>

