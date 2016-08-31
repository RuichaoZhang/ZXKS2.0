<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link type="text/css" href="css/styles.css" rel="stylesheet" media="all" />

<script type="text/javascript">
	function forward(){
		var url = "${pageContext.request.contextPath}/LoginServlet?operate=logout&user=examinee";
		window.location.href=url;
	}
</script>
</head>

<body>
<div id="menu-holder">
<div>
<ul class="menu">
<br>
考生须知
</ul>
</div>
  <div id="corner-left">Corner left</div>
  <div id="corner-right">Corner right</div>

<div class="content_in">
<p style="font-size:20px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;欢迎您，${sessionScope.examinee.examineeName}同学:</p><br/>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1:禁止在键盘中按F5违规操作，否则会自动交卷。
<br/><br/><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2:考试过程中，出现考试中止的异常情况则可向考监管人员提出申请。
<br/><br/><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3:提交试卷时间过长时，不要进行任何操作，尤其是退出系统的操作。
<br/><br/><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4:我们会给您发送一封验证邮件,请输入正确的验证码。
<br/><br/><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5:请认真作答每一页的 题目,点击提交后不能再退回,每个人的答题机会只有一次,请珍惜机会。

</div>


<!--end menu-holder-->
<div class="end">
<c:if test="${'verificationCode' == message.state}">
	<script>
		alert("${message.content}");
	</script>
	</c:if>
<form action="${pageContext.request.contextPath}/OnlineTestManagementServlet" method="get" >
	<input type="hidden" name="operate" value="getExaminationPaper">
	<input type="submit" class="btnn" value="同意以上规则并开始答题">
	
	<label>请输入验证码&nbsp;:&nbsp;&nbsp;</label><input type="text" name="verificationCode" value="">

	
	
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

	<input type="button" id="exit" value="退出"   class="btn" onclick="forward()">
</form>
</div>
</div>
</body>
</html>
