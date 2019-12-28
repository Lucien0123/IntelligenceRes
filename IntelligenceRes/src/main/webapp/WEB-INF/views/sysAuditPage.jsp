<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <!-- /页面右边头部 -->
        <div class="RightContentForm">
            <div class="CurrentPath">
                <i class="icon icon-map-marker"></i>&nbsp;&nbsp;审核中心
            </div>
            <div class="AuditPage">
                <form id="auditForm" method="post" action="${ctx }/sys/task/backTaskAction">
                    <c:forEach var="item" items="${detail}">
                        <div class="form-group has-success">
                            <h3>${item.key}</h3>
                            <div class="ItemContent">
                                    ${item.value}
                            </div>
                        </div>
                    </c:forEach>
                    <div class="form-group has-error margintop20px">
                        <h4>退回意见</h4>
                        <textarea name="backOpinion" id="backOpinion" class="form-control" rows="3" style="width: 70%;"
                                  placeholder="若退回，请在此处填写退回原由"></textarea>
                        <input id="route" name="route" type="text" hidden="hidden" value="${route}">
                        <input id="id" name="id" type="text" hidden="hidden" value="${id}">
                    </div>
                    <div class="form-group has-success margintop30px">
                        <button id="backButton" type="submit" class="btn btn-lg submitbutton150 marginLeft10px">退回</button>
                        <a href="${ctx }/sys/task/publishTaskAction?route=${route }&id=${id }" class="btn btn-primary btn-lg submitbutton150 marginLeft50px">发布</a>
                        <c:if test="${commend }">
                            <a href="${ctx }/sys/task/recommendJobAction?id=${id }" class="btn btn-primary btn-lg submitbutton150 marginLeft50px">推荐该工作</a>
                        </c:if>
                    </div>
                </form>
            </div>
            <!-- /审核文本内容 --!>
        </div>
        <!-- /页面表单内容 -->
        </div>
        <!-- /右侧内容部分 -->
    </div>
</div>
<div class="buttomdiv">
    <jsp:include page="./syscommonbottom.jsp"></jsp:include>
</div>
<script src="${ctx}/zui/lib/jquery/jquery.js"></script>
<script src="${ctx}/zui/js/zui.js"></script>
</body>

</html>
