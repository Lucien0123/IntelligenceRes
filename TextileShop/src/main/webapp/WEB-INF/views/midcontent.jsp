<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
    .card {
        border: none;
    }

    .card-heading {
        background-color: #B0C1CB;
    }

    .card-content {
        background-color: #B0C1CB;
    }
</style>
<!--[if lt IE 8]>
<div class="alert alert-danger">您正在使用 <strong>过时的</strong> 浏览器. 是时候 <a href="http://browsehappy.com/">更换一个更好的浏览器</a>
来提升用户体验.
</div>
<![endif]-->

<div class="cards">
    <div class="col-xs-2"></div>
    <c:forEach items="${categorys }" var="cate">
        <div class="col-xs-1" style="padding: 0;">
            <a class="card" href="${ctx }/product/productlist?cateid=${cate.id }"><img
                    src="${ctx }/img/click${cate.id }.png"/></a>
        </div>
    </c:forEach>
    <div class="col-xs-2"></div>
</div>
<!-- /馆链接 -->
<!-- 分隔符 -->
<div style="width: 100%;" align="center">
    <img src="${ctx}/images/spitter1.jpg	"/>
</div>
<br/>
<!-- 大链接 -->
<c:forEach items="${bigProducts }" var="bigp">
    <div class="row">
        <div class="col-xs-2"></div>
        <div class="col-xs-8">
            <a class="card" href="${ctx }/product/productdetail?productid=${bigp.id }"><img
                    src="${ctx}/img/${bigp.prodswaper}"/></a>
        </div>
        <div class="col-xs-2"></div>
    </div>
</c:forEach>
<!-- /大链接 -->
<!-- 分隔符 -->
<div style="width: 100%;" align="center">
    <img src="${ctx}/images/spitter1.jpg	"/>
</div>
<br/>
<!-- 中链接 -->
<div class="row">
    <div class="col-xs-2"></div>
    <c:forEach items="${midProducts0 }" var="midp">
        <div class="col-xs-4">
            <a class="card" href="${ctx }/product/productdetail?productid=${midp.id }"><img
                    src="${ctx}/img/${midp.prodswaper}"/></a>
        </div>
    </c:forEach>
    <div class="col-xs-2"></div>
</div>
<div class="row">
    <div class="col-xs-2"></div>
    <c:forEach items="${midProducts1 }" var="midp">
        <div class="col-xs-4">
            <a class="card" href="${ctx }/product/productdetail?productid=${midp.id }"><img
                    src="${ctx}/img/${midp.prodswaper}"/></a>
        </div>
    </c:forEach>
    <div class="col-xs-2"></div>
</div>
<div class="row">
    <div class="col-xs-2"></div>
    <c:forEach items="${midProducts2 }" var="midp">
        <div class="col-xs-4">
            <a class="card" href="${ctx }/product/productdetail?productid=${midp.id }"><img
                    src="${ctx}/img/${midp.prodswaper}"/></a>
        </div>
    </c:forEach>
    <div class="col-xs-2"></div>
</div>
<!-- /中链接 -->
<!-- 分隔符 -->
<div style="width: 100%;" align="center">
    <img src="${ctx}/images/spitter1.jpg"/>
</div>
<br/>
<!-- 小链接 -->
<div class="row">
    <div class="col-xs-2"></div>
    <c:forEach items="${smallProducts0 }" var="smlp">
        <div class="col-xs-2">
            <a class="card" href="${ctx }/product/productdetail?productid=${smlp.id }">
                <div class="media-wrapper">
                    <img src="${ctx}/img/${smlp.thumbnail}"/>
                </div>
                <div class="caption">lovo家纺罗莱 生活出品</div>
                <div class="card-heading"><strong>${smlp.prodswaper}</strong></div>
                <div class="card-content text-muted">${smlp.name}</div>
            </a>
        </div>
    </c:forEach>
    <div class="col-xs-2"></div>
</div>
<div class="row">
    <div class="col-xs-2"></div>
    <c:forEach items="${smallProducts1 }" var="smlp">
        <div class="col-xs-2">
            <a class="card" href="${ctx }/product/productdetail?productid=${smlp.id }">
                <div class="media-wrapper">
                    <img src="${ctx}/img/${smlp.thumbnail}"/>
                </div>
                <div class="caption">lovo家纺罗莱 生活出品</div>
                <div class="card-heading"><strong>${smlp.prodswaper}</strong></div>
                <div class="card-content text-muted">${smlp.name}</div>
            </a>
        </div>
    </c:forEach>
    <div class="col-xs-2"></div>
</div>
<!-- /小链接 -->