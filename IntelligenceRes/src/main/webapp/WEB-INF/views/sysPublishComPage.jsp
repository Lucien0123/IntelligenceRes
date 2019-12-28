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
                <i class="icon icon-map-marker"></i>&nbsp;&nbsp;添加园区企业
            </div>
            <!-- 发布新职位表单 -->
            <div class="RightFormBody">
                <form id="addComForm" action="${ctx }/sys/parkcompanyManager/addNewParkCompany"
                      enctype="multipart/form-data" method="post">
                    <div class="form-group has-success">
                        <label>公司名称</label>
                        <input type="text" id="comName" name="comName" class="form-control input-lg"
                               placeholder="请输入新公司的名称">
                    </div>
                    <div class="form-group row has-warning">
                        <div class="col-xs-3">
                            <label>主营业务</label>
                            <input type="text" name="comMainBusiness" class="form-control" placeholder="公司主要主营范围">
                        </div>
                        <div class="col-xs-1"></div>
                        <div class="col-xs-3">
                            <label>融资阶段</label>
                            <select class="form-control" name="comForm">
                                <option value="不需要融资">不需要融资</option>
                                <option value="A轮融资">A轮融资</option>
                                <option value="B轮融资">B轮融资</option>
                                <option value="C轮融资">C轮融资</option>
                                <option value="D轮融资">D轮融资</option>
                                <option value="已上市">已上市</option>
                            </select>
                        </div>
                        <div class="col-xs-1"></div>
                        <div class="col-xs-3">
                            <label>人员规模</label>
                            <select class="form-control" name="comStaffScale">
                                <option value="20人以下">20人以下</option>
                                <option value="20-50人">20-50人</option>
                                <option value="50-100人">50-100人</option>
                                <option value="100-500人">100-500人</option>
                                <option value="500人以上">500人以上</option>
                            </select>
                        </div>
                        <div class="col-xs-1"></div>
                    </div>
                    <div class="form-group">
                        <label>添加时间</label>
                        <input type="text" class="form-control input-lg" disabled="true" value="${curDate}">
                    </div>
                    <div class="form-group has-success">
                        <label>公司网站链接</label>
                        <input type="text" name="comHomepageLink" class="form-control input-lg" placeholder="请输入公司网站链接">
                    </div>
                    <div class="form-group has-success">
                        <label>公司地址</label>
                        <input type="text" name="comAddress" class="form-control input-lg"
                               placeholder="请输入公司地址（×单元×号楼×××室）">
                    </div>
                    <div class="form-group has-success">
                        <label>公司简要描述</label>
                        <input type="text" name="comDesc" class="form-control input-lg" placeholder="请简要描述公司情况">
                    </div>
                    <div class="form-group">
                        <label>公司图标</label>
                        <input type="file" name="comSignal" class="form-control input-lg">
                    </div>
                    <div class="form-group has-warning">
                        <label>添加一个企业管理员帐号（初始登录帐号、密码一致）</label>
                        <input type="text" id="busiAccount" name="busiAccount" class="form-control input-lg"
                               placeholder="请添加一个企业管理员帐号">
                    </div>
                    <div class="form-group has-error">
                        <label>公司标签</label>
                        <div class="JobStatementOl">
                            <ol id="parkcomLabels">
                                <li name="parkcomLabel"><span class="myLable">园区企业</span><i
                                        class="DeleteLiIcon icon icon-remove-sign marginLeft5px"></i></li>
                            </ol>
                        </div>
                        <div class="row">
                            <div class="col-xs-8">
                                <input id="parkcomLabelInput" type="text" class="form-control input-lg"
                                       placeholder="请添加该公司的标签">
                            </div>
                            <div class="col-xs-4">
                                <button onclick="addNewLiForOl()" class="btn btn-lg" type="button">
                                    &nbsp;添&nbsp;加&nbsp;</button>
                            </div>
                        </div>
                        <input type="text" id="parkcomLabelStr" name="parkcomLabelStr" style="display: none;"/>
                    </div>
                    <div class="form-group paddingleft30px margintop30px">
                        <button id="btn_submit" class="btn btn-lg btn-primary submitbutton150" type="button">
                            创建园区企业
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
<script type="text/javascript">
    $(".DeleteLiIcon").click(
            function () {
                console.log("删除条目");
                var parent = $(this).parent();
                parent.remove();
            }
    );

    var addNewLiForOl = function () {
        console.log("添加公司标签:" + $("#parkcomLabelInput").val());
        var ol = $("#parkcomLabels");
        if ($("#parkcomLabelInput").val().length > 0) {
            var temp = '<li><span class="myLable">' + $("#parkcomLabelInput").val() + '</span><i class="DeleteLiIcon icon icon-remove-sign marginLeft5px"></i></li>';
            ol.append(temp);
            $("#parkcomLabelInput").val('');
        }
        var list_labelli = $("#parkcomLabels li span");
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
        }

        $("#parkcomLabelStr").val(labelStr);
        console.log($("#parkcomLabelStr").val());
    }
    $(document).on('click', '.DeleteLiIcon', function () {
        var temp = $(this).parent();
        temp.remove();

    });
    var options = {
        method: 'POST',
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        resetForm: true,
        beforeSubmit: function () {
            console.log("发送异步请求，添加公司");
            var comName = $("#comName").val();
            var busiAccount = $("#busiAccount").val();
            if (comName == "") {
                $.zui.messager.show("请输入公司名称！", {
                    type: 'danger',
                    placement: 'top',
                    time: '3000'
                });
                return false;
            }

            if (busiAccount == "") {
                $.zui.messager.show("请添加一个管理员帐号！", {
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
                $.zui.messager.show("创建企业成功！", {
                    type: 'info',
                    placement: 'top',
                    time: '3000'
                });
            } else if (returndata == 'BUSSINESSADMINEREXIST') {
                $.zui.messager.show("您输入的企业账户名已经存在，请重新输入！", {
                    type: 'danger',
                    placement: 'top',
                    time: '3000'
                });
            } else if (returndata == 'PARKNAMEEXIST') {
                $.zui.messager.show("您输入的企业名称已经存在，请重新输入！", {
                    type: 'danger',
                    placement: 'top',
                    time: '3000'
                });
            } else if (returndata == 'POWERLIMIT') {
                $.zui.messager.show("权限限制，您不能创建新的公司！", {
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
        $("#addComForm").ajaxSubmit(options);
    });
</script>
</body>

</html>
