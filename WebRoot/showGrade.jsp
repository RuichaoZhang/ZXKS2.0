<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>展示成绩</title>
		<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
		<link type="text/css" href="css/styles.css" rel="stylesheet" media="all" />
		<meta http-equiv='Refresh' content='5; url=${pageContext.request.contextPath}/login.jsp'>
	</head>
	<body>
		<div id="menu-holder">
			<div>
				<ul class="menu">
					<br>
					得出结果
				</ul>
			</div>
		<div id="corner-left">Corner left</div>
		<div id="corner-right">Corner right</div>
		<div class="content">
			${sessionScope.examinee.examineeName}同学，在本次考试中获得: ${grade}分！<br><br><br>
			请您耐心等待我们的给您的通知<br><br><br>
			该页面在
				<span id="time"></span>
				秒后自动跳转
			
		</div>
		</div>
		<script type="text/javascript">
			var times = 6;
			clock();
			function clock(){
				window.setTimeout('clock()',1000);
				times=times-1;
				var time = document.getElementById('time');
				time.innerHTML = times;
			}			
		</script>
	
		<table>
		</table>
	</body>
</html>
