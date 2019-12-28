<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Hathaway—用户注册</title>
<link href="${ctx }/zui/css/zui.css" rel="stylesheet">
<link href="${ctx }/zui/css/zui-theme.css" rel="stylesheet">
<link href="${ctx }/css/my.css" rel="stylesheet">
<link href="${ctx }/css/user.css" rel="stylesheet">
<!--[if lt IE 9]>
<script src="${ctx }/zui/lib/ieonly/html5shiv.js"></script>
<script src="${ctx }/zui/lib/ieonly/respond.js"></script>
 <script src="${ctx }/zui/lib/ieonly/excanvas.js"></script>
<![endif]-->
<!-- CSS布局 -->
<style>
#topDiv {
	width: 100%;
	height: auto;
	background-color: #fff;
}

#downDiv {
	width: 100%;
	height: auto;
	background-color: #588499;
}
</style>
</head>
<body>
	<!--[if lt IE 8]>
 <div class="alert alert-danger">您正在使用 <strong>过时的</strong> 浏览器. 是时候 <a href="http://browsehappy.com/">更换一个更好的浏览器</a> 来提升用户体验.</div>
<![endif]-->
	<!-- 顶部 -->
	<div id="topDiv">
		<jsp:include page="./user/commontopuser.jsp" />
	</div>
	<!-- /顶部 -->
	<!-- 主内容 -->
	<div class="wrapper" style="min-height: 400px;">
		<div class="panel" style="margin-top: 20px">
			<div class="panel-heading" style="font-size: 18px">
				<strong>用户注册</strong>
			</div>
			<div class="panel-body row">
				<div class="col-xs-3"></div>

				<div class="col-xs-6" style="padding: 30px 60px">
					<div class="panel" style="border: none;">
						<div class="panel-heading" style="background: none;">
							<strong>欢迎注册成为会员</strong>
						</div>
						<div class="panel-body">
							<form action="${ctx}/user/register" method="post"
								id="registerform">
								<div class="form-group hide">
									<div class="alert alert-danger">用户名或密码有误！</div>
								</div>
								<div class="form-group">
									<input type="text" name="username" id="username"
										placeholder="请输入手机号" class="form-control input-lg">
								</div>
								<div class="form-group">
									<input type="password" name="password" id="password"
										placeholder="请输入密码" class="form-control input-lg">
								</div>
								<div class="form-group">
									<input type="password" name="confirmpsw" id="confirmpsw"
										placeholder="请再输入密码" class="form-control input-lg">
								</div>
								<button id="submit" type="submit"
									class="btn btn-primary btn-wider btn-lg"
									style="width: 190px; text-align: center;">注 册</button>
								<c:if test="${!empty msg}">
								      &nbsp;&nbsp;&nbsp;
		            				  <span class="form-group">
		            					<span id="formError" class="alert alert-danger">${msg}</span>
									  </span>
									  <c:remove var="msg" />
								</c:if>
							</form>
						</div>
					</div>
				</div>

				<div class="col-xs-3"></div>

			</div>
		</div>
	</div>
	<!-- /主内容 -->
	<!-- 底部 -->
	<div id="downDiv">
		<jsp:include page="./commonbottompage.jsp" />
	</div>
	<!-- /底部 -->
	<script src="${ctx }/zui/lib/jquery/jquery.js"></script>
	<script src="${ctx }/zui/js/zui.js"></script>
	<script src="${ctx }/js/my.js"></script>
</body>
</html>