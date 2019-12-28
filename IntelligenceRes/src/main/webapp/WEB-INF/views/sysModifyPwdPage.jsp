<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.itvk.cn/jsp/tags" prefix="q" %>
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
<div class="middiv row">
    <div class="col-xs-2 LeftGuilder" style="padding-right: 0px;">
        <jsp:include page="./syscommonleftguilder.jsp"></jsp:include>
    </div>
    <!-- /左侧导航条 -->
    <div class="col-xs-10 RightContent" style="padding: 0;">
        <div class="RightHead">
            <span class="ParkName">天津市工业园区</span>
            <a href="${ctx}/sys/adminer/loginoutAction" class="loginicon">退出</a>
            <!--<span class="loginicon"><i class="icon icon-signout icon-2x"></i>&nbsp;登出</span>-->
        </div>

        <div class="RightContentForm">
            <div class="CurrentPath">
                <i class="icon icon-map-marker"></i>&nbsp;&nbsp;修改密码
            </div>
            <div class="ModifyPwdPage">
                <form id="modifyPwdForm" action="${ctx}/sys/adminer/modifyPwdAction" onsubmit="return confirmCheck();">
                    <div class="form-group has-success">
                        <label>当前登录帐号：</label>
                    </div>
                    <div class="form-group has-success">
                        <label>当前用户姓名：</label>
                    </div>
                    <div class="form-group has-success">
                        <input type="password" id="oldPwd" name="oldPwd" class="form-control input-lg"
                               placeholder="请输入原密码">
                    </div>
                    <div class="form-group has-warning margintop20px">
                        <input type="password" id="newPwd" name="password" class="form-control input-lg"
                               placeholder="请输入新密码（不少于6位）">
                    </div>
                    <div class="form-group has-warning margintop20px">
                        <input type="password" id="confirmPwd" name="confirmPwd" class="form-control input-lg"
                               placeholder="确认新密码">
                    </div>
                    <div class="form-group margintop30px">
                        <button class="btn btn-lg btn-primary submitbutton150" type="submit">修改密码</button>
                        <span id="errMsg" class="form-group marginLeft15px" style="display: none;">
                            <span class="alert alert-danger">请将密码输入完整！</span>
                        </span>
                        <span id="errMsgLength" class="form-group marginLeft15px" style="display: none;">
                            <span class="alert alert-danger">新密码的长度不少于6位！</span>
                        </span>
                        <span id="errMsgEqual" class="form-group marginLeft15px" style="display: none;">
                            <span class="alert alert-danger">请确认两次密码输入一致！</span>
                        </span>
                        <c:if test="${!empty errmsg}">
                            <span class="form-group">
                                <span class="alert alert-danger marginLeft15px">${errmsg }</span>
                            </span>
                        </c:if>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- /右侧内容部分 -->
</div>
<div class="buttomdiv">
    <jsp:include page="./syscommonbottom.jsp"></jsp:include>
</div>
<script src="${ctx}/zui/lib/jquery/jquery.js"></script>
<script src="${ctx}/zui/js/zui.js"></script>
<script>
    var confirmCheck = function () {
        var pwd = $("#newPwd").val();
        var confirm = $("#confirmPwd").val();
        var oldpwd = $("#oldPwd").val();
        var flag = true;
        if (oldpwd == '' || confirm == '' || pwd == '') {
            flag = false;
            $("#errMsg").show();
        } else {
            $("#errMsg").hide();
            if (pwd.length < 6) {
                $("#errMsgLength").show();
                flag = false;
            } else {
                $("#errMsgLength").hide();
                if (confirm != pwd) {
                    flag = false;
                    $("#errMsgEqual").show();
                } else {
                    $("#errMsgEqual").hide();
                }
            }
        }
        return flag;
    }
</script>
</body>

</html>
