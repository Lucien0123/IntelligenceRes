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
                <i class="icon icon-map-marker"></i>&nbsp;&nbsp;应聘面试中心
            </div>
            <div class="NewsCenter">
                <div class="TaskCenter">
                    <ul class="nav nav-tabs">
                        <li class="active">
                            <a href="###" data-target="#tab2Content1" data-toggle="tab">投递列表</a>
                        </li>
                        <li>
                            <a href="###" data-target="#tab2Content2" data-toggle="tab">邀请面试列表</a>
                        </li>
                    </ul>
                    <div class="tab-content margintop20px">
                        <div class="tab-pane fade active in" id="tab2Content1">
                            <table class="table table-hover table-auto">
                                <thead>
                                <tr>
                                    <th class="text-center" style="width: 12%;">投递人姓名</th>
                                    <th class="text-center" style="width: 18%;">面试岗位</th>
                                    <th class="text-center" style="width: 12%;">联系电话</th>
                                    <th class="text-center" style="width: 13%;">职业</th>
                                    <th class="text-center" style="width: 10%;">投递时间</th>
                                    <th class="text-center" style="width: 10%;">简历</th>
                                    <th class="text-center" style="width: 10%;">状态</th>
                                    <th class="text-center" style="width: 15%;">操作</th>
                                </tr>
                                </thead>
                                <tbody align="center">
                                <c:forEach var="deliverItem" items="${deliverPage.content }">
                                    <tr>
                                        <td class="trFontSize" style="line-height: 34px;">${deliverItem.commonuserName }</td>
                                        <td class="trFontSize OverflowEllipsis" style="line-height: 34px;">${deliverItem.jobName }</td>
                                        <td class="trFontSize" style="line-height: 34px;">${deliverItem.telphone }</td>
                                        <td class="trFontSize" style="line-height: 34px;">${deliverItem.occupation }</td>
                                        <td class="trFontSize" style="line-height: 34px;">${deliverItem.deliverDateStr }</td>
                                        <td class="trFontSize" style="line-height: 34px;"><a href="${deliverItem.resumeLink }" target="_blank">查看简历</a></td>
                                        <td class="trFontSize" style="line-height: 34px;">${deliverItem.status }</td>
                                        <td class="trFontSize" style="line-height: 34px;">
                                            <a href="${ctx }/sys/interview/invite?id=${deliverItem.id }" class="trFontSize">邀请面试</a>
                                            &nbsp;|&nbsp;
                                            <a href="${ctx }/sys/interview/nopass?id=${deliverItem.id }" class="trFontSize" style="color: #FF0000;">不合适</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <div class="panel-footer">
                                <q:pager totalElements="${deliverPage.totalElements}"
                                         number="${deliverPage.number}"/>
                                <div style="clear: both;"></div>
                            </div>
                        </div>
                        <!-- /投递列表 -->
                        <div class="tab-pane fade" id="tab2Content2">
                            <table class="table table-hover table-auto">
                                <thead>
                                <tr>
                                    <th class="text-center" style="width: 12%;">投递人</th>
                                    <th class="text-center" style="width: 15%;">面试岗位</th>
                                    <th class="text-center" style="width: 11%;">联系电话</th>
                                    <th class="text-center" style="width: 11%;">面试官</th>
                                    <th class="text-center" style="width: 11%;">面试时间</th>
                                    <th class="text-center" style="width: 15%;">面试地点</th>
                                    <th class="text-center" style="width: 15%;">操作</th>
                                </tr>
                                </thead>
                                <tbody align="center">
                                <c:forEach var="interviewItem" items="${interviewPage.content }">
                                    <tr>
                                        <td class="trFontSize" style="line-height: 34px;">${interviewItem.commonuserName }</td>
                                        <td class="trFontSize OverflowEllipsis" style="line-height: 34px;">${interviewItem.jobName }</td>
                                        <td class="trFontSize" style="line-height: 34px;">${interviewItem.telphone }</td>
                                        <td class="trFontSize" style="line-height: 34px;">${interviewItem.interviewer }</td>
                                        <td class="trFontSize" style="line-height: 34px;">${interviewItem.inerviewDateStr }</td>
                                        <td class="trFontSize OverflowEllipsis" style="line-height: 34px;">${interviewItem.interviewPlace }</td>
                                        <td class="trFontSize" style="line-height: 34px;">
                                            <a href="${ctx }/sys/interview/passInterview?id=${interviewItem.id }" class="trFontSize">通过面试</a>
                                            &nbsp;|&nbsp;
                                            <a href="${ctx }/sys/interview/nopass?id=${interviewItem.id }" class="trFontSize" style="color: #FF0000;">不合适</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <div class="panel-footer">
                                <q:pager totalElements="${interviewPage.totalElements}"
                                         number="${interviewPage.number}"/>
                                <div style="clear: both;"></div>
                            </div>
                        </div>
                        <!-- /已办 -->
                    </div>
                </div>
            </div>
        </div>
        <!-- /新闻资讯列表 -->
    </div>
    <!-- /右侧内容部分 -->
</div>
<div class="buttomdiv">
    <jsp:include page="./syscommonbottom.jsp"></jsp:include>
</div>
<script src="${ctx}/zui/lib/jquery/jquery.js"></script>
<script src="${ctx}/zui/js/zui.js"></script>
<script>

</script>
</body>

</html>
