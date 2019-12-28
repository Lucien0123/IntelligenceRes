<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>罗莱家纺LOVO</title>
    <link href="${ctx }/zui/css/zui.css" rel="stylesheet"/>
    <link href="${ctx }/zui/css/zui-theme.css" rel="stylesheet"/>
    <link href="${ctx }/css/my.css" rel="stylesheet">
    <link href="${ctx }/css/user.css" rel="stylesheet">
    <link href="${ctx }/font-awesome/css/font-awesome.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="${ctx }/zui/lib/ieonly/html5shiv.js"></script>
    <script src="${ctx }/zui/lib/ieonly/respond.js"></script>
    <script src="${ctx }/zui/lib/ieonly/excanvas.js"></script>
    <![endif]-->
    <style>
        body {
            margin: 0px;
            padding: 0px;
            background-color: #999;
            background-image: url("${ctx }/images/loginback.jpg");
        }

        #UserlogingDiv {
            width: 425px;
            height: 320px;
            margin-right: 220px;
            margin-top: 8%;
            float: right;
            background-color: #eee;
            border: 1px solid #999;
        }

        #errorMsgSpan {
            margin-left: 25px;
            position: absolute;
            z-index: 3;
        }

        #LoginCopyDiv {
            width: 100%;
            height: auto;
            padding: 0;
            position: absolute;
            top: 86%;
            left: 0;
        }
    </style>
    <!-- 定义js控制当获取鼠标焦点时上移一小段距离的动画 -->
    <script src="${ctx }/js/myjsMove.js" type="text/javascript"></script>
</head>
<body>
<!--[if lt IE 8]>
<div class="alert alert-danger">您正在使用 <strong>过时的</strong> 浏览器. 是时候 <a href="http://browsehappy.com/">更换一个更好的浏览器</a>
    来提升用户体验.
</div>
<![endif]-->
<div id="UserlogingDiv">
    <div id="LogingHeadDiv">
        <font id="loginTitleP">账号密码登录 </font>
    </div>
    <!--登录表单-->
    <form id="form1" method="post" action="${ctx }/user/login"
          onSubmit="return checkNull()">
        <div class="logininput">
            <input id="username" name="username" class="form-control form-focus input-lg"
                   autofocus type="text" placeholder="  请输入账号"/> &nbsp; <input
                id="password" name="password" class="form-control form-focus input-lg"
                type="password" placeholder="  请输入密码"/>
        </div>

        <div class="button_sp_area">
            <input type="submit" id="btnlogin" class="btn btn-primary"
                   style="width: 120px; margin-left: 30px;" value="登录"/>
            <c:if test="${!empty msg}">
                &nbsp;&nbsp;&nbsp;<span class="form-group"><span id="formError" class="alert alert-danger">${msg}</span></span>
                <c:remove var="msg"/>
            </c:if>
        </div>
    </form>
    <!--/登录表单-->

    <!--登录帮助-->
    &nbsp;
    <div align="right">
        <a href="javascript:;" name="logingHerf">忘记密码？</a>&nbsp; | &nbsp;<a
            href="${ctx}/user/userregister">注册新账号</a>&nbsp; | &nbsp;<a
            href="${ctx}/"> 返回主页>></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </div>
    <!--/登录帮助-->
</div>
<!--手机型号连接，上移动画-->
<div id="linkDiv" align="center">
    <table cellspacing="0" cellpadding="0">
        <tr align="center">
            <td>
                <div id="apple1" class="apple1">
                    <a href="javascript:;" name="iphone"><i
                            class="fa fa-2x fa-apple" style="color: #fff"></i><br/>iphone</a>
                </div>
            </td>
            <td>
                <div id="apple2" class="apple1">
                    <a href="javascript:;" name="iphone"><i
                            class="fa fa-2x fa-desktop" style="color: #fff"></i><br/>iMac</a>
                </div>
            </td>
            <td>
                <div id="apple3" class="apple1">
                    <a href="javascript:;" name="iphone"><i
                            class="fa fa-2x fa-android" style="color: #fff"></i><br/>Android</a>
                </div>
            </td>
            <td>
                <div id="apple4" class="apple1">
                    <a href="javascript:;" name="iphone"><i
                            class="fa fa-2x fa-windows" style="color: #fff"></i><br/>Windows<br/>Phone</a>
                </div>
            </td>
            <td>
                <div id="apple5" class="apple1">
                    <a href="javascript:;" name="iphone"><i
                            class="fa fa-2x fa-mobile-phone" style="color: #fff"></i><br/>其他设备</a>
                </div>
            </td>
        </tr>
    </table>
</div>
<!--/手机型号连接，上移动画-->

<!--登录界面的CopyRight-->
<!--应用的是主界面的CopyRight，只是精简了一部分-->
<div id="LoginCopyDiv" align="center">
    <div id="CopyRightDiv">
        <!--其他版权信息-->
        <div id="CopyRightIm">
            <div>
                <a name="CopyHr" href="javascript: ;">&nbsp;天津大学</a> | <a name="CopyHr"
                                                                          href="javascript: ;">南开大学</a> | <a
                    name="CopyHr" href="javascript: ;">中国名航大学</a>
                | <a name="CopyHr" href="javascript: ;">天津工业大学</a> | <a name="CopyHr"
                                                                        href="javascript: ;">天津科技大学</a> | <a
                    name="CopyHr" href="javascript: ;">天津理工大学</a>
                | <a name="CopyHr" href="javascript: ;">天津师范大学</a> | <a name="CopyHr"
                                                                        href="javascript: ;">天津医科大学</a> | <a
                    name="CopyHr" href="javascript: ;">天津商业大学</a>
                | <a name="CopyHr" href="javascript: ;">&nbsp;天津财经大学</a> | <a
                    name="CopyHr" href="#">天津外国语大学</a> | <a name="CopyHr"
                                                            href="javascript: ;">天津体育大学</a> | <a name="CopyHr"
                                                                                                 href="javascript: ;">天津职业技术师范大学</a>
                | <a name="CopyHr" href="http://www.tjuci.edu.cn/">天津城建大学</a>&nbsp;&nbsp;&nbsp;&nbsp;<font
                    color="#fff" size="-1">罗莱家纺网站：</font><a name="CopyHr"
                                                            href="${ctx}/index.jsp">www.LOVO.com</a>
            </div>
            <div align="center" style="width: 900px">
                <a name="CopyHr" href="javascript: ;">使用条件</a> | <a name="CopyHr"
                                                                    href="javascript: ;">隐私申明</a> | <a name="CopyHr"
                                                                                                       href="javascript: ;">基于兴趣的广告</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font
                    color="#fff" size="-1">COPYRIGHT © 2016-2020
                LOVO罗莱.com</font>
            </div>
        </div>
        <!--/其他版权信息-->
    </div>
</div>
<!--/登录界面的CopyRight-->
</body>
</html>