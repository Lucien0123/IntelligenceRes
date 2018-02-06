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
                <i class="icon icon-map-marker"></i>&nbsp;&nbsp;招聘中心
            </div>
            <!-- 发布招聘工作列表 -->
            <div class="RecruitJobList">
                <div class="PublicJobButton" align="right">
                    <c:if test="${canPublish }">
                        <a class="btn btn-lg btn-primary submitbutton150" href="${ctx}/sys/recruite/publicNewJobPage">发布新的职位</a>
                    </c:if>
                </div>
                <div class="RecruitedJobList">
                    <c:forEach var="job" items="${jobPage.content }">
                        <div class="RecruitedJobItem">
                            <div class="row">
                                <div class="col-xs-2 padtopbottom10">
                                    <div class="ResumeCount">
                                        ${job.visitTimes}
                                    </div>
                                    <div class="font20gray3" align="center">次浏览</div>
                                </div>
                                <div class="col-xs-9 padtopbottom10">
                                    <div class="font24black">${job.jobName }</div>
                                    <div class="font14gray6">
                                        <span>发布人：${job.publicer }</span>
                                        <span class="marginLeft15px">发布时间：${job.publicTime }</span>
                                    </div>
                                    <div class="JobDemand">
                                        ${job.jobAdvantage }
                                    </div>
                                </div>
                                <div class="col-xs-1" align="right">
                                    <div id="deleteAction" class="DeleteJobOper" align="center">
                                        <input hidden="true" value="${job.id }" name="jobid" class="input1"/>
                                        撤销职位
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <!-- /已发布职位列表 -->
                <div class="panel-footer">
                    <q:pager totalElements="${jobPage.totalElements}"
                             number="${jobPage.number}"/>
                    <div style="clear: both;"></div>
                </div>
            </div>
        </div>
        <!-- /右侧内容部分 -->
    </div>
</div>
<div class="buttomdiv">
    <jsp:include page="./syscommonbottom.jsp"></jsp:include>
</div>
<script src="${ctx}/zui/lib/jquery/jquery.js"></script>
<script src="${ctx}/zui/js/zui.js"></script>
<script>
    $("#deleteAction").click(function () {
        var parent = $(this).parent("div[class='RecruitedJobItem']");
        var child = $(this).find('.input1');
        console.log(child.val());
        parent.fadeOut(1000);
        var url = "${ctx }/sys/recruite/deleteJobAction";
        var param = {"jobId" : child.val()};
        $.post(url, param, function (txt) {
            console.log(txt);
        });
    });
</script>
</body>

</html>
