<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>罗莱家纺LOVO</title>
    <link href="${ctx}/zui/css/zui.css" rel="stylesheet">
    <link href="${ctx}/zui/css/zui-theme.css" rel="stylesheet">
    <link href="${ctx}/css/my.css" rel="stylesheet">

    <!--[if lt IE 9]>
    <script src="${ctx}/zui/lib/ieonly/html5shiv.js"></script>
    <script src="${ctx}/zui/lib/ieonly/respond.js"></script>
    <script src="${ctx}/zui/lib/ieonly/excanvas.js"></script>
    <![endif]-->
    <style>
        #topDiv {
            width: 100%;
            height: auto;
            background-color: #fff;
        }

        #downDiv {
            width: 100%;
            height: auto;
            background-color: #060E21;
            margin-top: 100px;
        }

        #midDiv {
            width: 100%;
            height: auto;
            background-color: #fff;
            margin-top: 20px;
        }

        #leftdiv {
            width: 800px;
            height: auto;
            float: left;
            border-right: 1px solid #ddd;
            padding-right: 10px;
        }

        #rightdiv {
            width: 402px;
            height: auto;
            float: right;
            font-size: 14px;
            line-height: 25px;
            color: #333;
            padding-left: 10px;
        }
    </style>

</head>
<body>
<div id="topDiv">
    <jsp:include page="./user/commontopuser.jsp"/>
</div>
<!-- /头部结束 -->
<div id="midDiv" align="center">
    <div style="width: 1202px;">
        <div id="leftdiv" align="left">
            <h5>了解我们</h5>
            <br/>
            <div style="width: 800px;" align="center">
                <div class="videoplayer">
                    <div style="width: 600px; height: auto; float: left; margin-left: 80px;">
                        <video id="media" src="${ctx}/media/programming.mp4" width="100%"
                               controls="controls" poster="${ctx}/images/videoPage.png"
                               preload="auto">
                        </video>
                    </div>
                    <div style="clear: both;"></div>
                    <br/><br/>
                    <div style="width: 800px;" align="left">
                        前世情人是周杰伦演唱的歌曲，由2016年5月30日首播，单曲MV由周杰伦与女儿共同
                        创作，“有个女孩叫Hathaway，在她四个月大时弹了一段旋律，我引以为傲，她...是我女儿”，
                        周杰伦将女儿比作前世情人，透露出周杰伦初为人父的心路历程。<br/><br/>
                        这首《前世情人》灵感是来自女儿有次在玩具钢琴上随手弹压几个音符，周董听了把这几个单音，
                        发想出整首歌曲。MV画面都以剪影处理，周杰伦、昆凌和女儿Hathaway都在里头，歌词虽然不是挂周董的名字，
                        但也道尽父亲的心情，其中一段周董弹钢琴，小女孩在旁边拉小提琴和跳舞的画面，应该是周董对女儿的期盼，
                        另外「「谢谢你，不然纯真是什么我早已经遗忘」、「什么爱，不说，就已经存在」「什么爱，望着，就全都明白」
                        描述的都是爸爸从女儿身上得到的感动。
                    </div>
                </div>
            </div>
        </div>
        <!-- /左边结束 -->
        <div id="rightdiv" align="left">
            <div style="border-bottom: 1px solid #999;">
                <h5>联系我们</h5>
                <br/>
                电话咨询：022-23085777 (周一到周日 09:00-24:00) <br/>
                在线客服：小东、西、南、北<br/>
                注册地址：天津市西青区津静路26号天津城建大学 <br/>
                邮编：300384<br/> <br/>
            </div>
            <div style="margin-top: 20px;">
                <h5>注册商标</h5>
                <img src="${ctx}/images/websignal.jpg"
                     style="width: 220px; margin-top: 10px; margin-bottom: 10px;">

            </div>
        </div>
        <!-- /右边结束 -->
    </div>
    <div style="clear: both;"></div>
</div>
<!-- /中间内容结束 -->
<div id="downDiv">
    <jsp:include page="./commonbottompage.jsp"/>
</div>
<!-- /尾部内容结束 -->
</body>
</html>