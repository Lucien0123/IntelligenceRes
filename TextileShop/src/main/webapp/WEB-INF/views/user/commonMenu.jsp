<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<nav class="menu" id="mymenu">
  <ul class="nav nav-primary">
    <li class="nav-parent show">
      <a href="javascript:;"> 订单信息</a>
      <ul class="nav">
        <li ${param.tag == 'index' ? "class='active'" : ""}>
        <a href="${ctx}/user/userindexpage"><i class="icon-shopping-cart"></i>
        我的订单<i class="icon-chevron-right"></i></a></li>
        <li ${param.tag == 'address' ? "class='active'" : ""}>
        <a href="${ctx}/user/address"><i class="icon-map-marker"></i>
        地址管理<i class="icon-chevron-right"></i></a></li>
      </ul>
    </li>
    <li class="nav-parent show">                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
      <a href="javascript:;"> 个人信息</a>
      <ul class="nav">
        <li ${param.tag == 'profile' ? "class='active' " : ""}>
        <a href="${ctx}/user/profileinfor"><i class="icon-user"></i>
         个人资料<i class="icon-chevron-right"></i></a></li>
        <li ${param.tag == 'pwd' ? "class='active' " : ""}>
        <a href="${ctx}/user/updatepsw"><i class="icon-eye-open"></i>
         修改密码<i class="icon-chevron-right"></i></a></li>
      </ul>
    </li>
  </ul>
</nav>