<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<script
	src="<%=request.getContextPath() %>/resources/admin/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
//鼠标移到菜单上时显示子菜单
function showMenuItem(menuId){
	$("#mc"+menuId).attr("style","display:block;padding: 5px 5px 5px 5px;width:500px;height:50px;background-color:#03559E;position:fixed");
	
}
function hideMenuItem(menuId){
	$("#mc"+menuId).attr("style","display:none");
}


</script>
<script src="<%=request.getContextPath() %>/resources/Scripts/swfobject_modified.js" type="text/javascript"></script>
<div class="div">
	<!--  
	<div class="topnav">
		<div class="datetime l">
			<iframe name="weather_inc" class="weather r"
				src="http://tianqi.xixik.com/cframe/1" width="200" height="35"
				frameborder="0" marginwidth="0" marginheight="0" scrolling="no"
				allowtransparency=true></iframe>
			<p>
				<fmt:formatDate value="${isoDate}" type="date" dateStyle="full" />
			</p>
		</div>
		<form name="searchForm"
			action="${pageContext.request.contextPath}/search" method="post">
			<div class="topsearch l txtc">
				<label>
					站内搜索：
				</label>
				<input name="search" type="text" />
				<input onclick="javascript:searchForm.submit();" type="image"
					src="<%=request.getContextPath() %>/resources/images/so.png" />
			</div>
			<div class="toplink l txtr">
				<a href="#">设为首页</a> |
				<a href="#">加入收藏</a>
			</div>
		</form>
	</div>
	-->
	<div class="banner" style="background-image: url('<%=request.getContextPath() %>/resources/images/banner.jpg');">
	</div>
	<div class="menu-box" align="center">
		<!-- menu1 start -->
		<div id="menu0" class="menusel">
			<h2><a href="${pageContext.request.contextPath}/"><b>首　页</b></a></h2>
		</div>
		<div id="menu0" class="menusel">
			<h2><a href="${pageContext.request.contextPath}/trace"><b>溯源查询</b></a></h2>
		</div>
		<!-- menu1 end -->
		<c:forEach items="${menus}" var="menu" varStatus="i">
			<c:if test="${menu.visible==true }">
			<!-- menu2 start -->
			<div id="menu${i.count}" class="menusel">
				<h2><a href="${pageContext.request.contextPath}/list/${menu.id}"><b>${menu.name }</b></a></h2>
				<c:if test="${fn:length(menu.childs)>0}">
				<div>
					<ul>
					<c:forEach items="${menu.childs}" var="mc">
						<li><a href="${pageContext.request.contextPath}/list/${mc.id}">${mc.name }</a></li>
					</c:forEach>	
					</ul>
				</div>
				</c:if>
			</div>
			<!-- menu2 end -->
			</c:if>
		</c:forEach>
		
	
		
	</div>

<script type="text/javascript">
for(var x = 0; x < 9; x++)
{
	var menuid = document.getElementById("menu"+x);
	if(menuid.getElementsByTagName("div").length==0){}
	else{type();}
}
function type()
{
	var menuh2 = menuid.getElementsByTagName("h2");
	var divs = menuid.getElementsByTagName("div");
	var alluls = divs[0].getElementsByTagName("ul");
	var menuli = alluls[0].getElementsByTagName("li");
	menuh2[0].onmouseover = show;
	menuh2[0].onmouseout = unshow;
	alluls[0].onmouseover = show;
	alluls[0].onmouseout = unshow;
	
	function show()
	{
	alluls[0].className = "clearfix block"
	/*menuul[0].lastChild.className = "lli";*/
	function getChildRen(x) 
	{ 
		var arry = new Array; 
		var c = x.childNodes;
		for (var i = 0; i < c.length; i++) 
			{
				if (c[i].nodeType == 1) 
				{ 
					arry.push(c[i]) 
				} 
			} 
		return arry; 
	}
	
	for(var i = 0; i < alluls.length; i++)
	{
		var ok = getChildRen(alluls[i]);
		ok[ok.length-1].className = "lli";
	}
	/*alert(alluls[0].offsetHeight)*/
	menuh2[0].className = "h2hover"
	}
	
	function unshow()
	{
	alluls[0].className = ""
	menuh2[0].className = ""
	}
	
	for(var i = 0; i < menuli.length; i++)
	 {
		 menuli[i].num = i;
		 var liul = menuli[i].getElementsByTagName("ul")[0];
			 if(liul)
			 {
				 typeshow()
			 }
	 }
	
	function typeshow()
	{
		menuli[i].onmouseover = showul;
		menuli[i].onmouseout = unshowul;
	}
	
	function showul()
	{
		menuli[this.num].getElementsByTagName("ul")[0].className = "block";
		menuli[this.num].getElementsByTagName("ul")[0].lastChild.className ="lli";
	}
	
	function unshowul()
	{
		menuli[this.num].getElementsByTagName("ul")[0].className = "";
	}
}
</script>
