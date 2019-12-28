<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${ctx}/images/titleicon.ico" type="image/x-icon"
	rel="shortcut icon" />
<title>罗莱家纺LOVO</title>
<link href="${ctx}/zui/css/zui.css" rel="stylesheet">
<link href="${ctx}/zui/css/zui-theme.css" rel="stylesheet">
<link href="${ctx}/css/my.css" rel="stylesheet">

<!--[if lt IE 9]>
<script src="zui/lib/ieonly/html5shiv.js"></script>
<script src="zui/lib/ieonly/respond.js"></script>
<script src="zui/lib/ieonly/excanvas.js"></script>
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
	<!-- 主内容 -->
	<div class="wrapper" style="min-height: 500px;">
		<div class="panel" style="margin-top: 20px">
			<div class="panel-heading" style="font-size: 18px">
				<strong>我的购物车</strong>
			</div>
			<form action="${ctx}/order/orderlist" method="post">
				<div class="panel-body">
					<table class="table">
						<thead>
							<tr class="text-center">
								<td colspan="2" class="text-left">商品信息</td>
								<td class="text-left">价格</td>
								<td>数量</td>
								<td>金额</td>
								<td>操作</td>
							</tr>
						</thead>
						<tbody id="cart-list">
							<c:forEach items="${sessionScope.cart}" var="entry">
								<tr>
									<td style="width: 100px"><a
										href="${ctx}/product/productdetail?id=${entry.key.id}"><img
											src="${ctx }/img/${entry.key.thumbnail}" title="" alt="" /></a></td>
									<td class="text-left"><a
										href="${ctx}/product/productdetail?id=${entry.key.id}"
										class="media-wrapper">${entry.key.name}</a>
										<input type="hidden" name="id" id="id" value="${entry.key.id}"/></td>
									<td style="width: 100px">
										<div id="price">${entry.key.saleprice}</div>
									</td>
									<td style="width: 140px">
										<div class="input-group">
											<span class="input-group-addon"><i
												class="icon icon-minus"></i></span> <input type="text"
												name="amount" id="amount" value="${entry.value}"
												class="form-control" style="text-align: center;" /> <span
												class="input-group-addon"><i class="icon icon-plus"></i></span>
										</div>
									</td>
									<td style="width: 160px" class="text-center text-middle"><strong
										class="text-danger" id="sum"><fmt:formatNumber
												value="${entry.key.saleprice*entry.value}"
												pattern="#,##0.00" /></strong></td>
									<td style="width: 120px" class="text-middle text-center"><a
										href="${ctx}/shopcart/deleteprod?id=${entry.key.id}" class="deleter">删除</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="panel-footer text-right">
					选择了 <strong class="text-danger" id="amount-sum"></strong> 件商品， 共计：<strong
						id="price-sum" class="text-danger"></strong> <input type="submit"
						id="submit" class="btn btn-primary" value="提交订单"
						data-loading="稍候..." />
				</div>
			</form>
		</div>
	</div>
	<!-- /主内容 -->
	<div id="downDiv">
		<jsp:include page="./commonbottompage.jsp" />
	</div>

	<script src="${ctx}/zui/lib/jquery/jquery.js"></script>
	<script src="${ctx}/zui/js/zui.js"></script>
	<script src="${ctx}/js/my.js"></script>
	<script>
		//当页面被加载时，执行该方法 
		countSum();
	</script>
</body>
</html>