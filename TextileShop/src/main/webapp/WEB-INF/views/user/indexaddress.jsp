<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
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
<div class="alert alert-danger">您正在使用 <strong>过时的</strong> 
浏览器. 是时候 <a href="http://browsehappy.com/">更换一个更好的浏览器</a> 
来提升用户体验.</div>
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
				<jsp:param value="address" name="tag" />
			</jsp:include>
		</div>
		<!-- /左边 -->

		<!-- 右边 -->
		<div class="col-xs-9">
			<div class="panel">
				<div class="panel-heading">
					<strong><i class="icon-map-marker"></i> 地址管理</strong>
					<div class="panel-actions">
						<button class="btn btn-primary" data-toggle="modal"
							data-target="#addAddressModal">添加新地址</button>
					</div>
				</div>
				<table class="table table-striped tablesorter">
					<thead>
						<tr class="text-center">
							<td style="width: 80px">收件人</td>
							<td style="width: 110px">电话</td>
							<td>详情地址</td>
							<td style="width: 70px">邮编</td>
							<td style="width: 80px">默认地址</td>
							<td style="width: 170px">操作</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${addresslist}" var="address">
							<tr class="text-center">
								<td>${address.contact}</td>
								<td class="text-left">${address.mobile}</td>
								<td class="text-center">${address.street}</td>
								<td>${address.zipcode}</td>
								<td>${address.defaultvalue?"是":"否"}</td>
								<td><a
									href="${ctx}/user/defaultaddress?addressid=${address.id}"
									${address.defaultvalue?'class="disabled"':""}>设为默认</a>&nbsp;&nbsp;
									<a href="${ctx}/user/updateaddress?addressid=${address.id}"
									class="editHref">编辑</a>&nbsp;&nbsp; <a
									href="${ctx}/user/deleteaddress?addressid=${address.id}"
									class="deleteHref">删除</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
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
	<!-- 新增地址的对话框 -->
	<div class="modal fade" id="addAddressModal">
		<div class="modal-dialog">
			<form action="${ctx}/user/addAddress" method="post"
				id="addressForm" class="form-horizontal">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
						</button>
						<h4 class="modal-title">新增地址</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label class="col-xs-2 control-label">收货人</label>
							<div class="col-xs-4 required">
								<input type="text" name="contact" placeholder="收货人姓名" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-2 control-label">电话</label>
							<div class="col-xs-4 required">
								<input type="text" name="mobile" placeholder="手机号"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-2 control-label">地址</label>
							<div class="col-xs-9 required">
								<input type="text" name="street" placeholder="详细地址"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-2 control-label">邮编</label>
							<div class="col-xs-4 required">
								<input type="text" name="zipcode" placeholder="邮政编码" class="form-control" />
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="reset" id="resetBtn" class="btn btn-default">重置</button>
						<button type="submit" id="submitBtn" class="btn btn-primary"
							style="min-width: 80px">保存</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- /新增地址 -->
	<!-- 编辑地址的对话框 -->
	<div class="modal fade" id="editAddressModal">
		<div class="modal-dialog">
			<form id="editAddressForm" action="" method="post"
				class="form-horizontal">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
						</button>
						<h4 class="modal-title">编辑地址信息</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label class="col-xs-2 control-label">收货人</label>
							<div class="col-xs-4 required">
								<input type="text" name="contact" placeholder="收货人姓名" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-2 control-label">电话</label>
							<div class="col-xs-4 required">
								<input type="text" name="mobile" placeholder="手机号"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-2 control-label">地址</label>
							<div class="col-xs-9 required">
								<input type="text" name="street" placeholder="详细地址"
									class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-2 control-label">邮编</label>
							<div class="col-xs-4 required">
								<input type="text" name="zipcode" placeholder="邮政编码" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-2 control-label">默认地址</label>
							<div class="col-xs-4">
								<label class="radio-inline"><input type="radio"
									name="defaultvalue" value="true" /> 是 </label> <label
									class="radio-inline"><input type="radio"
									name="defaultvalue" value="false" /> 否 </label>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="reset" class="btn btn-default">重置</button>
						<button type="submit" class="btn btn-primary"
							style="min-width: 80px">保存</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- /编辑地址的对话框 -->
	<!-- 删除的提示对话框 -->
	<div class="modal fade" id="deleteModal">
	<div class="modal-dialog modal-sm">
	  <div class="modal-content">
	    <div class="modal-header">
	      <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">关闭</span></button>
	      <h4 class="modal-title">不可恢复删除，您确定删除吗？</h4>
	    </div>
	    <div class="modal-footer">
	      <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	      <button type="button" id="doDelete" class="btn btn-primary" style="min-width: 80px">确定</button>
	    </div>
	  </div>
	</div>
	</div>
	<!-- /删除的提示对话框  -->

	<script src="${ctx}/zui/lib/jquery/jquery.js"></script>
	<script src="${ctx}/zui/js/zui.js"></script>
	<script src="${ctx}/js/my.js"></script>
    <script>
        /***********处理删除的问题*************/
        var url = null;
    	$(".deleteHref").click(function(){
    		//打开对话框
    		$('#deleteModal').modal('show');
    		
    		//获取当前超链接的地址
    		url = $(this).attr("href");
    		//关闭原有的提交事件
    		return false;
    	});
    	
    	//为删除对话框中的确定按钮添加事件
    	$("#doDelete").click(function(){
    		//执行删除操作提示，
    		if(url){
    			/* 执行到这里说明用户单击的删除的链接，弹出对话框
    			        所以当前超链接必定有值
    			        执行结束后关闭对话框 */
    			location.assign(url);
    		}
    		$('#deleteModal').modal('hide');
    		//取消默认的form表单提交的事件 
    		return false;
    	});
    	
    	/************处理编辑的问题************/
    	$(".editHref").click(function(){
    		var url = $(this).attr("href");
    		
    		//获取当前行的数据
    		var $tds = $("td", $(this).closest("tr"));
    		var contact = $($tds[0]).text();
    		var mobile = $($tds[1]).text();
    		var street = $($tds[2]).text();
    		var zipcode = $($tds[3]).text();
    		var dv = $($tds[4]).text() == '是' ? true : false;
    		
    		$("input[name='contact']", "#editAddressForm").val(contact);
    		$("input[name='mobile']", "#editAddressForm").val(mobile);
    		$("input[name='street']", "#editAddressForm").val(street);
    		$("input[name='zipcode']", "#editAddressForm").val(zipcode);
    		if(dv){
    			$("input[value='true']", "#editAddressForm").prop("checked", true);
    		}else{
    			$("input[value='false']", "#editAddressForm").prop("checked", true);
    		}
    		$("#editAddressForm").attr("action", url);
    		
    		//打开编辑的对话框
    		$('#editAddressModal').modal('show');
    		
    		return false;
    	});
    </script>
</body>
</html>