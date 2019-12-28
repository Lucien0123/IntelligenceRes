// JavaScript Document
//定义js控制当获取鼠标焦点时上移一小段距离的动画

/**
 * 本例子适用于对于5个对象的上移操作，目前实现方法就是为每一个目标都设置一个移动函数
 * 通过对不同对象设定不同的移动函数和定时器，可以避免快速切换移动对象时，产生前一个对象还没有完全落下，下一个对象已经上升。
 * 对外部Div有如下规范
 * <div id="linkDiv" align="center">
    	<table cellspacing="0" cellpadding="0">
    		<tr align="center">
    			<td>
    				<div id="apple1" class="apple1">内容</div>
    			</td>
    			<td>
    				<div id="apple2" class="apple1">内容</div>
    			</td>
    			<td>
    				<div id="apple3" class="apple1">内容</div>
    			</td>
    			<td>
    				<div id="apple4" class="apple1">内容</div>
    			</td>
    			<td>
    				<div id="apple5" class="apple1">内容</div>
    			</td>
    		</tr>
    	</table>
    </div>
 */
	window.onload = function(){
		var oDiv1 = document.getElementById('apple1');
		var oDiv2 = document.getElementById('apple2');
		var oDiv3 = document.getElementById('apple3');
		var oDiv4 = document.getElementById('apple4');
		var oDiv5 = document.getElementById('apple5');
		
		//var timer1;
		oDiv1.onmouseover=function(){startMove1(0, oDiv1);};
		oDiv1.onmouseout= function(){startMove1(15, oDiv1);};
		
		//var timer2;
		oDiv2.onmouseover=function(){startMove2(0, oDiv2);};
		oDiv2.onmouseout= function(){startMove2(15, oDiv2);};
		
		//var timer3;
		oDiv3.onmouseover=function(){startMove3(0, oDiv3);};
		oDiv3.onmouseout= function(){startMove3(15, oDiv3);};
		
		//var timer4;
		oDiv4.onmouseover=function(){startMove4(0, oDiv4);};
		oDiv4.onmouseout= function(){startMove4(15, oDiv4);};
		
		//var timer5;
		oDiv5.onmouseover=function(){startMove5(0, oDiv5);};
		oDiv5.onmouseout= function(){startMove5(15, oDiv5);}
	};
	var timer1 = null;
	function startMove1(iTarget,div){
		clearInterval(timer1);
		var oDiv = div;
		timer1 = setInterval(function(){
			var speed = 0;
			if(oDiv.offsetTop > iTarget){
				speed = -3;
			}
			else {
				speed = 3;
			}
			if(oDiv.offsetTop == iTarget){
				clearInterval(timer1);
			}
			else {
				oDiv.style.top = oDiv.offsetTop+speed+'px';
			}
		}, 10)
	}
	
	var timer2 = null;
	function startMove2(iTarget,div){
		clearInterval(timer2);
		var oDiv = div;
		timer2 = setInterval(function(){
			var speed = 0;
			if(oDiv.offsetTop > iTarget){
				speed = -3;
			}
			else {
				speed = 3;
			}
			if(oDiv.offsetTop == iTarget){
				clearInterval(timer2);
			}
			else {
				oDiv.style.top = oDiv.offsetTop+speed+'px';
			}
		}, 10)
	}
	var timer3 = null;
	function startMove3(iTarget,div){
		clearInterval(timer3);
		var oDiv = div;
		timer3 = setInterval(function(){
			var speed = 0;
			if(oDiv.offsetTop > iTarget){
				speed = -3;
			}
			else {
				speed = 3;
			}
			if(oDiv.offsetTop == iTarget){
				clearInterval(timer3);
			}
			else {
				oDiv.style.top = oDiv.offsetTop+speed+'px';
			}
		}, 10)
	}
	var timer4 = null;
	function startMove4(iTarget,div){
		clearInterval(timer4);
		var oDiv = div;
		timer4 = setInterval(function(){
			var speed = 0;
			if(oDiv.offsetTop > iTarget){
				speed = -3;
			}
			else {
				speed = 3;
			}
			if(oDiv.offsetTop == iTarget){
				clearInterval(timer4);
			}
			else {
				oDiv.style.top = oDiv.offsetTop+speed+'px';
			}
		}, 10)
	}
	var timer5 = null;
	function startMove5(iTarget,div){
		clearInterval(timer5);
		var oDiv = div;
		timer5 = setInterval(function(){
			var speed = 0;
			if(oDiv.offsetTop > iTarget){
				speed = -3;
			}
			else {
				speed = 3;
			}
			if(oDiv.offsetTop == iTarget){
				clearInterval(timer5);
			}
			else {
				oDiv.style.top = oDiv.offsetTop+speed+'px';
			}
		}, 10)
	}