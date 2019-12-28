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
                <i class="icon icon-map-marker"></i>&nbsp;&nbsp;个人信息中心
            </div>
            <div class="panel-body contentDiv">
                <div class="PublicJobButton" align="right">
                    <a class="btn btn-lg btn-primary submitbutton150" href="${ctx}/sys/adminer/modifypwdPage">修改登录密码</a>
                </div>
                <form id="profileInforForm" enctype="multipart/form-data"
                      action="${ctx }/sys/adminer/modifyProfileAction" method="post">
                    <div style="margin-top: 7px;">
                        <div class="row">
                            <div class="col-xs-1 photograph" style="margin-left: 10px;">
                                <!-- 图片尺寸 95*140 -->
                                <div>
                                    <img src="${adminer.headPortrait }" class="imgSize" id="pro_img">
                                </div>
                                <div class="uploadBtnDiv" align="center">
                                    <div class="uploadBack"></div>
                                    <input type="file" id="headPortrait" name="headPortrait" class="uploadInputsty"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <br/>
                    <div class="form-group has-success margintop30px">
                        <label>管理员编号</label>
                        <input type="text" class="form-control input-lg" value="ADMIN10010${adminer.id }"
                               disabled="disabled">
                    </div>
                    <div class="form-group has-success">
                        <label>管理员姓名</label>
                        <input type="text" name="realname" class="form-control input-lg" placeholder="管理员真实姓名"
                               value="${adminer.realname}">
                    </div>
                    <div class="form-group row has-warning">
                        <div class="col-xs-3">
                            <label>所属公司</label>
                            <input type="text" class="form-control input-lg" value="${parkComName }"
                                   disabled="disabled">
                        </div>
                        <div class="col-xs-1"></div>
                        <div class="col-xs-3">
                            <label>职务</label>
                            <input type="text" class="form-control input-lg" value="${adminer.adminerPosition }"
                                   disabled="disabled">
                        </div>
                        <div class="col-xs-1"></div>
                        <div class="col-xs-3">
                            <label>性别</label>
                            <div>
                                <label class="radio-inline">
                                    <input type="radio" name="gender"
                                           value="0" ${adminer.gender == 1 ? "checked='checked'":""}> 男
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="gender"
                                           value="1" ${adminer.gender == 0 ? "checked='checked'":""}> 女
                                </label>
                            </div>
                        </div>
                        <div class="col-xs-1"></div>
                    </div>
                    <div class="form-group has-success">
                        <label>个人联系电话</label>
                        <input class="form-control" name="telphone" rows="3" value="${adminer.telphone }"/>
                    </div>
                    <div class="form-group has-success">
                        <label>备注</label>
                        <textarea class="form-control" rows="3" name="remark">${adminer.remark }</textarea>
                    </div>
                    <div class="form-group has-success">
                        <button class="btn btn-lg btn-primary submitbutton150" type="button" id="btn_submit">
                            修改/保存个人信息
                        </button>
                    </div>
                </form>
            </div>
            <!-- /面板内容 -->
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
    //异步显示头像
    $("#headPortrait").on("change", function () {
        //Ajax异步提交的工程目录下的picCache目录下
        var formData1 = new FormData($("#profileInforForm")[0]);
        $.ajax({
            url: '${ctx }/sys/adminer/syncshow',
            type: 'POST',
            data: formData1,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (returndata) {
                if ("ERROR" == returndata){
                    $.zui.messager.show("请重新上传头像！", {
                        type: 'danger',
                        placement: 'top',
                        time: '3000'
                    });
                } else {
                    console.log("显示头像的路径：" + returndata);
                    $("#pro_img").attr("src", returndata);
                }
            },
            error: function (returndata) {
                $.zui.messager.show("请重新上传头像！", {
                    type: 'danger',
                    placement: 'top',
                    time: '3000'
                });
            }
        });
    });
    //异步表单提交 ，保存到数据库
    var options = {
        method: 'POST',
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        resetForm: false,
        beforeSubmit: function () {
            console.log("发送异步请求，添加公司");

        },
        success: function (returndata) {
            console.log(returndata);
            if (returndata == 'SUCCESS') {
                $.zui.messager.show("资料修改成功！", {
                    type: 'info',
                    placement: 'top',
                    time: '3000'
                });
            } else {
                $.zui.messager.show("资料修改失败！", {
                    type: 'danger',
                    placement: 'top',
                    time: '3000'
                });
            }
        },
        error: function (result) {
            $.zui.messager.show("更新资料失败，请重试！", {
                type: 'danger',
                placement: 'top',
                time: '3000'
            });
        }
    };

    $("#btn_submit").click(function () {
        $("#profileInforForm").ajaxSubmit(options);
    });
</script>
</body>

</html>
