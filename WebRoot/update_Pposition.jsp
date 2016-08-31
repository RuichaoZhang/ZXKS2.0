<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examinee.js"></script>
<meta charset="utf-8">

<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.min.css">
</head>

<body>
 <c:if test="${'add_pposition' == message.state}">
	<script>
		alert("${message.content}");
	</script>
	</c:if>
	<c:if test="${'update_pposition' == message.state}">
	<script>
		alert("${message.content}");
	</script>
	</c:if>
	<c:if test="${'delete_pposition' == message.state}">
	<script>
		alert("${message.content}");
	</script>
	</c:if>
<br>
<br>
<br>
<br>
<form action="${pageContext.request.contextPath}/PositionManagementServlet?operate=update_pposition" method = "get" class="form-horizontal" role="form">

	<div class="form-group">
    <label  class="col-sm-5 control-label">职位名称:</label>
    <div class="col-sm-2">
		<input type = "hidden" name = "operate" value = "update_pposition"/>
		<input type = "hidden" name = "pageNum" value = "1"/>
		<input type = "hidden" name = "ppositionId" value ="${pposition.ppositionId}" />
		<input type = "text" name = "ppositionName" id="ppositionName"
		maxlength="16"
		 value = "${pposition.ppositionName}" class="form-control" 
		onblur="check('ppositionName')" placeholder="请输入新增的职位">
    </div>
    </div>
   
 
   <div class="form-group">
		<label  class="col-sm-5 control-label">试题模版:</label>
			<div class="col-sm-2"> 
						<select name = "testruleId" id="testruleId" class="form-control" onblur="checkTestRule('testruleId')">
							<option  value="">请选择</option>
							<c:forEach items="${ppositions}" var="num">
						     <option value="${num.testrule.testRuleId}" <c:if test="${pposition.testrule.testRuleId == num.testrule.testRuleId}">selected="selected"</c:if>> ${num.testrule.testRuleName}</option>						
							</c:forEach>
							</select>
							<br>
			</div>
	   </div>
		<div class="form-group">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="submit" class="btn btn-default" onclick="show_confirm()">修改</button>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="${pageContext.request.contextPath}/PositionManagementServlet?operate=findAll_pposition&pageNum=1"><button  class="btn btn-default"  type="button">返回</button></a>
		</div>
</form>


</body>
<script type="text/javascript">
function show_confirm()
{
var r=confirm("确认修改职位吗？");
if (r==false)
  {
  alert("修改失败");

  }

}
</script>
</html>