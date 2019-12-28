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
    <script type="text/javascript" charset="utf-8" src="${ctx}/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctx}/ueditor/ueditor.all.min.js"></script>
    <script type="text/javascript" src="${ctx}/ueditor/lang/zh-cn/zh-cn.js"></script>
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
                <i class="icon icon-map-marker"></i>&nbsp;&nbsp;发布新新闻资讯
            </div>
            <!-- 发布新闻资讯表单 -->
            <div class="RightFormBody">
                <form id="publicNewsForm" action="${ctx }/sys/news/publicNewsAction">
                    <div class="form-group has-success">
                        <label>新闻咨询标题</label>
                        <input name="newsTitle" type="text" class="form-control input-lg" placeholder="请输入发布新闻咨询标题">
                    </div>
                    <div class="form-group row has-warning">
                        <div class="col-xs-3">
                            <label>发布企业</label>
                            <input name="companyName" type="text" class="form-control input-lg" disabled="true"
                                   value="${companyName}">
                        </div>
                        <div class="col-xs-1"></div>
                        <div class="col-xs-3">
                            <label>发布人</label>
                            <input name="publisher" type="text" class="form-control input-lg" disabled="true"
                                   value="${publisher}">
                        </div>
                        <div class="col-xs-1"></div>
                        <div class="col-xs-3">
                            <label>发布时间</label>
                            <input type="text" class="form-control input-lg" disabled="true" value="${publishDate}">
                        </div>
                        <div class="col-xs-1"></div>
                    </div>
                    <div class="form-group">
                        <label>新闻、咨询内容</label>
                        <script type="text/plain" id="myEditor" style="width:100%;height:240px;"></script>
                        <input type="text" id="newsContent" name="newsContent" hidden="hidden" />
                    </div>
                    <c:if test="${isSelectAuditor}">
                        <div class="form-group has-error">
                            <label>选择提交人</label>
                            <select class="form-control input-lg" name="auditorId">
                                <c:forEach var="adminer" items="${adminers }">
                                    <option value="${adminer.id}">${adminer.realname}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </c:if>
                    <div class="form-group paddingleft30px margintop30px">
                        <button class="btn btn-lg btn-primary submitbutton150" type="submit">
                            提请审核发布
                        </button>
                    </div>
                </form>
            </div>
            <!-- /发布新闻资讯表单 -->
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
    var ueObj = UE.getEditor('myEditor');
    $("#publicNewsForm").submit(function () {
        //==========使用jQuery来发送异步请求
        var newsContent = ueObj.getContent();
        $("#newsContent").val(newsContent);
        console.log($("#newsContent").val());
        var url = $(this).attr("action");
        var param = $(this).serialize(); //获取表单中输入的数据
        $.post(url, param, function (txt) {
            if ("SUCCESS" == txt) {
                $.zui.messager.show('新闻咨询已经提交审核！', {type: 'success', placement: 'top', time: '3000'});
            } else if ("NEWSTITLEEXIST" == txt) {
                $.zui.messager.show('新闻资讯标题重复，请重新输入！', {type: 'danger', placement: 'top', time: '3000'});
            } else if ("PLEASESELECTAUDITOR" == txt) {
                $.zui.messager.show('请选择提交申请人！', {type: 'danger', placement: 'top', time: '3000'});
            } else {
                $.zui.messager.show('新闻公告发布失败！', {type: 'danger', placement: 'top', time: '3000'});
            }
        });
        return false; //阻止表单默认的同步提交方式
    });
</script>
</body>

</html>
