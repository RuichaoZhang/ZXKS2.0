<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="utf-8">
	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/pposition.js"></script>
	<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.min.css">
</head>

<body>
<c:if test="${'update_testType' == message.state}">
	<script>
		alert("${message.content}");
	</script>
	</c:if>
<br><br><br><br>
<form action="${pageContext.request.contextPath}/TestTypeManagerServlet" method="get" class="form-horizontal" role="form">
	<div class="form-group">
	    <label  class="col-sm-5 control-label">试题类型:</label>
	    <div class="col-sm-2">
	      <input type="text" name="testTypeName" id="testTypeName" value="${requestScope.testType.testTypeName}" class="form-control" onblur="checkLength('testTypeName')" placeholder="请输入新增的试题类型">
	    </div>
    </div>
    <input type="hidden" name="testTypeId" value="${requestScope.testType.testTypeId}"  size="20" >
	<input type="hidden" name="operate" value="update_testType"  size="20" >
  	<td colspan="2">
	  	<div align="center">
		</div>
	</td>
	<center>
		<div class="form-group">
			<button type="submit" class="btn btn-default" onclick="show_confirm()">修改</button>
			<a href="${pageContext.request.contextPath}/TestTypeManagerServlet?operate=findAll_testType&pageNum=1"><button  class="btn btn-default"  type="button">返回</button></a>
		</div>
	</center>
</form>
</body>
<script type="text/javascript">
function show_confirm()
{
var r=confirm("确认修改试题类型吗？");
if (r==false)
  {
  alert("修改失败");
  }
}
</script>
</html>