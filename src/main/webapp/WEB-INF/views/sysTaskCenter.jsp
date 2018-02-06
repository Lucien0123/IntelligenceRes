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
                <i class="icon icon-map-marker"></i>&nbsp;&nbsp;个人任务中心
            </div>
            <!-- 发布新职位表单 -->
            <div class="TaskCenter">
                <ul class="nav nav-tabs">
                    <li class="active">
                        <a href="###" data-target="#tab2Content1" data-toggle="tab">待办任务列表</a>
                    </li>
                    <li>
                        <a href="###" data-target="#tab2Content2" data-toggle="tab">已办任务列表</a>
                    </li>
                </ul>
                <div class="tab-content margintop20px">
                    <div class="tab-pane fade active in" id="tab2Content1">
                        <table class="table table-hover table-auto">
                            <thead>
                            <tr>
                                <th class="text-center" style="width: 13%;">类别</th>
                                <th class="text-center" style="width: 33%;">发布任务标题</th>
                                <th class="text-center" style="width: 13%;">提交/处理人</th>
                                <th class="text-center" style="width: 13%;">发布时间</th>
                                <th class="text-center" style="width: 13%;">状态</th>
                                <th class="text-center" style="width: 15%;">操作</th>
                            </tr>
                            </thead>
                            <tbody align="center">
                            <c:forEach var="todo" items="${todoPage.content }">
                                <tr>
                                    <td class="trFontSize" style="line-height: 34px;">${todo.taskType}</td>
                                    <td class="trFontSize" style="line-height: 34px;">${todo.taskTitle}</td>
                                    <c:if test="${isSubmiter}">
                                        <td class="trFontSize" style="line-height: 34px;">${todo.submiterName}</td>
                                    </c:if>
                                    <c:if test="${!isSubmiter}">
                                        <td class="trFontSize" style="line-height: 34px;">${todo.auditorName}</td>
                                    </c:if>
                                    <td class="trFontSize" style="line-height: 34px;">${todo.publishDateStr}</td>
                                    <td class="trFontSize" style="line-height: 34px;">${todo.status}</td>
                                    <c:if test="${isSubmiter}">
                                        <td class="trFontSize" style="line-height: 34px;">
                                            <a href="${ctx }/sys/task/toAuditPage?route=${todo.taskType}&id=${todo.id}" class="trFontSize">审核</a>
                                        </td>
                                    </c:if>
                                    <c:if test="${!isSubmiter}">
                                        <td class="trFontSize" style="line-height: 34px;">
                                            <a href="${ctx }/sys/task/toEditPage?route=${todo.taskType}&taskid=${todo.id}" class="trFontSize">修改</a>
                                            &nbsp;&nbsp;|&nbsp;&nbsp;
                                            <a href="${ctx }/sys/task/deleteTaskAction?route=${todo.taskType}&taskid=${todo.id}" class="trFontSize">取消</a>
                                        </td>
                                    </c:if>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <div class="panel-footer">
                            <q:pager totalElements="${todoPage.totalElements}"
                                     number="${todoPage.number}"/>
                            <div style="clear: both;"></div>
                        </div>
                    </div>
                    <!-- /待办 -->
                    <div class="tab-pane fade" id="tab2Content2">
                        <table class="table table-hover table-auto">
                            <thead>
                            <tr>
                                <th class="text-center" style="width: 13%;">类别</th>
                                <th class="text-center" style="width: 33%;">发布任务标题</th>
                                <th class="text-center" style="width: 13%;">提交/处理人</th>
                                <th class="text-center" style="width: 13%;">发布时间</th>
                                <th class="text-center" style="width: 13%;">状态</th>
                                <th class="text-center" style="width: 15%;">操作</th>
                            </tr>
                            </thead>
                            <tbody align="center">
                            <c:forEach var="complete" items="${completePage.content }">
                                <tr>
                                    <td class="trFontSize" style="line-height: 34px;">${complete.taskType}</td>
                                    <td class="trFontSize" style="line-height: 34px;">${complete.taskTitle}</td>
                                    <c:if test="${isSubmiter}">
                                        <td class="trFontSize" style="line-height: 34px;">${complete.submiterName}</td>
                                    </c:if>
                                    <c:if test="${!isSubmiter}">
                                        <td class="trFontSize" style="line-height: 34px;">${complete.auditorName}</td>
                                    </c:if>
                                    <td class="trFontSize" style="line-height: 34px;">${complete.publishDateStr}</td>
                                    <td class="trFontSize" style="line-height: 34px;">${complete.status}</td>
                                    <td class="trFontSize" style="line-height: 34px;"></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <div class="panel-footer">
                            <q:pager totalElements="${completePage.totalElements}"
                                     number="${completePage.number}"/>
                            <div style="clear: both;"></div>
                        </div>
                    </div>
                    <!-- /已办 -->
                </div>
            </div>
            <!-- /任务列表 -->
        </div>
        <!-- /页面表单内容 -->
    </div>
    <!-- /右侧内容部分 -->
</div>
<div class="buttomdiv">
    <jsp:include page="./syscommonbottom.jsp"></jsp:include>
</div>
<script src="${ctx}/zui/lib/jquery/jquery.js"></script>
<script src="${ctx}/zui/js/zui.js"></script>
</body>

</html>
