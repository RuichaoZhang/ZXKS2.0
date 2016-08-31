<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<html>
<head>
	<title>考试系统管理中心</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<meta http-equiv=Content-Type content=text/html;charset=utf-8>
</head>
	<frameset rows="45,*"  frameborder="NO" border="0" framespacing="0">
		<frame src="admin_top.jsp"  />
	<frameset cols="190,*" >
	<frame src="left.jsp" />
	<frame src="${pageContext.request.contextPath}/GradeManagementServlet?operate=findByLike_grade&pageNum=1" name="main"  />
	</frameset>
	</frameset>
	<noframes>
		<body></body>
	</noframes>
</html>