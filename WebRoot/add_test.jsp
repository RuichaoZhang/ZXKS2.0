<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   	<meta charset="utf-8">
	<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.min.css">
  </head>
  <body>
		</br>
		</br>
		</br>
		</br>
		<form class="form-horizontal" role="form" method="get" id="formId" action="${pageContext.request.contextPath}/TestManagementServlet">
			<input type="hidden" name="operate" value="add_test" size="20">
			<div class="form-group">
		    <label  class="col-sm-5 control-label">试题题目:</label>
		    <div class="col-sm-4">
		      <input id="testSubject" value="${testSubject }" type="text" name="testSubject" class="form-control"  placeholder="请输入试题题目">
		    </div>
		    </div>
		   <div class="form-group">
		    <label  class="col-sm-5 control-label">题目选项1:</label>
		    <div class="col-sm-2">
		      <input id="testItemA" value="${testItemA }" type="text" name="testItemA" class="form-control" >
		    </div>
		    </div>
			<div class="form-group">
		    <label  class="col-sm-5 control-label">题目选项2:</label>
		    <div class="col-sm-2">
		      <input id="testItemB" value="${testItemB }"type="text" name="testItemB" class="form-control"  >
		    </div>
		    </div>
			<div class="form-group">
		    <label  class="col-sm-5 control-label">题目选项3:</label>
		    <div class="col-sm-2">
		      <input id="testItemC" value="${testItemC }" type="text" name="testItemC" class="form-control" >
		    </div>
		    </div>
			<div class="form-group">
		    <label  class="col-sm-5 control-label">题目选项4:</label>
		    <div class="col-sm-2">
		      <input id="testItemD" value="${testItemD }" type="text" name="testItemD" class="form-control"  >
		    </div>
		    </div>
			<div class="form-group">
		    <label  class="col-sm-5 control-label">正确答案:</label>
		    <div class="col-sm-2">
		      <input id="testItemTrue" value="${testItemTrue }" type="text" name="testItemTrue" class="form-control"  >
		    </div>
		    </div>
		 
		   <div class="form-group">
				<label  class="col-sm-5 control-label">试题类型:</label>
					<div class="col-sm-1"> 
						<select name="testTypeName">
						   <option value="">请选择</option>
					       <c:forEach items="${testTypes}" var="testType">
						       <option value="${testType.testTypeName}" <c:if test="${testType.testTypeName == testTypeName}">selected="selected"</c:if>> ${testType.testTypeName}</option>						
					       </c:forEach>
					</select>
					</div>
		   </div>
		   <div class="form-group">
		    <label  class="col-sm-5 control-label" placeholder="此框中只能填数字">分值:</label>
		    <div class="col-sm-2">
		      <input id="testScore" value="${testScore }" type="text" name="testScore" class="form-control"  >
		    </div>
		    </div>
		   
		 <center>
		  <div class="form-group">
		    
		      <button type="button" id="buttonA" value="确认新增" class="btn btn-default" onclick="check()">确认新增</button>
		    
		  </div>
		  </center>
		</form>
		<c:if test="${'add_test' == message.state}">
			<script>
				alert("${message.content}");
			</script>
		</c:if>
		<script type="text/javascript">
			function check(){
				var inputTestSubject = document.getElementById("testSubject");
				var inputA = document.getElementById("testItemA");
				var inputB = document.getElementById("testItemB");
				var inputC = document.getElementById("testItemC");
				var inputD = document.getElementById("testItemD");
				var inputTestItemTrue = document.getElementById("testItemTrue");
				var inputTestScore = document.getElementById("testScore");
				var inputTestItemTrue = document.getElementById("testItemTrue");
				
				if(inputA.value == inputB.value || inputA.value == inputC.value
						|| inputA.value == inputD.value || inputB.value == inputC.value
						|| inputB.value == inputD.value || inputC.value == inputD.value){
					alert("试题选项不能重复!");
					return;
				}else if(inputTestItemTrue.value != inputA.value && 
						inputTestItemTrue.value != inputB.value &&
						inputTestItemTrue.value != inputC.value &&
						inputTestItemTrue.value != inputD.value){
					alert("没有匹配的正确答案选项!");
					return;
				}
				if(inputTestSubject.value.length > 200){
					alert("试题题目长度超过限制,请输入200字符以内");
					return false;
				}
				if(inputA.value.length > 200){
					alert("选项长度超过限制,请输入200字符以内");
					return false;
				}
				if(inputB.value.length > 200){
					alert("选项长度超过限制,请输入200字符以内");
					return false;
				}
				if(inputC.value.length > 200){
					alert("选项长度超过限制,请输入200字符以内");
					return false;
				}
				if(inputD.value.length > 200){
					alert("选项长度超过限制,请输入200字符以内");
					return false;
				}
				if(inputTestItemTrue.value.length > 200){
					alert("正确答案长度超过限制,请输入200字符以内");
					return false;
				}
				if(isNaN(inputTestScore.value)){
					alert("分值域为数字类型，请重新输入！");
					return false;
				}else{
						var formId = document.getElementById("formId");
						formId.submit();
				}
				return true;
			};
		
		</script>
  </body>
</html>
