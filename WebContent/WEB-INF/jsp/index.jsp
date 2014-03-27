<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.zjf.cms.util.ConfigUtil"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp" %>
<% %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=new ConfigUtil().getValue("siteName") %></title>
<link href="<%=request.getContextPath() %>/resources/css/css.css" rel="stylesheet" type="text/css" />
</head>

<body style="background-color: gray;"> 

<%@ include file="/top.jsp" %>
<div class="main clearfix">
	<div class="col-left l">
    	<!--  <div class="common1 tzgg" >
        	<div class="top"><div class="bor"><div class="l">通知公告</div><span class="r"><a href="${pageContext.request.contextPath}/list/3">更多>></a></span></div></div>
            
            <div class="con clearfix" style="height:280px;">
            	<ul>
	            	<c:forEach items="${article3}" var="article"  begin="0" end="10">
	            	<c:if test="${article.visible==true}">
	               	 <li><a href="${pageContext.request.contextPath}/detail/${article.id }">${fn:substring(article.title, 0, 10)}</a><span><fmt:formatDate value="${article.pubDate}" type="date" dateStyle="long"/></span></li>
	               	</c:if>
	               	 </c:forEach>
                </ul>
            </div>
        </div>
        <div class="hack10"></div>-->
    	<div class="common1 flfg" >
        	<div class="top"><div class="bor"><div class="l">入网企业</div><span class="r"><a href="${pageContext.request.contextPath}/list/10">更多>></a></span></div></div>
            <div class="con" style="height:280px;">
            	<ul>
            	<c:forEach items="${article10}" var="article" begin="0" end="10">
            	<c:if test="${article.visible==true}">
               	 <li><a href="${pageContext.request.contextPath}/detail/${article.id }">${fn:substring(article.title, 0, 10)}</a><span><fmt:formatDate value="${article.pubDate}" type="date" dateStyle="long"/></span></li>
              	</c:if>
                </c:forEach>
                </ul>            
            </div>
        </div>
        <div class="hack10"></div>
    	<div class="common1 flfg" >
        	<div class="top"><div class="bor"><div class="l">召回信息</div><span class="r"><a href="${pageContext.request.contextPath}/list/5">更多>></a></span></div></div>
            <div class="con" style="height:280px;">
            	<ul>
            	<c:forEach items="${article5}" var="article" begin="0" end="10">
            	<c:if test="${article.visible==true}">
               	 <li><a href="${pageContext.request.contextPath}/detail/${article.id }">${fn:substring(article.title, 0, 10)}</a><span><fmt:formatDate value="${article.pubDate}" type="date" dateStyle="long"/></span></li>
              	</c:if>
                </c:forEach>
                </ul>            
            </div>
        </div>
    </div>
	<div class="col-center l">
    	<!--  <div class="common2 xmdt">
        	<div class="top"><div class="titlebg"><span>召回信息</span></div><span class="more r"><a href="${pageContext.request.contextPath}/list/5">更多>></a></span></div>
            <div class="con">
            	<ul>
            	<c:forEach items="${article5}" var="article" begin="0" end="10">
            	<c:if test="${article.visible==true}">
                <li><a href="${pageContext.request.contextPath}/detail/${article.id }">${fn:substring(article.title, 0, 10)}</a><span><fmt:formatDate value="${article.pubDate}" type="date" dateStyle="long"/></span></li>
               </c:if>
                </c:forEach>
                </ul>
            </div>
        </div>
        <div class="hack10"></div>-->
        <div class="common2 xmdt">
        	<div class="top"><div class="titlebg"><span>溯源查询</span></div></div>
            <div class="con" style="">
            <table>
            	<tr>
            	<td>产品条码</td>
            	<td><input type="text" name="pno"  style="width:95%;"/></td>
            	<td>批次号</td>
            	<td><input type="text" name="bno" style="width:95%;"/></td>
            	<td><img onclick="traceBypb()" src="${pageContext.request.contextPath}/resources/images/so.png"></img></td>
            	</tr>
            	<tr>
            	<td>溯源码</td>
            	<td colspan="3"><input type="text" name="pno"  style="width:98%;"/></td>
            	<td ><img onclick="traceBysy()" src="${pageContext.request.contextPath}/resources/images/so.png"></img></td>
            	</tr>
            </table>
            <img style="margin-top: 20px;" src="${pageContext.request.contextPath}/resources/images/suyuanshili.png"></img>
            <div id="reportDiv" style="border:1px solid ;margin-top: 20px;">
			</div>
			<div id="reportTp" style="display: none;">
			<div style="background:gray;color: white; ">产品基本信息</div>
			<table>
				<tr>
			       	<td>生产企业:</td>
			       	<td>:ep_name</td>
			    </tr>
				<tr>
			       	<td>产品名称:</td>
			       	<td>:prd_name</td>
			    </tr>
			    <tr>
			       	<td>产品编号:</td>
			       	<td>:prd_no</td>
			    </tr>
			    <tr>
			       	<td>产品批次号:</td>
			       	<td>:prd_batch</td>
			    </tr>
			    <tr>
			       	<td>产品生产许可证:</td>
			       	<td>:prd_access</td>
			    </tr>
			    <tr>
			       	<td>产品生产日期:</td>
			       	<td>:prd_date</td>
			    </tr>
			    <tr>
			       	<td>产品保质期:</td>
			       	<td>:prd_life</td>
			    </tr>
			    <tr>
			       	<td>检验员:</td>
			       	<td>:checker</td>
			    </tr>
			    <tr>
			       	<td>出厂检验方式:</td>
			       	<td>:check_way</td>
			    </tr>
			    <tr>
			       	<td>出厂检验情况:</td>
			       	<td>:check_content</td>
			    </tr>
			    <tr>
			       	<td>检验报告:</td>
			       	<td>:check_report</td>
			    </tr>
			</table>
			<div style="background:gray;color: white; ">原料</div>
			<div id="yl">:yl</div>
			</div>
            </div>
        </div>
        <div class="hack10"></div>
    	<div class="common2 jljdtb" >
        	<div class="top"><div class="titlebg"><span>监督抽查</span></div><span class="more r"><a href="${pageContext.request.contextPath}/list/9">更多>></a></span></div>
            <div class="con" style="height:270px;">
            	<ul>
            	<c:forEach items="${article9}" var="article" begin="0" end="10">
            	<c:if test="${article.visible==true}">
                <li><a href="${pageContext.request.contextPath}/detail/${article.id }">${fn:substring(article.title, 0, 10)}</a><span><fmt:formatDate value="${article.pubDate}" type="date" dateStyle="long"/></span></li>
               </c:if>
                </c:forEach>
                </ul>
            </div>
        </div>
    </div>
	<div class="col-right l">
    	<div class="common1 bszn" >
        	<div class="top"><div class="bor"><div class="l">政策法规</div><span class="more r"><a href="${pageContext.request.contextPath}/list/6">+更多</a></span></div></div>
            <div class="con" style="height:280px;">
            	<ul>
            	<c:forEach items="${article6}" var="article" begin="0" end="5">
            	<c:if test="${article.visible==true}">
                	<li><div style="padding-left:60px;padding-top:10px;height:100px;background-image:url(<%=request.getContextPath()%>/resources/images/lbtn1.png);background-repeat:no-repeat"><a href="${pageContext.request.contextPath}/detail/${article.id }">${fn:substring(article.title, 0, 10)}</a></div></li>
               	</c:if>
                </c:forEach>
                </ul>
            </div>
        </div>
        <div class="hack10"></div>
    	<div class="common1 zlxz" >
        	<div class="top"><div class="bor"><div class="l">资料下载</div><span class="more r"><a href="${pageContext.request.contextPath}/list/8">+更多</a></span></div></div>
            <div class="con" style="height:280px;" >
            	<ul>
            	<c:forEach items="${article8}" var="article" begin="0" end="10">
            	<c:if test="${article.visible==true}">
                <li><a href="${pageContext.request.contextPath}/detail/${article.id }">${fn:substring(article.title, 0, 10)}</a><span><fmt:formatDate value="${article.pubDate}" type="date" dateStyle="long"/></span></li>
               </c:if>
                </c:forEach>
                </ul> 
            </div>
        </div>
    </div>
