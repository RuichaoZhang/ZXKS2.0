<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examinee.js"></script>
<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/font-awesome.min.css" />
<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/chen.css">
<style type="text/css">
	/*底部版权*/
.layout_footer{
    position: absolute;
    bottom: 0px;
    font-size: 12px;
    background: #ffffff;
    text-align: center;
    border:1px solid #eee;
    width: 100%;
    height: 30px;
}
</style>
</head>

<body>

<ol class="breadcrumb">
  <li><i class="icon-home home-icon"></i><a href="right.html">首页</a></li>
  <li class="active">考生管理</li>
  
</ol>
	<c:if test="${'add_examinee' == message.state}">
	<script>
		alert("${message.content}");
	</script>
	</c:if>
	<c:if test="${'update_examinee' == message.state}">
	<script>
		alert("${message.content}");
	</script>
	</c:if>
	<c:if test="${'delete_examinee' == message.state}">
	<script>
		alert("${message.content}");
	</script>
	</c:if>
	<c:if test="${'upload_examinee' == message.state}">
	<script>
		alert("${message.content}");
	</script>
	</c:if>
<form action="${pageContext.request.contextPath}/ExamineeManagementServlet" method="get" class="ooo">
<table border="0" cellpadding="0" cellspacing="0" width="100%" class="new_chen">
	<tr>
	<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   		姓名:
   		<input type="hidden" name="operate" value="findByLike_examinee">
  		<input type="hidden" name="pageNum" value="1">
   		<input type="text"  name="examineeName"  class="input" value="${examineeName}"></td>
	<td>性别:
	</td>
	<td>
		<select  name="examineeSex" class="form-control">
      		<option selected="selected" value="">请选择</option> 
			<option value="男" <c:if test="${examineeSex == '男'}">selected="selected"</c:if> >男</option>
			<option value="女" <c:if test="${examineeSex == '女'}">selected="selected"</c:if>>女</option>
		</select>
	</td>
	<td>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;应聘职位:
	</td>
	<td>
		<select name="ppositionName" class="form-control">
			<option  value="">请选择</option>
			<c:forEach items="${ppositions}" var="c" varStatus="vs">
				<option value="${c.ppositionName}" <c:if test="${c.ppositionName == ppositionName}">selected="selected"</c:if>> ${c.ppositionName}</option>	
			</c:forEach>
		</select>
	</td>
	<td>
		<input type="submit"  class="btn btn-info"  value="查询考生">   
	</td>
   </tr>
</table>
</form>
<br>
<table>
	<tr>
		<td align="center">
			<div  class="font">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			新增考生
			<a href="${pageContext.request.contextPath}/ExamineeManagementServlet?operate=add_examinee" target="main" class="glyphicon glyphicon-plus" ></a>
			</div>
		</td>
			<td align="center">
			<div  class="font">
			下载模板
			<a href="${pageContext.request.contextPath}/xls/Examinee_Info.xls" target="main" class="glyphicon glyphicon-plus"></a>
			</div>
		</td>
		<td> &nbsp;&nbsp;&nbsp;</td>
		<td align="center">
			<div  class="font">
				批量导入
			</div>
		</td>
		<td>
			<form action="${pageContext.request.contextPath}/ExamineeManagementServlet" enctype="multipart/form-data" method="post" id="upLoad">
			<div  class="font">
			<input type="file" name="file" id="inputfile" >
			</div>
		</td>
		<td>
		<input type="submit"  class="btn btn-sm btn-primary"  value="确认导入">
		</form>   
	    </td>
	</tr>
</table>
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
				<th>操作</th>
		   
			</tr>
	   </thead>
	   <tbody>
	   <c:forEach items="${page.records}" var="c" varStatus="vs">
			<tr>
				<td>${c.examineeName}</td>
				<td>${c.examineeSex}</td>
				<td>${c.examineeSchool}</td>
				<td>${c.pposition.ppositionName}</td>
				<td>${c.examineeTelephone}</td>
				<td>${c.examineeEmail}</td>
				
				<td>
				<input type="hidden" value="${c.examineeId}" id="id">
					<a href="${pageContext.request.contextPath}/ExamineeManagementServlet?operate=update_examinee&examineeId=${c.examineeId}" class="glyphicon glyphicon-pencil"></a> 丨
                     <a id="a" href="${pageContext.request.contextPath}/ExamineeManagementServlet?operate=delete_examinee&examineeId=${c.examineeId}" class="glyphicon glyphicon-trash" onclick="show_confirm5()"></a></td>
			</tr>
		</c:forEach>
	   </tbody>

	 </table>
	</div>
		<center>第${page.pageNum }页/共${page.totalPage}页&nbsp;&nbsp;
		<a href="${pageContext.request.contextPath}/ExamineeManagementServlet?operate=findByLike_examinee&examineeName=${examineeName}&examineeSex=${examineeSex}&ppositionName=${ppositionName}&pageNum=1">首页</a>&nbsp;
					<a href="${pageContext.request.contextPath}/ExamineeManagementServlet?operate=findByLike_examinee&examineeName=${examineeName}&examineeSex=${examineeSex}&ppositionName=${ppositionName}&pageNum=${page.pageNum - 1 == 0 ? 1 : page.pageNum - 1}">上一页</a>&nbsp;
					<a href="${pageContext.request.contextPath}/ExamineeManagementServlet?operate=findByLike_examinee&examineeName=${examineeName}&examineeSex=${examineeSex}&ppositionName=${ppositionName}&pageNum=${page.pageNum + 1 <= page.totalPage ? page.pageNum + 1 : page.totalPage}">下一页</a>&nbsp;
					<a href="${pageContext.request.contextPath}/ExamineeManagementServlet?operate=findByLike_examinee&examineeName=${examineeName}&examineeSex=${examineeSex}&ppositionName=${ppositionName}&pageNum=${page.totalPage}">尾页</a>&nbsp;
		<select id="s1">
			<c:forEach begin="1" end="${page.totalPage}" var="num">
				<option value="${num}" ${page.pageNum==num} ? 'selected="selected"' : ''> ${num}</option>						
			</c:forEach>
		</select>
		<a href="javascript:jump()">跳转 </a>
		<script type="text/javascript">
			function jump(){
				var num = document.getElementById("s1").value;
				window.location.href="${pageContext.request.contextPath}/ExamineeManagementServlet?operate=findAll_examinee&pageNum=" + num;
			}
		</script>
		</center>
	  <div class="layout_footer">
        <p>Copyright © 2015 - Jar组作品</p>
    </div>
</body>

<script src="bootstrap-3.3.5-dist/js/jquery-1.8.3.js"></script>
<script src="bootstrap-3.3.5-dist/layer/layer.js"></script>
<script type="text/javascript">
function show_confirm(){
var r=confirm("确认查询考生吗？");
if (r==false)
  {
  alert("查询失败");
  }
}
</script>
</html>