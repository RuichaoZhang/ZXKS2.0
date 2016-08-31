<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<form class="form-horizontal" role="form" method="get"  id="formId" action="${pageContext.request.contextPath}/TestManagementServlet">
			<input type="hidden" name="operate" value="update_test" size="20">
			<input type="hidden" name="testId" value="${test.testId }" size="20">
			<div class="form-group">
		    <label  class="col-sm-5 control-label">试题题目:</label>
		    <div class="col-sm-4">
		      <input id="testSubject" type="text" name="testSubject" size="80" onblur="check('testSubject')" value="${test.testSubject }" class="form-control"  placeholder="请输入试题题目">
		    </div>
		    </div>
		   <div class="form-group">
		    <label  class="col-sm-5 control-label">题目选项1:</label>
		    <div class="col-sm-2">
		      <input id="testItemA" type="text" name="testItemA" size="20" onblur="check('testItemA')" value="${testItem1 }" class="form-control" >
		      <input type="hidden" name="testItemIdA" size="20" value="${testItemId1 }">
		    </div>
		    </div>
			<div class="form-group">
		    <label  class="col-sm-5 control-label">题目选项2:</label>
		    <div class="col-sm-2">
		      <input id="testItemB" type="text" name="testItemB" size="20" onblur="check('testItemB')" value="${testItem2 }" class="form-control"  >
		      <input type="hidden" name="testItemIdB" size="20" value="${testItemId2 }">
		    </div>
		    </div>
			<div class="form-group">
		    <label  class="col-sm-5 control-label">题目选项3:</label>
		    <div class="col-sm-2">
		      <input id="testItemC" type="text" name="testItemC" size="20" onblur="check('testItemC')" value="${testItem3 }" class="form-control" >
		      <input type="hidden" name="testItemIdC" size="20" value="${testItemId3 }">
		    </div>
		    </div>
			<div class="form-group">
		    <label  class="col-sm-5 control-label">题目选项4:</label>
		    <div class="col-sm-2">
		      <input id="testItemD" type="text" name="testItemD" size="20" onblur="check('testItemD')" value="${testItem4 }" class="form-control"  >
		      <input type="hidden" name="testItemIdD" size="20" value="${testItemId4 }">
		    </div>
		    </div>
			<div class="form-group">
		    <label  class="col-sm-5 control-label">正确答案:</label>
		    <div class="col-sm-2">
		      <input id="testItemTrue" type="text" name="testItemTrue" size="20" onblur="check('testItemTrue')" value="${test.testItemTrue}" class="form-control"  >
		    </div>
		    </div>
		 
		   <div class="form-group">
				<label  class="col-sm-5 control-label">试题类型:</label>
					<div class="col-sm-1"> 
						<select name="testTypeName">
							<option value="">请选择</option>
				        	<c:forEach items="${testTypes}" var="testType">
					       		<option value="${testType.testTypeName}" <c:if test="${testType.testTypeName == test.testType.testTypeName}">selected="selected"</c:if>> ${testType.testTypeName}</option>						
				       		</c:forEach>
						</select>
					</div>
		   </div>
		   <div class="form-group">
		    <label  class="col-sm-5 control-label" placeholder="此框中只能填数字">分值:</label>
		    <div class="col-sm-2">
		      <input id="testScore" type="text" name="testScore" size="10"  onblur="check('testScore')" value="${test.testScore}" class="form-control"  >
		    </div>
		    </div>
		   
			<center>
				<div class="form-group">
					<button type="button" value="确认修改" class="btn btn-default" onclick="checkAb()">修改</button>
					
					
					<button class="btn btn-default" >返回</button>
				</div>
			</center>
		</form>
		
		
		</body>
		<script type="text/javascript">
			function checkAb(){
				var inputA = document.getElementById("testItemA");
				var inputB = document.getElementById("testItemB");
				var inputC = document.getElementById("testItemC");
				var inputD = document.getElementById("testItemD");
				var inputTestItemTrue = document.getElementById("testItemTrue");
				if(inputA.value == inputB.value || inputA.value == inputC.value
						|| inputA.value == inputD.value || inputB.value == inputC.value
						|| inputB.value == inputD.value || inputC.value == inputD.value){
					alert("试题选项不能重复!");
				}else if(inputTestItemTrue.value != inputA.value && 
						inputTestItemTrue.value != inputB.value &&
						inputTestItemTrue.value != inputC.value &&
						inputTestItemTrue.value != inputD.value){
					alert("没有匹配的正确答案选项!");
				}else{
					var formId = document.getElementById("formId");
					formId.submit();
				}
			}
			
			function check(id){
				var input = document.getElementById(id);
				if(id == "testSubject"){
					if(input.value.length > 200){
						alert("试题题目长度超过限制,请输入200字符以内");
					}
				}
				if(id == "testItemA"){
					if(input.value.length > 200){
						alert("选项长度超过限制,请输入200字符以内");
					}
				}
				if(id == "testItemB"){
					if(input.value.length > 200){
						alert("选项长度超过限制,请输入200字符以内");
					}
				}
				if(id == "testItemC"){
					if(input.value.length > 200){
						alert("选项长度超过限制,请输入200字符以内");
					}
				}
				if(id == "testItemD"){
					if(input.value.length > 200){
						alert("选项长度超过限制,请输入200字符以内");
					}
				}
				if(id == "testItemTrue"){
					if(input.value.length > 200){
						alert("正确答案长度超过限制,请输入200字符以内");
					}
				}
				if(id == "testScore"){
					if(isNaN(input.value)){
						alert("分值域为数字类型，请重表输入！");
						return;
					}
				}
			};
		</script>
		<c:if test="${'update_test' == message.state}">
			<script>
				alert("${message.content}");
			</script>
	</c:if>
  
  </body>
</html>
