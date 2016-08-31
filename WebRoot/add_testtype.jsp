<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.min.css">
	<script type="text/javascript">
			function change(id) {
				var testTypeName = document.getElementById(id);
				var num = testTypeName.value.length;
				if(num>16){
					alert("你输入的试题类型长度超过16");
				}
			}
		</script>
</head>

<body>
<br>
<br>
<br>
<br>
<form action="${pageContext.request.contextPath}/TestTypeManagerServlet" method="get" class="form-horizontal" role="form">
	<div class="form-group">
    <label  class="col-sm-5 control-label">试题类型:</label>
    <div class="col-sm-2">
    <input type="hidden" class="form-control"  name="operate" value="add_testType">
      <input type="text" class="form-control"  name="testTypeName" id="testTypeName" onblur="change('testTypeName')">
	</div>
	</div>
	<center>
	<div class="form-group">
      <button type="submit" class="btn btn-default" onclick="show_confirm()">确认新增</button>
  </div>
  </center>
</form>
<c:if test="${'add_testType' == message.state}">
		<script>
			alert("${message.content}");
		</script>
	</c:if>
</body>
<script type="text/javascript">
function show_confirm()
{
var r=confirm("确认新增试题类型吗？");
if (r==false)
  {
  alert("新增失败");

  }

}
</script>
</html>