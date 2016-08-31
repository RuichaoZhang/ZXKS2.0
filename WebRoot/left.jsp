<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>


<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.min.css" />
		<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/font-awesome.min.css" />
		<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/ace.min.css" />
	</head>
	<body>
	<div class="sidebar" id="sidebar">
		<div class="sidebar-shortcuts" id="sidebar-shortcuts">
			<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
				<button class="btn btn-success">
					<i class=" icon-star"></i>
				</button>

				<button class="btn btn-info">
					<i class=" icon-star"></i>
				</button>

				<button class="btn btn-warning">
					<i class=" icon-star"></i>
				</button>

				<button class="btn btn-danger">
					<i class=" icon-star"></i>
				</button>
			</div>

			<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
				<span class="btn btn-success"></span>

				<span class="btn btn-info"></span>

				<span class="btn btn-warning"></span>

				<span class="btn btn-danger"></span>
			</div>
		</div><!-- #sidebar-shortcuts -->

		<ul class="nav nav-list">
		  <li>
				<a href="${pageContext.request.contextPath}/GradeManagementServlet?operate=findByLike_grade&pageNum=1" target="main">
					<i class="icon-check"></i>
					<span class="menu-text"> 成绩管理 </span>
					<b class="arrow icon-angle-right"></b>
				</a>

				
			</li>
		


			<li>
				<a href="${pageContext.request.contextPath}/ExamineeManagementServlet?operate=findAll_examinee&pageNum=1" target="main" >
					<i class="icon-user"></i>
					<span class="menu-text"> 考生管理 </span>

					<b class="arrow icon-angle-right"></b>
				</a>

				
			</li>


			<li>
				<a href="${pageContext.request.contextPath}/PositionManagementServlet?operate=findAll_pposition&pageNum=1" target="main"  >
					<i class="icon-list"></i>
					<span class="menu-text"> 职位管理 </span>

					<b class="arrow icon-angle-right"></b>
				</a>

				
			</li>


			<li>
				<a href="${pageContext.request.contextPath}/TestRuleManagementServlet?operate=findAll_testRule&pageNum=1"  target="main">
					<i class="icon-file-alt"></i>
					<span class="menu-text"> 试卷管理 </span>

					<b class="arrow icon-angle-right"></b>
				</a>

				
			</li>
				<li>
				<a href="${pageContext.request.contextPath}/TestManagementServlet?operate=findAll_test&pageNum=1" target="main" >
					<i class="icon-tag"></i>
					<span class="menu-text"> 试题管理 </span>

					<b class="arrow  icon-angle-right"></b>
				</a>
			</li>

			<li>
				<a href="${pageContext.request.contextPath}/TestTypeManagerServlet?operate=findAll_testType&pageNum=1" target="main" >
					<i class="icon-move"></i>
					<span class="menu-text"> 试题类型管理 </span>

					<b class="arrow icon-angle-right"></b>
				</a>
			</li>
		</ul><!-- /.nav-list -->
		<div class="sidebar-collapse" id="sidebar-collapse">
			<i class=" icon-cog" ></i>
		</div>
	</div>
</body>
</html>