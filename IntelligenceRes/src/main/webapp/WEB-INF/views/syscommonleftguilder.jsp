<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="AdminerInfor">
    <c:if test="${sessionScope.cur_adminer.power eq 1}">
        <div class="font18white letterspacing3px">[管理员]</div>
    </c:if>
    <c:if test="${sessionScope.cur_adminer.power eq 2}">
        <div class="font18white letterspacing3px">[园区企业管理员]</div>
    </c:if>
    <div class="AdminerHead" align="center">
        <img class="AdminerHeadImg" src="${ctx}/img/nonamepic.jpg" />
    </div>
    <div class="font12white margintop10px letterspacing3px" align="center">[${sessionScope.cur_adminer.realname}]</div>

    <c:if test="${sessionScope.cur_adminer.power == 1}">
        <div class="font12white" align="center">[ADMIN1307070${sessionScope.cur_adminer.id}]</div>
    </c:if>
    <c:if test="${sessionScope.cur_adminer.power == 2}">
        <div class="font12white" align="center">[BUSSINESS13000${sessionScope.cur_adminer.id}]</div>
    </c:if>
</div>
<div class="GuilderItems margintop30px paddingbottom50px">
    <div class="list-group">
        <a href="${ctx}/sys/task/center" class="list-group-item LeftGuildIistItem">
            <h4 class="list-group-item-heading font18whiteFA">任务列表&nbsp;&nbsp;<i class="icon icon-chevron-right"></i></h4>
            <p class="list-group-item-text font12greyddd">Management of individual tasks.</p>
        </a>
        <a href="${ctx}/sys/recruite/center" class="list-group-item LeftGuildIistItem margintop10px">
            <h4 class="list-group-item-heading font18whiteFA">企业招聘信息管理&nbsp;&nbsp;<i class="icon icon-chevron-right"></i></h4>
            <p class="list-group-item-text font12greyddd">Here for recruiting information maintenance and management.</p>
        </a>
        <a href="${ctx}/sys/news/center" class="list-group-item LeftGuildIistItem margintop10px">
            <h4 class="list-group-item-heading font18whiteFA">园区信息发布管理&nbsp;&nbsp;<i class="icon icon-chevron-right"></i></h4>
            <p class="list-group-item-text font12greyddd">To maintain the park information, announcements here.</p>
        </a>
        <a href="${ctx}/sys/interview/center" class="list-group-item LeftGuildIistItem margintop10px">
            <h4 class="list-group-item-heading font18whiteFA">招聘面试管理&nbsp;&nbsp;<i class="icon icon-chevron-right"></i></h4>
            <p class="list-group-item-text font12greyddd">Manage the job interview management park here</p>
        </a>
        <a href="${ctx}/sys/parkcompanyManager/center" class="list-group-item LeftGuildIistItem margintop10px">
            <h4 class="list-group-item-heading font18whiteFA">园区企业管理&nbsp;&nbsp;<i class="icon icon-chevron-right"></i></h4>
            <p class="list-group-item-text font12greyddd">Manage the enterprise information park here.</p>
        </a>
        <a href="${ctx}/sys/adminer/profilepage" class="list-group-item LeftGuildIistItem margintop10px">
            <h4 class="list-group-item-heading font18whiteFA">个人信息管理&nbsp;&nbsp;<i class="icon icon-chevron-right"></i></h4>
            <p class="list-group-item-text font12greyddd">Manage the personal information here.</p>
        </a>
    </div>
</div>
