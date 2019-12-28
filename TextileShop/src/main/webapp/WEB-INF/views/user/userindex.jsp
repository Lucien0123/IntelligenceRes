<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link href="${ctx}/images/titleicon.ico" type="image/x-icon" rel="shortcut icon"/>
    <title>Hathaway—用户中心</title>
    <link href="${ctx}/zui/css/zui.css" rel="stylesheet">
    <link href="${ctx}/zui/css/zui-theme.css" rel="stylesheet">
    <link href="${ctx}/css/my.css" rel="stylesheet">
    <link href="${ctx}/css/user.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="${ctx}/zui/lib/ieonly/html5shiv.js"></script>
    <script src="${ctx}/zui/lib/ieonly/respond.js"></script>
    <script src="${ctx}/zui/lib/ieonly/excanvas.js"></script>
    <![endif]-->
    <!-- CSS布局 -->
    <style>
        body {
            margin: 0 auto;
            width: 100%;
        }

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
    </style>
</head>
<body>
<!--[if lt IE 8]>
<div class="alert alert-danger">您正在使用 <strong>过时的</strong> 浏览器. 是时候 <a href="http://browsehappy.com/">更换一个更好的浏览器</a>
    来提升用户体验.
</div>
<![endif]-->
<!-- 顶部 -->
<div id="topDiv">
    <jsp:include page="./commontopuser.jsp"/>
</div>
<!-- /顶部 -->
<!-- 中部 -->
<div style="min-height: 370px;">
    <!-- 主内容 -->

    <!--  -->
    <div class="row" style="padding: 20px 0px;">
        <!-- /左边 -->
        <div class="col-xs-2">
            <%-- JSP中的include中page路径里/代表的是项目的根目录 --%>
            <jsp:include page="./commonMenu.jsp">
                <jsp:param value="index" name="tag"/>
            </jsp:include>
        </div>
        <!-- /左边 -->

        <!-- 右边 -->
        <div class="col-xs-9">
            <div class="panel">
                <div class="panel-heading">
                    <strong><i class="icon-shopping-cart"> </i>订单管理</strong>
                </div>
                <table class="table table-striped tablesorter">
                    <thead>
                    <tr class="text-center">
                        <td style="width:60px">ID</td>
                        <td style="width:260px" class="text-left">商品信息</td>
                        <td style="width:60px">数量</td>
                        <td style="width:80px" class="text-right">金额</td>
                        <td style="width:210px">订单跟踪</td>
                        <td style="width:60px">状态</td>
                        <td style="min-width:100px">买家留言</td>
                        <td style="width:220px">操作</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${orders}" var="ord">
                        <tr style="overflow: hidden;">
                            <td class="text-center">${ord.id}</td>
                            <td class="text-left">
                                <c:forEach items="${ord.items}" var="item" varStatus="vs">
                                    <c:if test="${vs.index >0}"><br/></c:if>
                                    <a href="${ctx}/product_detail?id=${item.product.id}">${item.product.name}</a> x ${item.amount}
                                </c:forEach>
                            </td>
                            <td class="text-center">${ord.total_amount}</td>
                            <td class="text-right"><fmt:formatNumber value="${ord.payment_price}"
                                                                     pattern="￥#,##0.00"/></td>
                            <td class="text-center">
                                下单时间：<fmt:formatDate value="${ord.create_time}" pattern="yyyy-MM-dd HH:mm"/>
                                <c:if test="${!empty ord.delivery_time}">
                                    <br/>发货时间：<fmt:formatDate value="${ord.delivery_time}" pattern="yyyy-MM-dd HH:mm"/>
                                </c:if>
                                <c:if test="${!empty ord.end_time}">
                                    <br/>完成时间：<fmt:formatDate value="${ord.end_time}" pattern="yyyy-MM-dd HH:mm"/>
                                </c:if>
                            </td>
                            <td class="text-center">
                                <!-- 2已付款,3待发货,4已发货,5己收货,6已完成,-1已取消 -->
                                <c:choose>
                                    <c:when test="${ord.status==2}">已付款</c:when>
                                    <c:when test="${ord.status==3}">待发货</c:when>
                                    <c:when test="${ord.status==4}">已发货</c:when>
                                    <c:when test="${ord.status==5}">己收货</c:when>
                                    <c:when test="${ord.status==6}">已完成</c:when>
                                    <c:when test="${ord.status==-1}">已取消</c:when>
                                </c:choose>
                            </td>
                            <td class="text-right">${ord.remark}</td>
                            <td class="text-center">
                                <a href="#">详情</a>
                        </tr>
                    </c:forEach>
                    </tbody>
                    <tfoot>
                    <tr>
                        <td colspan="8">
                        </td>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div>
        <div class="col-xs-1"></div>
        <!-- /右边 -->
    </div>
    <!-- /主内容 -->
</div>
<!-- /中部 -->

<!-- 底部 -->
<div id="downDiv">
    <jsp:include page="../commonbottompage.jsp"/>
</div>
<!-- /底部 -->

<script src="${ctx}/zui/lib/jquery/jquery.js"></script>
<script src="${ctx}/zui/js/zui.js"></script>
<script src="${ctx}/js/my.js"></script>
</body>
</html>