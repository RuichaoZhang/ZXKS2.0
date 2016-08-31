<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/font-awesome.min.css" />
<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/chen.css">
</head>

<body>

		<ol class="breadcrumb">
		  <li><i class="icon-home home-icon"></i><a href="${pageContext.request.contextPath}/GradeManagementServlet?operate=findAll_grade&pageNum=1">首页</a></li>
		  <li class="active">职位管理</li>
		  
		</ol>
		<br>
		<div  class="font">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		      
			新增职位 
			<a  href="${pageContext.request.contextPath}/PositionManagementServlet?operate=find_testRule" target="main" class="glyphicon glyphicon-plus"></a>
		</div>
		<div class="table-responsive">
		<table class="table table-bordered table-striped table-hover">
		<thead>
			<tr>
				<tr>
				<th align="30%">名称</th>
				<th align="30%">试卷</th>
				<th align="30%">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.records}" var="c" varStatus="vs">
					<tr>
						<td>${c.ppositionName}</td>
						<td>${c.testrule.testRuleName}</td>
						<td>
							<a href="${pageContext.request.contextPath}/PositionManagementServlet?operate=jump_updatePposition&ppositionId=${c.ppositionId}&ppositionName=${c.ppositionName}&testruleId=${c.testrule.testRuleId}" class="glyphicon glyphicon-pencil">&nbsp;</a>
							<a href="javascript:void(0);" onclick="show_confirm('PositionManagementServlet?operate=delete_pposition&ppositionId=${c.ppositionId}',this);" class="glyphicon glyphicon-trash"></a>
						</td>
					</tr>
			</c:forEach>
		</tbody>
	</table>
		</div>
		<center>
			第${page.pageNum }页/共${page.totalPage}页&nbsp;&nbsp;
			<a href="${pageContext.request.contextPath}/PositionManagementServlet?operate=findAll_pposition&pageNum=1">首页</a>&nbsp;
			<a href="${pageContext.request.contextPath}/PositionManagementServlet?operate=findAll_pposition&pageNum=${page.pageNum - 1 == 0 ? 1 : page.pageNum - 1}">上一页</a>&nbsp;
			<a href="${pageContext.request.contextPath}/PositionManagementServlet?operate=findAll_pposition&pageNum=${page.pageNum + 1 >= page.totalPage ? page.totalPage : page.pageNum + 1}">下一页</a>&nbsp;
			<a href="${pageContext.request.contextPath}/PositionManagementServlet?operate=findAll_pposition&pageNum=${page.totalPage}">尾页</a>&nbsp;
			<select id="s1">
				<c:forEach begin="1" end="${page.totalPage}" var="num">
					<option value="${num}" ${page.pageNum==num} ? 'selected="selected"' : '' > ${num}</option>						
				</c:forEach>
			</select>
			<a href="javascript:jump()">跳转 </a>
			<script type="text/javascript">
				function jump(){
					var num = document.getElementById("s1").value;
					window.location.href="${pageContext.request.contextPath}/PositionManagementServlet?operate=findAll_pposition&pageNum=" + num;
				}
			</script>	
		</center>
</body>

	<script type="text/javascript">
			function show_confirm(url,a)
			{
			var r=confirm("确认删除职位吗？");
			if (r==false)
			{
			}else
			{
				a.setAttribute("href", url);
			}
			}
			
			function init()
			{
			}
		</script>

</html>