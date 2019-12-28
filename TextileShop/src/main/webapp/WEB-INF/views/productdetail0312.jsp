<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="${ctx}/images/titleicon.ico" type="image/x-icon"
          rel="shortcut icon"/>
    <title>罗莱家纺LOVO</title>
    <link href="${ctx}/zui/css/zui.css" rel="stylesheet">
    <link href="${ctx}/zui/css/zui-theme.css" rel="stylesheet">
    <link href="${ctx}/css/my.css" rel="stylesheet">

    <!--[if lt IE 9]>
    <script src="${ctx}/zui/lib/ieonly/html5shiv.js"></script>
    <script src="${ctx}/zui/lib/ieonly/respond.js"></script>
    <script src="${ctx}/zui/lib/ieonly/excanvas.js"></script>
    <![endif]-->
    <style type="text/css">
        #contentSwiperDiv {
            width: 265px;
            height: 300px;
        }

        #swiper {
            width: 265px;
            height: 300px;
        }

        .productpicStyle {
            width: 800px;
            height: auto;
        }

        .hotsalePic {
            margin-left: 15px;
        }

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
    </style>
</head>
<body>
<!--[if lt IE 8]>
<div class="alert alert-danger">您正在使用 <strong>过时的</strong> 浏览器. 是时候 <a href="http://browsehappy.com/">更换一个更好的浏览器</a>
    来提升用户体验.
</div>
<![endif]-->
<div id="topDiv">
    <jsp:include page="./user/commontopuser.jsp"/>
</div>
<!-- 主内容 -->
<div class="wrapper">
    <div class="row">
        <!-- /左边 -->
        <!-- 图片的尺寸大小 265 *270 -->
        <div class="col-xs-9">
            <!-- 详情 -->
            <div class="panel panel-body">
                <div class="row">
                    <div class="col-xs-4">
                        <div id="contentSwiperDiv">
                            <img src="${ctx }/img/${product.thumbnail}">
                        </div>
                    </div>
                    <div class="col-xs-8">
                        <div class="product-property">
                            <input type="hidden" name="productid" id="productid" value="${product.id}"/>
                            <h1 class="header-dividing">${product.name}</h1>
                            <ul class="list-unstyled">
                                <li><span class="attr-name">价格</span> <span
                                        class="attr-value">￥<del>
												<strong><fmt:formatNumber value="${product.price}"
                                                                          pattern="#,##0.00"/></strong>
											</del></span></li>
                                <li><span class="attr-name">特价</span> <span
                                        class="attr-value">￥<strong class="text-danger"
                                                                    style="font-size: 16px"><fmt:formatNumber
                                        value="${product.saleprice}" pattern="#,##0.00"/></strong></span></li>
                                <li><span class="attr-name">已售</span> <span
                                        class="attr-value">${product.salescount}</span></li>
                                <li id="countBox"><span class="attr-name">数量</span> <span
                                        class="attr-value">
											<div class="input-group" style="width: 120px;">
												<span class="input-group-addon"><i
                                                        class="icon icon-minus"></i></span> <input type="text"
                                                                                                   name="pnum"
                                                                                                   id="pnum" value="1"
                                                                                                   class="form-control"
                                                                                                   style="text-align: center;"> <span
                                                    class="input-group-addon"><i class="icon icon-plus"></i></span>
											</div>
									</span></li>
                            </ul>
                            <span> <a id="cartBtn" class="btn btn-primary"
                                      href="${ctx}/shopcart/addshopcart"> <i class="icon icon-shopping-cart"></i>
										加入购物车
								</a>
								</span>
                        </div>
                    </div>
                </div>
                <h5 class="header-dividing" style="margin-top: 30px;">
                    <i class="icon-file-text-alt text-muted"></i> 详情
                </h5>
                <div>
                    <div class="article-content" id="product-detail">
                        <div style="text-align: center;">
                            ${product.detaildescription }
                        </div>
                    </div>
                </div>
                <!-- /详情 -->
            </div>
        </div>
        <!-- /左边 -->

        <!-- 右边热卖 -->
        <div class="col-xs-3">
            <div class="panel">
                <div class="panel-heading">
                    <strong><i class="icon panel-icon icon-star"></i>罗莱家纺LOVO</strong>
                    <div class="pull-right">
                        <a href="javascript:;"> </a>
                    </div>
                </div>
                <div class="panel-body cards cards-borderless" id="product-hot">
                    <br /><br /><br /><br /><br />
                </div>
            </div>
        </div>
        <!-- /右边热卖 -->
    </div>
</div>
<!-- /主内容 -->
<!-- 添加到购物车的对话框 -->
<div class="modal fade" id="addToCartModal">
    <div class="modal-dialog modal-sm">
        <form method="post" class="form-horizontal">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">×</span><span class="sr-only">关闭</span>
                    </button>
                    <h4 class="modal-title">商品成功加入购物车</h4>
                </div>
                <div class="modal-footer">
                    <a href="${ctx}/" class="btn btn-primary">继续购物</a> <a
                        href="${ctx}/shopcart/shopcartlist" class="btn btn-primary"
                        style="min-width: 80px">立即结算</a>
                </div>
            </div>
        </form>
    </div>
</div>
<!-- /添加到购物车 -->
<div id="downDiv">
    <jsp:include page="./commonbottompage.jsp"/>
</div>

<script src="${ctx}/zui/lib/jquery/jquery.js"></script>
<script src="${ctx}/zui/js/zui.js"></script>
<script src="${ctx}/js/my.js"></script>
<script>
    //在对话框中添加一个隐藏的表单域，其value值设定为商品的ID
    $("#cartBtn").click(function () {
        var url = "${ctx}/shopcart/addshopcart";
        //先获取所先商品的ID和数量
        var id = $("#productid").val();
        var num = $("#pnum").val();
        var param = "productid=" + id + "&pnum=" + num;

        //AJAX提交数据
        $.post(url, param, function (data) {
            if (data == "ok") {
                $('#addToCartModal').modal('show');
            }
        });
        return false;
    });
</script>
</body>
</html>