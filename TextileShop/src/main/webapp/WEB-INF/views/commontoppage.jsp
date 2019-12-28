<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="tophead">
    <div style="width: 100%;"><img src="${ctx }/images/signaltop.jpg"/></div>
    <div class="row" style="margin-top:15px;">
        <div class="col-xs-2">
            <img src="${ctx}/images/websignal.jpg"/>
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
                    <a href="${ctx}/">首页</a>
                </li>
                <c:forEach items="${categorys }" var="cate">
                    <li>
                        <a href="${ctx }/product/productlist?cateid=${cate.id}">${cate.name }</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </nav>
</div>
<div class="topswaper">
    <div id="myNiceCarousel" class="carousel slide" data-ride="carousel">
        <!-- 圆点指示器 -->
        <ol class="carousel-indicators">
            <!--<c:forEach items="${swaperNews}" var="top" varStatus="abc">
			    	<li data-target="#myNiceCarousel" data-slide-to="${abc.index}" ${abc.index==0?"class='active'":""}></li>
			    </c:forEach>-->
            <li data-target="#myNiceCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myNiceCarousel" data-slide-to="1"></li>
            <li data-target="#myNiceCarousel" data-slide-to="2"></li>
            <li data-target="#myNiceCarousel" data-slide-to="3"></li>
        </ol>

        <!-- 轮播项目 -->
        <!-- 658*334 -->
        <div class="carousel-inner">
            <div class="item active">
                <img src="${ctx}/img/swaper1.jpg">
                <div class="carousel-caption">
                </div>
            </div>
            <div class="item">
                <img src="${ctx}/img/swaper2.jpg">
                <div class="carousel-caption">
                </div>
            </div>
            <div class="item">
                <img src="${ctx}/img/swaper3.jpg">
                <div class="carousel-caption">
                </div>
            </div>
            <div class="item">
                <img src="${ctx}/img/swaper4.jpg">
                <div class="carousel-caption">
                </div>
            </div>
        </div>

        <!-- 项目切换按钮 -->
        <a class="left carousel-control" href="#myNiceCarousel" data-slide="prev"> <span
                class="icon icon-chevron-left"></span>
        </a>
        <a class="right carousel-control" href="#myNiceCarousel" data-slide="next"> <span
                class="icon icon-chevron-right"></span>
        </a>
    </div>
</div>