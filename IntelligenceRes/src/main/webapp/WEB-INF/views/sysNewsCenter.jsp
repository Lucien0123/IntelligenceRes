<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.itvk.cn/jsp/tags" prefix="q"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>园区智力资源中心</title>
    <link href="${ctx}/zui/css/zui.css" rel="stylesheet" />
    <link href="${ctx}/zui/css/sys-zui-theme.css" rel="stylesheet" />
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
<div class="alert alert-danger">您正在使用 <strong>过时的</strong> 浏览器. 是时候 <a href="http://browsehappy.com/">更换一个更好的浏览器</a> 来提升用户体验.</div>
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
                <i class="icon icon-map-marker"></i>&nbsp;&nbsp;新闻咨询中心
            </div>
            <div class="NewsCenter">
                <div class="PublicJobButton" align="right">
                    <a class="btn btn-lg btn-primary submitbutton150" href="${ctx}/sys/news/publishNewsPage">发布新闻咨询</a>
                </div>
                <div class="NewsTable">
                    <table class="table table-hover table-auto">
                        <thead>
                        <tr>
                            <th class="text-center" style="width: 13%;">新闻、咨询编号</th>
                            <th class="text-center" style="width: 33%;">新闻、咨询标题</th>
                            <th class="text-center" style="width: 13%;">新闻、咨询发布人</th>
                            <th class="text-center" style="width: 13%;">发布时间</th>
                            <th class="text-center" style="width: 13%;">当前状态</th>
                            <th class="text-center" style="width: 15%;">操作</th>
                        </tr>
                        </thead>
                        <tbody align="center">
                        <c:forEach var="news" items="${newspage.content}">
                            <tr>
                                <td class="trFontSize" style="line-height: 34px;">NEWS1700${news.id}</td>
                                <td class="trFontSize" style="line-height: 34px;">${news.newsTitle}</td>
                                <td class="trFontSize" style="line-height: 34px;">${news.publisher}</td>
                                <td class="trFontSize" style="line-height: 34px;">${news.publicDate}</td>
                                <td class="trFontSize" style="line-height: 34px;">已发布</td>
                                <td class="trFontSize" style="line-height: 34px;">
                                    <a href="/sys/news/deleteNewsAction?newsid=${news.id}" class="trFontSize">取消</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="panel-footer">
                        <q:pager totalElements="${newspage.totalElements}"
                                 number="${newspage.number}"/>
                        <div style="clear: both;"></div>
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
</body>

</html>
