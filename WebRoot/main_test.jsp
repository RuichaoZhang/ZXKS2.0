<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>

<title>凯捷在线考试答题界面</title>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<link href="css/main_test.css" rel="stylesheet" type="text/css" />

<style type="text/css">
	.user_login{width:131px;height:40.5px;background:url('images/next_page.png') no-repeat  0 0; border:none; float:left;margin-right:180px;}
	.user_login:hover{
	background:url('images/next_page.png') no-repeat 0  -42.5px;float:left;
}
.user_login5{width:130px;height:42.5px;background:url('images/submit.png') no-repeat  0 0; border:none; float:left; }
	.user_login5:hover{
	background:url('images/submit.png') no-repeat 0  -42.5px;float:left;
}

	.cc{
	width:169px;height:42.5px;
	}
   
	.check{width:169px;height:42.5px;background:url('images/check.png') no-repeat  0 0; border:none; margin-top:80px;margin-left:30px;}
	.check:hover{
	background:url('images/check.png') no-repeat 0  -42.5px;
}
.center_right_down{width:70px;height:70px;background:url('images/return_top.png') no-repeat  0 0; border:none; margin-top:368px; margin-left:80px;}
	.center_right_down:hover{
	background:url('images/return_top.png') no-repeat 0  -70px;
}

</style>
<script src="bootstrap-3.3.5-dist/js/jquery-1.8.3.js"></script>
<script src="bootstrap-3.3.5-dist/layer/layer.js"></script>
<script type="text/javascript">
var tt = 0;
var flag = true;

function timedCount()

{   
if(flag)
{
	tt=parseInt($("#time").val())*60;
	flag = false;
}
if(tt>3600){
var h = Math.floor(tt/3600);
var m = Math.floor((tt-h*3600)/60);
var s = tt%60; 
}
if(tt<=3600){
	
	var m = Math.floor(tt/60);
	var s = tt%60; 
}
       
if(tt>3600){
 
	document.getElementById('txt').value=h+":"+m+":"+s ;
	
	tt=tt-1;
    setTimeout("timedCount()",1000);
}

if(tt>0 && tt<=3600){ 
	
	if(tt==300){
		layer.msg('考试还有五分钟！');
		}
		/*  layer.open({
			    type: 1,
			    title: false,
			    closeBtn: 0,
			    shadeClose: true,
			    skin: 'yourclass',
			    content: '考试还有五分钟'
			}); */
	
  	document.getElementById('txt').value=m+":"+s ;
  	tt=tt-1;
	setTimeout("timedCount()",1000);
	}
	

	if(tt<=0){
		
		clearInterval(tt); 
		$("#form").submit();
	}
}
</script>
<script type="text/javascript">

var pageIndex = 0;
var i = 2;
function page()
{
	
	var num = $("#totalTest").val();
	
	 if(pageIndex>(num-1)){
			layer.msg('已经没有试题！');
		}	
			
			
	/* 	 layer.open({
			    type: 1,
			    title: false,
			    closeBtn: 0,
			    shadeClose: true,
			    skin: 'yourclass',
			    content: '已经没有试题'
			});
		} */
	 if(pageIndex<=(num-1)){
	$(".test_top").each(function(index, item){
		
	    if(index >=pageIndex && index<=pageIndex+1 )
	    {
	    	$(item).show();
	    	
	    }
	   
	    else
	    {
	    	$(item).hide();
	    }
	     i+=2;
 	  });
	 }
	if(pageIndex<=(num-3)){
	pageIndex+=2;
    }
	else{
		
		pageIndex+=3;
	}
}
$(document).ready(function(){
	page();
	  
	
	
   

 });
 




 
 
 function abc()
{
		/* var totalTest = $("#totalTest").val();
		var url = "{pageContext.requst.contextPath}/OnlineTestManagementServlet？totalTest="+totalTest+"";
		for(var i = 0; i< totalTest; i++){
			var testName = $("#testId" + i + "");
			url = url + testName + "=" +$("#" + testName + "").val() ;
			for(var j = 0; j < 4; j++){
				var testItemName = "testId" + i + "" + j + "";
				var testItem = $("input[name='"+testItemName+"']:checked");
				url = url+"&testItemName="+testItem.val();
			};
		}; */
		//询问框
layer.confirm('您确定要交卷吗?', {
    btn: ['确定','取消'] //按钮
}, function(){
    layer.msg('提交成功', {icon: 1});
    $("#form").submit();
}
);
		
	}
</script>
</head>
<body class="all" onLoad="timedCount()">
	<div class="top">
		<img src="images/logo.png" alt="凯捷中国" width="214" height="50" />
	</div>
	
	<div class="center">
		<div class="ttt">
			考生姓名:${sessionScope.examinee.examineeName}&nbsp;&nbsp;&nbsp;&nbsp;时间:${sessionScope.examinationPaper.time}min&nbsp;&nbsp;&nbsp;&nbsp;
			总题数:${sessionScope.examinationPaper.totalTest}
		</div>
		
		<div class="center_left">
           
			<form id="form" action="${pageContext.request.contextPath}/OnlineTestManagementServlet" method="post">
		            	<%int j = 0;%>
						<div id="test">
						<c:forEach items="${sessionScope.examinationPaper.testList}" var="test">
						 
							 <div class="test_top">
								<table class="text">
									<tr>
										<td colspan="3">
										<input type="hidden" id="examineeId"  value="${sessionScope.examinee.examineeId}">
										<input type="hidden" id="totalTest"  value="${sessionScope.examinationPaper.totalTest}">
										<input type="hidden" id="testId<%=j%>" name="testId<%=j%>" value="${test.testId}">
										<div class="test_title">
<span class="number"><%=j+1%><i></i></span>
<div class="f-title">

                    <span class="orgTxt">[单选题]</span>
                    <%int i = 0;%> <%int z = 0;%>
                      ${test.testSubject}
</div>
</div>
									</tr>
									
									<c:forEach items="${test.testItemList}" var="testItem">
									
										<tr>
											<td colspan="3">
											&nbsp;&nbsp;<input type="radio" id="testItemId<%=z%><%=j%>" name="testItemId<%=j%><%=i%>" value="${testItem.testItemId}" />
												<label id="huan" for="testItemId<%=z%><%=j%>">${testItem.testItemContent}</label><br><br><br>
												<%z++;%>
											</td>
										</tr>
									</c:forEach>
									<%j++;%>
								</table>
							</div>
						</c:forEach>
						</div>
						<input type="hidden" name="time" id="time"
							value="${sessionScope.examinationPaper.time}"> 
						<input type="hidden" name="totalTest"
							value="${sessionScope.examinationPaper.totalTest}"> 
						<input
							type="button" class="user_login" id="next" onclick="page()"> 
							<input type="button" value="" class="user_login5" onclick="abc();">
					</form>
				</div>
	<div class="center_right">
		<div class="center_right_top">
		<br>
<center><img  src="images/sj-icon2.png" /></center>
<br />
			<center><input type="text" id="txt"  readonly="readonly" ></center>
		</div>
		<div class="cc">
		
		</div>
		<a href="#"><div class="center_right_down"></div></a>

	</div>
</div>
</body>
</html>
