<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>园区智力资源中心</title>
    <link href="${ctx}/zui/css/zui.css" rel="stylesheet"/>
    <link href="${ctx}/zui/css/sys-zui-theme.css" rel="stylesheet"/>
    <link href="${ctx}/css/sysmy.css" rel="stylesheet">
    <link href="${ctx}/css/mystyle.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="${ctx}/zui/lib/ieonly/html5shiv.js"></script>
    <script src="${ctx}/zui/lib/ieonly/respond.js"></script>
    <script src="${ctx}/zui/lib/ieonly/excanvas.js"></script>
    <![endif]-->
    <style>
        body {
            background-color: #444444;
        }

        .UserlogingDiv {
            width: 425px;
            height: 320px;
            margin-top: 8%;
            background-color: #eee;
            border: 1px solid #999;
            padding: 20px;
        }

        .errorMsgSpan {
            margin-left: 25px;
            position: absolute;
            z-index: 3;
        }
    </style>
</head>

<body>
<!--[if lt IE 8]>
<div class="alert alert-danger">您正在使用 <strong>过时的</strong> 浏览器. 是时候 <a href="http://browsehappy.com/">更换一个更好的浏览器</a>
    来提升用户体验.
</div>
<![endif]-->
<div class="topdiv">
    <jsp:include page="./syscommmontop.jsp"></jsp:include>
</div>
<div class="middiv row" style="border: none;" align="center">
    <div class="UserlogingDiv">
        <div class="LogingHeadDiv">
            <span class="font20gray3">管理员登录</span>
        </div>
        <!--登录表单-->
        <form name="loginform" action="${ctx }/sys/adminer/adminerlogin" onsubmit="return charCheck();">
            <div class="logininput margintop20px">
                <input name="account" id="account" class="form-control form-focus input-lg" autofocus type="text"
                       placeholder="  请输入账号"/> &nbsp;
                <input name="password" id="password" class="form-control form-focus input-lg" type="password" placeholder="  请输入密码"/>
            </div>

            <div class="LoginButton margintop30px">
                <button type="submit" class="btn btn-lg btn-primary submitbutton150">登录</button>
                <span id="errMsg" class="form-group marginLeft15px" style="display: none;">
                    <span class="alert alert-danger">帐号密码不能为空！</span>
                </span>
                <c:if test="${!empty errmsg}">
                    <span class="form-group marginLeft15px">
                        <span class="alert alert-danger">${errmsg}</span>
                    </span>
                    <c:remove var="errmsg" />
                </c:if>
            </div>
        </form>
        <!--/登录表单-->
    </div>
</div>
<script src="${ctx}/zui/lib/jquery/jquery.js"></script>
<script src="${ctx}/zui/js/zui.js"></script>
<script>
    var charCheck = function () {
        var account = $("#account").val();
        var password = $("#password").val();
        if (account != '' && password != ''){
            $("#errMsg").hide();
            return true;
        } else {
            $("#errMsg").show();
            return false;
        }
    }
</script>
</body>

</html>
