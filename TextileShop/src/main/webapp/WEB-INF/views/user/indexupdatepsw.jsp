<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link href="${ctx}/images/titleicon.ico" type="image/x-icon" rel="shortcut icon" />
<title>Hathaway—用户中心</title>
<link href="${ctx}/zui/css/zui.css" rel="stylesheet">
<link href="${ctx}/zui/css/zui-theme.css" rel="stylesheet">
<link href="${ctx}/css/my.css" rel="stylesheet">
<link href="${ctx}/css/user.css" rel="stylesheet">
<!--[if lt IE 9]>
<script src="../zui/lib/ieonly/html5shiv.js"></script>
<script src="../zui/lib/ieonly/respond.js"></script>
 <script src="../zui/lib/ieonly/excanvas.js"></script>
<![endif]-->
<!-- CSS布局 -->
<style>
#topDiv {
	width: 100%;
	height: auto;
	background-color: #fff;
}

#midDiv {
	width: 100%;
	height: auto;
	background-color: #fff;
}

#downDiv {
	width: 100%;
	height: auto;
	background-color: #588499;
}

#slidebar {
	position: fixed;
	right: 0;
	bottom: 30%;
	background-color: #fff;
	z-index: 99;
}
</style>
</head>
<body>
	<!--[if lt IE 8]>
<div class="alert alert-danger">您正在使用 <strong>过时的</strong> 浏览器. 是时候 <a href="http://browsehappy.com/">更换一个更好的浏览器</a> 来提升用户体验.</div>
<![endif]-->
	<!-- 顶部 -->
	<div id="topDiv">
		<jsp:include page="./commontopuser.jsp" />
	</div>
	<!-- /顶部 -->
	<!-- 中部 -->
	<div style="min-height: 370px;">
	<!-- 主内容 -->
	<div class="row" style="padding: 20px 0px;">
		<!-- /左边 -->
		<div class="col-xs-2">
			<%-- JSP中的include中page路径里/代表的是项目的根目录 --%>
	           <jsp:include page="./commonMenu.jsp">
	              <jsp:param value="pwd" name="tag"/>
	           </jsp:include>
		</div>
		<!-- /左边 -->

		<!-- 右边 -->
		<div class="col-xs-9">
			<div class="panel">
				<div class="panel-heading">
					<strong><i class="icon-user"></i> 修改密码</strong>
				</div>
				<div class="panel-body">
					<form action="${ctx}/user/modpsw" method="post" id="ajaxForm"
						class="form-horizontal">
						<div class="form-group">
							<label class="col-xs-2 control-label">原密码</label>
							<div class="col-xs-4 required">
								<input type="password" name="oldpsw" id="oldpsw" 
								class="form-control" placeholder="请输入原始密码" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-2 control-label">新密码</label>
							<div class="col-xs-4 required">
								<input type="password" name="newpsw" id="newpsw"
									placeholder="请输入新密码" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-2"></div>
							<div class="col-xs-10">
								<input type="submit" id="submit" class="btn btn-primary"
									style="min-width: 80px" value="保存" data-loading="稍候..." />
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="col-xs-1"></div>
		<!-- /右边 -->
	</div>
	<!-- /主内容 -->
	</div>
	<!-- /中部 -->

	<!-- 底部 -->
	<div id="downDiv">
		<jsp:include page="../commonbottompage.jsp" />
	</div>
	<!-- /底部 -->

	<script src="${ctx}/zui/lib/jquery/jquery.js"></script>
	<script src="${ctx}/zui/js/zui.js"></script>
	<script src="${ctx}/js/my.js"></script>
</body>
</html>