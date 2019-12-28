<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="tophead">
    <div style="width: 100%;"><img src="${ctx }/images/signaltop.jpg"/></div>
    <div class="row" style="margin-top:15px;">
        <div class="col-xs-2">
            <img src="${ctx }/images/websignal.jpg"/>
        </div>
        <c:if test="${!empty sessionScope.current_user}" var="flag">
            <div class="col-xs-8"
                 style="text-align: right; border-right: 1px solid #ccc">
                <a name="toplink" href="${ctx}/user/userindexpage">个人中心</a>
            </div>
            <div class="col-xs-2">
                <a name="toplink" href="${ctx}/user/loginout" id="loginout">注销</a>
            </div>
        </c:if>
        <c:if test="${!flag}">
            <div class="col-xs-7"
                 style="text-align: right; border-right: 1px solid #ccc">
                <a name="toplink" href="${ctx}/user/userlogin0312">登录</a>
            </div>
            <div class="col-xs-2">
                <a name="toplink" href="${ctx}/user/userregister">注册</a>
            </div>
        </c:if>
    </div>
    <div class="wrapper row">
        <div class="col-xs-9"></div>
        <div class="col-xs-3" id="cart">
            <a class="btn btn-primary" href="${ctx}/shopcart/shopcartlist">
                <i class="icon icon-shopping-cart"></i>&nbsp;&nbsp;&nbsp;&nbsp;购物车&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <span class="label label-badge" style=""></span>
            </a>
        </div>
    </div>
</div>
<div class="webguilder">
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <ul class="nav navbar-nav nav-justified">
            <li>&nbsp;</li>
            <li>&nbsp;</li>
            <li class="active">
                <a href="${ctx }/">首页</a>
            </li>
            <c:forEach items="${sessionScope.categorys }" var="cate">
                <li>
                    <a href="${ctx }/product/productlist?cateid=${cate.id}">${cate.name }</a>
                </li>
            </c:forEach>
        </ul>
    </div>
</nav>
</div>