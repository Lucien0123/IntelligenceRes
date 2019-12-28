<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>罗莱家纺LOVO</title>
<link href="${ctx}/zui/css/zui.css" rel="stylesheet">
<link href="${ctx}/zui/css/zui-theme.css" rel="stylesheet">
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

#midDiv {
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
	right: 1px;
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
	<div class="wrapper" style="min-height: 342px;">
	    <div class="panel" style="margin-top: 20px">
		  <div class="panel-heading" style="font-size: 18px">
		    <strong>结算</strong>
		  </div>
		  <form action="${ctx}/order/paymentOrder" method="post">
	      <div class="panel-body">
	          <div id="addressBox">
              <div id="addressTitle">
                  <strong>收货地址</strong>
                  <a href="javascript:;">添加新地址</a>
              </div>
              <div id="addressList">
               <c:forEach items="${addressList}" var="ad">
                 <div class="item">
                     <span>
                     <input type="radio" ${ad.defaultvalue ? 'checked="checked"' : ""} name="addressid" id="addressid" value="${ad.id}"/>
                     </span>
                     <strong>${ad.contact}</strong>
                     <span>${ad.mobile}</span>
                     <span class="text-muted">${ad.street}</span>
                     <span class="text-muted">${ad.zipcode}</span>
                  </div>
                </c:forEach>
             </div>
             </div>
             
             <br/>
             <br/>
             <div class="form-group">
             	<label class="col-xs-1 control-label required">买家留言</label>
          		<div class="col-xs-11">
             	<input type="text" name="remark" id="remark" class="form-control" />
             	</div>
             </div>
		    </div>
		    <div class="panel-footer text-right">
		                选择了 <strong class="text-danger">${curr_order.total_amount}</strong> 件商品，
		                共计：<strong class="text-danger"><fmt:formatNumber value="${curr_order.payment_price}" pattern="￥#,##0.00"/> </strong>
		      <input type="submit" id="submit" class="btn btn-primary" value="订单支付" />
		    </div>
		  </form>
		</div>
	</div>
	<!-- /主内容 -->

	<div id="downDiv" align="center">
		<jsp:include page="./commonbottompage.jsp" />
	</div>

	<script src="z${ctx}/ui/lib/jquery/jquery.js"></script>
	<script src="${ctx}/zui/js/zui.js"></script>
	<script src="${ctx}/js/my.js"></script>
</body>
</html>