</div>
<div class="hack10"></div>

<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>
<script type="text/javascript">
function traceBypb(){
	 var pno = $("[name='pno']").val();
	 var bno = $("[name='bno']").val();
	 if(!pno||!bno){
		 alert('请输入产品编码和批次号');
		 return;
	 }
	 $.ajax({
			url : '../sysy-system/traceController.do?traceQuery',
			type : 'post',
			data : {
				'pno':pno,
				'bno':bno
			},
			cache : false,
			success : function(data) {
				var d = $.parseJSON(data);
				var count = d.count;
				if(count==0){
					alert("没有查询到相关产品");
					return;
				}
				var prdMap = d.prdMap;
				var prdMaterial = d.prdMaterial;
				//alert(JSON.stringify(d));
				var tp = $("#reportTp").html();
				for(var x in prdMap){
					tp = tp.replace(":"+x,prdMap[x]);
				}
				var yl = "";
				for(var i =0;i<prdMaterial.length;i++){
					var m_no = prdMaterial[i]['m_no'];
					var m_batch = prdMaterial[i]['m_batch'];
					var m_name = prdMaterial[i]['m_name'];
					var msize = prdMaterial[i]['msize'];
					yl += m_name+msize+"<br>";
				}
				tp = tp.replace(":yl",yl);
				$("#reportDiv").html(tp);
				
			}
		});
	 return false;
}
function traceBysy(){
	 var sno= $("[name='sno']").val();
	 return false;
}
</script>