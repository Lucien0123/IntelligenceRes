<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${ctx}/images/titleicon.ico" type="image/x-icon" rel="shortcut icon" />
<title>罗莱家纺LOVO</title>
<link href="${ctx}/zui/css/zui.css" rel="stylesheet" />
<link href="${ctx}/zui/css/zui-theme.css" rel="stylesheet" />
<link href="${ctx}/css/my.css" rel="stylesheet">
<!--[if lt IE 9]>
<script src="${ctx}/zui/lib/ieonly/html5shiv.js"></script>
<script src="${ctx}/zui/lib/ieonly/respond.js"></script>
<script src="${ctx}/zui/lib/ieonly/excanvas.js"></script>
<![endif]-->
<style>
#topDiv {
	width: 100%;
	height: auto;
	background-color: #fff;
}

#downDiv {
	width: 100%;
	height: auto;
	background-color: #588499;
}

#slidebar {
	position: fixed;
	right: 0;
	bottom: 30%;
	background-color: #fff;
	z-index: 99;
}
</style>
</head>
<body>
<!--[if lt IE 8]>
<div class="alert alert-danger">您正在使用 <strong>过时的</strong> 浏览器. 是时候 <a href="http://browsehappy.com/">更换一个更好的浏览器</a> 来提升用户体验.</div>
<![endif]-->
	<div id="topDiv">
		<jsp:include page="./user/commontopuser.jsp" />
	</div>
	
	<!-- 商品列表 -->
    <div class="wrapper" style="min-height: 500px">

		<!-- 列表开始 -->
		<div class="list">
	        <section class="cards cards-borderless cards-products row">
	        <c:forEach items="${productList }" var="prod">
	          <div class="col-xs-2">
	            <div class="card" >
	              <div style="height: 294px; line-height: 294px; background-color: #ddf4df">
	              <a href="${ctx}/product/productdetail?productid=${prod.id}" target="_blank"><img src="${pic_base}${prod.thumbnail}" alt=""/></a>
	              </div>
	              <div class="card-heading" style="background-color: #ddf4df">
	                <span class="pull-right price"><fmt:formatNumber value="${prod.saleprice}" pattern="￥#,##0.00"/> </span>
	                <a href="${ctx}/product/productdetail?productid=${prod.id}" target="_blank">${prod.name}</a>
	              </div>
	            </div>
	          </div>
	        </c:forEach>
	        </section>
	     </div>
		<!-- /列表结束 -->
    </div>    
    <!-- /商品列表 -->
	
	<div id="downDiv" align="center">
		<jsp:include page="./commonbottompage.jsp" />
	</div>
	<script src="${ctx}/zui/lib/jquery/jquery.js"></script>
	<script src="${ctx}/zui/js/zui.js"></script>
	<script src="${ctx}/js/my.js"></script>

</body>
</html>