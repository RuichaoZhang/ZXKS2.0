<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>考试系统管理中心</title>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
		<meta http-equiv=Content-Type content=text/html;charset=utf-8>
	</head>
<html>
<head>
<meta charset="utf-8">

<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/font-awesome.min.css" />
<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/chen.css">
</head>

<body>


	<form action="${pageContext.request.contextPath}/GradeManagementServlet" method="get">
		<ol class="breadcrumb">
			<li><i class="icon-home home-icon"></i><a href="right.html">首页</a></li>
			<li class="active">成绩管理</li>
		</ol>
		<table border="0" cellpadding="0" cellspacing="0" width="100%" class="new_chen">
			<tr>
				<td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					姓名:<input type="text" class="input"  value="${examineeName}" name="examineeName">
			   		<input type="hidden" name="operate" value="findByLike_grade">
			   		<input type="hidden" name="pageNum" value="1">
				</td>
			   
				<td>
					性别:<select  name="examineeSex">
						    <option  value="" <c:if test="${examineeSex == '请选择'}">selected="selected"</c:if>>请选择</option>
					    	<option value="男" <c:if test="${examineeSex == '男'}">selected="selected"</c:if> >男</option>
					    	<option value="女" <c:if test="${examineeSex == '女'}">selected="selected"</c:if>>女</option>
							</select>
				</td>
				
				<td>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					学校:
					<input type="text" name="examineeSchool" class="input" value="${examineeSchool}">
				</td>
				<td>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					应聘职位:
					<select name="ppositionName">
						<option  value="">请选择</option>
				        <c:forEach items="${grades}" var="c" varStatus="vs">
			            <option value="${c.pposition.ppositionName}" <c:if test="${c.pposition.ppositionName == ppositionName}">selected="selected"</c:if>> ${c.pposition.ppositionName}</option>	
		                </c:forEach>
					</select>
				</td>
				
				<td>范围:<select name="examineeState">
				    	<option selected="selected" value=""<c:if test="${examineeState == '请选择'}">selected="selected"</c:if>>请选择</option>
				    	<option value=2 <c:if test="${examineeState == '2'}">selected="selected"</c:if>>已通过</option>
						<option value=3 <c:if test="${examineeState == '3'}">selected="selected"</c:if>>未通过</option>
						</select>
				</td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input  type="submit" class="btn btn-info" value="查询" > 
				</td>
			</tr>
		</table>
	</form>
	<br>
	<div class="table-responsive">
	<table class="table table-bordered table-striped table-hover">
		<thead>
			<tr>
				<th>姓名</th>
				<th>性别</th>
				<th>学校</th>
				<th>应聘职位</th>
				<th>手机号</th>
				<th>邮箱</th>
				<th>成绩</th>
				<th>满分</th>
			</tr>
	   </thead>
	   <tbody>
			<c:forEach items="${page.records}" var="c" varStatus="vs">
				<tr>
					<td>${c.examinee.examineeName}</td>
					<td>${c.examinee.examineeSex}</td>
					<td>${c.examinee.examineeSchool}</td>
					<td>${c.pposition.ppositionName}</td>
					<td>${c.examinee.examineeTelephone}</td>
					<td>${c.examinee.examineeEmail}</td>
					<td>${c.gradeScore}</td>
					<td>${c.gradeFullmark}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<center>
				第${page.pageNum }页/共 ${page.totalPage}页&nbsp;&nbsp;
				<a href="${pageContext.request.contextPath}/GradeManagementServlet?operate=findByLike_grade&examineeName=${examineeName}&examineeSex=${examineeSex}&examineeSchool=${examineeSchool}&ppositionName=${ppositionName}&examineeState=${examineeState}&pageNum=1">首页</a>&nbsp;
				<a href="${pageContext.request.contextPath}/GradeManagementServlet?operate=findByLike_grade&examineeName=${examineeName}&examineeSex=${examineeSex}&examineeSchool=${examineeSchool}&ppositionName=${ppositionName}&examineeState=${examineeState}&pageNum=${page.pageNum - 1 == 0 ? 1 : page.pageNum - 1}">上一页</a>&nbsp;
				<a href="${pageContext.request.contextPath}/GradeManagementServlet?operate=findByLike_grade&examineeName=${examineeName}&examineeSex=${examineeSex}&examineeSchool=${examineeSchool}&ppositionName=${ppositionName}&examineeState=${examineeState}&pageNum=${page.pageNum + 1 >= page.totalPage ? page.totalPage : page.pageNum + 1}">下一页</a>&nbsp;
				<a href="${pageContext.request.contextPath}/GradeManagementServlet?operate=findByLike_grade&examineeName=${examineeName}&examineeSex=${examineeSex}&examineeSchool=${examineeSchool}&ppositionName=${ppositionName}&examineeState=${examineeState}&pageNum=${page.totalPage}">尾页</a>&nbsp;
				<select id="s1">
					<c:forEach begin="1" end="${page.totalPage}" var="num">
						<option value="${num}" ${page.pageNum==num} ? 'selected="selected"' : ''> ${num}</option>						
					</c:forEach>
				</select>
				
				<a href="javascript:jump()">跳转 </a>
			
				<script type="text/javascript">
					function jump(){
						var num = document.getElementById("s1").value;
						window.location.href="${pageContext.request.contextPath}/GradeManagementServlet?operate=findByLike_grade&&examineeName=${examineeName}&examineeSex=${examineeSex}&examineeSchool=${examineeSchool}&ppositionName=${ppositionName}&examineeState=${examineeState}&pageNum=" + num;
					}
				</script>
	</center>
	</div>
</body>
<script type="text/javascript">
function show_confirm()
{
var r=confirm("确认查询成绩吗？");
if (r==false)
  {
 
  alert("查询失败");
  }
}

</script>
</html>
</html>