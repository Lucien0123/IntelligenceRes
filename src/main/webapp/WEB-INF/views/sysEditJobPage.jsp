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
                <i class="icon icon-map-marker"></i>&nbsp;&nbsp;发布新职位
            </div>
            <!-- 发布新职位表单 -->
            <div class="RightFormBody">
                <form id="rePublishJobForm" action="${ctx }/sys/recruite/republishJobAction">
                    <div class="form-group has-success">
                        <label>退回原由</label>
                        <div class="BackOpinion">
                            ${backOpinion }
                        </div>
                    </div>
                    <div class="form-group has-success" hidden="hidden">
                        <input name="id" value="${editJob.id}" type="text" class="form-control input-lg"
                               placeholder="请输入发布职位的名称">
                    </div>
                    <div class="form-group has-success">
                        <label>职位名称</label>
                        <input name="jobName" value="${editJob.jobName}" type="text" class="form-control input-lg"
                               placeholder="请输入发布职位的名称">
                    </div>
                    <div class="form-group row has-warning">
                        <div class="col-xs-3">
                            <label>薪资区间</label>
                            <select class="form-control" name="jobSalary">

                                <option ${'不要求'==editJob.jobSalary?"selected='selected'":"" } value="不要求">不要求</option>
                                <option ${'1K-2K'==editJob.jobSalary?"selected='selected'":"" } value="1K-2K">1K-2K
                                </option>
                                <option ${'2K-5K'==editJob.jobSalary?"selected='selected'":"" } value="2K-5K">2K-5K
                                </option>
                                <option ${'5K-10K'==editJob.jobSalary?"selected='selected'":"" } value="5K-10K">5K-10K
                                </option>
                                <option ${'10K-15K'==editJob.jobSalary?"selected='selected'":"" } value="10K-15K">
                                    10K-15K
                                </option>
                                <option ${'15K以上'==editJob.jobSalary?"selected='selected'":"" } value="15K以上">15K以上
                                </option>
                            </select>
                        </div>
                        <div class="col-xs-1"></div>
                        <div class="col-xs-3">
                            <label>学历要求</label>
                            <select class="form-control" name="education">
                                <option ${'不要求'==editJob.education?"selected='selected'":"" } value="不要求">不要求</option>
                                <option ${'大专'==editJob.education?"selected='selected'":"" } value="大专">大专</option>
                                <option ${'本科'==editJob.education?"selected='selected'":"" } value="本科">本科</option>
                                <option ${'硕士'==editJob.education?"selected='selected'":"" } value="硕士">硕士</option>
                                <option ${'博士'==editJob.education?"selected='selected'":"" } value="博士">博士</option>
                            </select>
                        </div>
                        <div class="col-xs-1"></div>
                        <div class="col-xs-3">
                            <label>工作经验</label>
                            <select class="form-control" name="workExpression">
                                <option ${'不要求'==editJob.workExpression?"selected='selected'":"" } value="不要求">不要求
                                </option>
                                <option ${'应届毕业生'==editJob.workExpression?"selected='selected'":"" } value="应届毕业生">
                                    应届毕业生
                                </option>
                                <option ${'3年及以下'==editJob.workExpression?"selected='selected'":"" } value="3年及以下">
                                    3年及以下
                                </option>
                                <option ${'5年'==editJob.workExpression?"selected='selected'":"" } value="3-5年">3-5年
                                </option>
                                <option ${'10年以上'==editJob.workExpression?"selected='selected'":"" } value="10年以上">
                                    10年以上
                                </option>
                            </select>
                        </div>
                        <div class="col-xs-1"></div>
                    </div>
                    <div class="form-group">
                        <label>发布时间</label>
                        <input name="publicTime" type="text" class="form-control input-lg" disabled="true"
                               value="${curDate}">
                    </div>
                    <div class="form-group">
                        <label>职位诱惑</label>
                        <input name="jobAdvantage" value="${editJob.jobAdvantage }" type="text"
                               class="form-control input-lg" placeholder="请简要描述该职位的诱惑">
                    </div>
                    <div class="form-group has-error">
                        <label>岗位职责</label>
                        <div class="JobStatementOl">
                            <ol id="jobStatement">
                                <c:forEach var="statement" items="${statementList}">
                                    <li name="statementName">${statement}<i
                                            class="DeleteLiIcon icon icon-remove-sign marginLeft5px"></i></li>
                                </c:forEach>
                            </ol>
                        </div>
                        <div class="row">
                            <div class="col-xs-8">
                                <input id="jobStatementInput" type="text" class="form-control input-lg"
                                       placeholder="请添加该职位的岗位职责">
                            </div>
                            <div class="col-xs-4">
                                <button onclick="addNewLiForOl(1)" class="btn btn-lg" type="button">
                                    &nbsp;添&nbsp;加&nbsp;</button>
                            </div>
                        </div>
                        <input name="jobStatementStr" id="jobStatementStr" type="text" style="display: none;" value="${editJob.jobStatementStr }">
                    </div>
                    <div class="form-group has-error">
                        <label>任职要求</label>
                        <div class="JobDemandOl">
                            <ol id="jobDemand">
                                <c:forEach var="demand" items="${demandList}">
                                    <li name="demandName">${demand}<i
                                            class="DeleteLiIcon icon icon-remove-sign marginLeft5px"></i></li>
                                </c:forEach>
                            </ol>
                        </div>
                        <div class="row">
                            <div class="col-xs-8">
                                <input id="jobDemandInput" type="text" class="form-control input-lg"
                                       placeholder="请添加该职位的任职要求">
                            </div>
                            <div class="col-xs-4">
                                <button onclick="addNewLiForOl(2)" class="btn btn-lg" type="button">
                                    &nbsp;添&nbsp;加&nbsp;</button>
                            </div>
                        </div>
                        <input name="jobDemandStr" id="jobDemandStr" type="text" style="display: none;" value="${editJob.jobDemandStr }">

                    </div>
                    <div class="form-group paddingleft30px margintop30px">
                        <button id="btn_submit" class="btn btn-lg btn-primary submitbutton150" type="button">重新发布审核
                        </button>
                    </div>
                </form>
            </div>
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
<script src="${ctx}/js/jquery.form.js"></script>
<script>
    $(".DeleteLiIcon").click(
        function () {
            console.log("删除条目");
            var parent = $(this).parent();
            parent.remove();
            updateStatementInputVal();
            updateDemandInputVal();
        }
    );
    
    function updateStatementInputVal() {
        var list_labelli = $("#jobStatement li span");
        var labelStr = '';
        console.log(list_labelli.length);
        for (var i = 0; i < list_labelli.length; i++) {
            var temp = $(list_labelli[i]).text();
            if (temp != undefined || temp != '') {
                console.log(temp);
                if (i == 0) {
                    labelStr += temp;
                } else {
                    labelStr = labelStr + "&br&" + temp;
                }
            } else {

            }
            console.log("-->" + labelStr);
        }
        $("#jobStatementStr").val(labelStr);
        console.log($("#jobStatementStr").val());
    }

    function updateDemandInputVal() {
        var list_demand_labelli = $("#jobDemand li span");
        var labelStrDemand = '';
        console.log(list_demand_labelli.length);
        for (var i = 0; i < list_demand_labelli.length; i++) {
            var temp1 = $(list_demand_labelli[i]).text();
            if (temp1 != undefined || temp1 != '') {
                console.log(temp1);
                if (i == 0) {
                    labelStrDemand += temp1;
                } else {
                    labelStrDemand = labelStrDemand + "&br&" + temp1;
                }
            } else {
            }
            console.log("-->" + labelStrDemand);
        }
        $("#jobDemandStr").val(labelStrDemand);
        console.log($("#jobDemandStr").val());
    }
    
    var addNewLiForOl = function (type) {
        if (type == 1) {
            console.log("添加岗位职责:" + $("#jobStatementInput").val());
            var ol = $("#jobStatement");
            if ($("#jobStatementInput").val().length > 0) {
                var temp = '<li><span class="myLable">' + $("#jobStatementInput").val() + '</span><i class="DeleteLiIcon icon icon-remove-sign marginLeft5px"></i></li>';
                ol.append(temp);
                $("#jobStatementInput").val('');
            }
            updateStatementInputVal();

        } else if (type == 2) {
            console.log("添加任职要求:" + $("#jobDemandInput").val());
            var ol = $("#jobDemand");
            if ($("#jobDemandInput").val().length > 0) {
                var temp = '<li><span class="myLable">' + $("#jobDemandInput").val() + '</span><i class="DeleteLiIcon icon icon-remove-sign marginLeft5px"></i></li>';
                ol.append(temp);
                $("#jobDemandInput").val('');
            }
            updateDemandInputVal();
        }
    }
    $(document).on('click', '.DeleteLiIcon', function () {
        var temp = $(this).parent();
        temp.remove();
        updateStatementInputVal();
        updateDemandInputVal();
    });

    var options1 = {
        method: 'POST',
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        resetForm: false,
        beforeSubmit: function () {
            console.log("发送异步请求，发布新工作");
            var jobName = $("#jobName").val();
            if (jobName == "") {
                $.zui.messager.show("请输入工作名称！", {
                    type: 'danger',
                    placement: 'top',
                    time: '3000'
                });
                return false;
            }
        },
        success: function (returndata) {
            console.log(returndata);
            if (returndata == 'SUCCESS') {
                $.zui.messager.show("发布新职位成功！", {
                    type: 'info',
                    placement: 'top',
                    time: '3000'
                });
            } else if (returndata == 'JOBNAMEEXIST') {
                $.zui.messager.show("发布工作名称已存在，请重新输入！", {
                    type: 'danger',
                    placement: 'top',
                    time: '3000'
                });
            } else if (returndata == 'PLEASESELECTAUDITOR') {
                $.zui.messager.show("请选择任务提交人！", {
                    type: 'danger',
                    placement: 'top',
                    time: '3000'
                });
            } else if (returndata == 'POWERLIMIT') {
                $.zui.messager.show("权限限制，您不能发布职位！", {
                    type: 'danger',
                    placement: 'top',
                    time: '3000'
                });
            }
        },
        error: function (result) {
            $.zui.messager.show("创建失败，请重试！", {
                type: 'danger',
                placement: 'top',
                time: '3000'
            });
        }
    };

    $("#btn_submit").click(function () {
        $("#rePublishJobForm").ajaxSubmit(options1);
    });
</script>
</body>

</html>
