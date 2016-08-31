<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>HR管理页面</title>
        <link href="bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
        <link rel="stylesheet" href="bootstrap-3.3.5-dist/css/ace.min.css" />

		<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/chen.css" />

<meta http-equiv=Content-Type content=text/html;charset=utf-8>
<script type="text/javascript">

	function logout_confirm(){
		var r=confirm("确认注销吗？");
		if (r==true){
			alert("注销成功!"); 
	  		window.parent.location.href="${pageContext.request.contextPath}/LoginServlet?operate=logout&user=hr";
		}else{
			alert("注销失败");
		}
}
</script>
</head>
<body>

<div class="navbar navbar-default" id="navbar">
							<img src="bootstrap-3.3.5-dist/images/login.png" width="190" height="45">
							<span class="logo">人力资源管理系统<span>
					<!-- /.brand -->
			
				<div class="navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">
						<li class="light-blue">
							<a href="#" >
								<img class="nav-user-photo" src="bootstrap-3.3.5-dist/images/avatar.png" alt="admin" />
								<span>
									${sessionScope.hr.hrName}
								</span>
								</a>
						</li>
						<li>
							<a href="" onclick="logout_confirm()">注销</a>
						</li>
					</ul><!-- /.ace-nav -->
				</div><!-- /.navbar-header -->
			</div><!-- /.container -->
		</div>
</body>
</html>
