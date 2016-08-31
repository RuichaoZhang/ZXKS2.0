<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examinee.js"></script>
<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.min.css">
</head>

<body>
<br>
<br>
<br>
<br><script type="text/javascript">
	var ppositionName = document.getElementById(pname).value;
</script>
<script type="text/javascript">
	function size(){
		//var ppositionName = document.getElementById("pname").value;
		//var size = ppositionName.size();
		alert("aa");
		/*if(size>16){
			alert("您输入的职位长度超过16位,请重新输入");
		}*/
	}
</script>
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
<form action="${pageContext.request.contextPath}/PositionManagementServlet?operate=add_pposition" method = "get" class="form-horizontal" role="form">

	<div class="form-group">
    <label  class="col-sm-5 control-label">职位名称:</label>
    <div class="col-sm-2">
    
    	<input type = "hidden" name = "operate" value = "add_pposition"/>
		<input type = "hidden" name = "pageNum" value = "1"/>
		<input type="text" name = "ppositionName" id = "pname" 
		value="${ppositionName }" maxlength="16"
		 class="form-control"  placeholder="请输入新增的职位" onblur="check('pname')">
    </div>
    </div>
   <div class="form-group">
		<label  class="col-sm-5 control-label">试题模版:</label>
			<div class="col-sm-2"> 
			<select name="testruleId" class="form-control" id="testruleId" class="form-control" onblur="checkTestRule('testruleId')">
-				<option  value="">请选择</option>
				<c:forEach items="${ppositions}" var="num">
					<option value="${num.testrule.testRuleId}"> ${num.testrule.testRuleName}</option>						
				</c:forEach>
			</select>
			</div>
   </div>
 <center>
  <div class="form-group">
    
      <button type="submit" class="btn btn-default" onclick="show_confirm()">确认新增</button>
    
  </div>
  </center>
</form>


</body>
<script type="text/javascript">
	function show_confirm()
	{
	var r=confirm("确认新增职位吗？");
	if (r==false)
	  {
	  alert("新增失败");
	
	  }
	
	}
</script>
</html>