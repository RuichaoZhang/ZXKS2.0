<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>无标题文档</title>

<link href="css/chen.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.next_page{width:130px;height:40px;background:url('images/next_page.png') no-repeat  0 0; border:none; margin:30px auto;}
.next_page:hover{
	background:url('images/next_page.png') no-repeat 0  -45.5px;}
.submit{width:130px;height:42.5px;background:url('images/submit.png') no-repeat  0 0; border:none;margin:0 auto;}
.submit:hover{
	background:url('images/submit.png') no-repeat 0  -45.5px;}
	</style>
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
		
		alert("距考试结束时间还有五分钟！");
	
  	document.getElementById('txt').value=m+":"+s ;
  	tt=tt-1;
	setTimeout("timedCount()",1000);
	}
	else{

	  	document.getElementById('txt').value=m+":"+s ;
	  	tt=tt-1;
		setTimeout("timedCount()",1000);
	}
}
	if(tt<=0){
		clearInterval(tt); 
        alert("时间到，考试结束!");  
        submit();
	}
}
</script>
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
</head>

<body>
<div class="top">
</div>
<div class="left">

<div class="left_top">

<span class="ttt">考生姓名:${sessionScope.examinee.examineeName}&nbsp;&nbsp;&nbsp;&nbsp;时间:${examinationPaper.time}min&nbsp;&nbsp;&nbsp;&nbsp;
总题数:${examinationPaper.totalTest}</span>

</div>

<%int j = 0;%>
<div class="test" id="test">

<div class="test_title">
<span class="number"><%=j+1%><i></i></span>
<div class="f-title">

                    <span class="orgTxt">[单选题]</span>
                    <%int i = 0;%>
                      ${test.testSubject}
</div>
</div>
                                    
									<c:forEach items="${test.testItemList}" var="testItem">
										<tr>
											<td colspan="3">
												<input type="radio" id="testItemId<%=j%><%=i%>" name="testItemId<%=j%><%=i%>" value="${testItem.testItemId}" />
												${testItem.testItemContent}<br><br><br>
											</td>
										</tr>
									</c:forEach>
									<%j++;%>
</div>
<div class="test_down">
<div class="test_down_top">
</div>


</div>
</div>
<div class="right">
<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img  src="images/sj-icon2.png" />
<br />
<span ><center style="background:#FFF"> 剩余时间:<span id="txt"></span></center>  </span>
<input type="hidden" name="time" id="time"value="${examinationPaper.time}"> 
<input type="hidden" name="totalTest"value="${examinationPaper.totalTest}"> 
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="" class="next_page" id="next" onclick="page()">
<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="" class="submit" id="submit">


</div>
</body>
</html>