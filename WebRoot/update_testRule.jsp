<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
	
		<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.min.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/pposition.js"></script>
		
		<style type="text/css">
		<!--
		body {
			margin-left: 0px;
			margin-top: 0px;
			margin-right: 0px;
			margin-bottom: 0px;
			
				}
				-->
	#addSubjectForm table  td{
		font-size:12px;
	}
		</style>
		<script type="text/javascript">
			function show_confirm()
			{
			var r=confirm("确认新增试卷吗？");
			if (r==true)
			  {
			  alert("新增成功!");  
			  }
			else
			  {
			  alert("新增失败");
			  }
			}
			var i = 1;
			function show_add()
			{   
			    var a = "hide";
				var b= i.toString();
				var obj = a.concat(b);
			    document.getElementById(obj).removeAttribute("style");
			    i++;
			 }
			function remove_add(a)
			{
				
				document.getElementById(a).style.display="none";
			}
			function msg(){
				var a = "hide";
				var b= i.toString();
				var obj = a.concat(b);
			    var c= document.getElementById(obj);
			         var total = c.name++
			}
			
			function check(){
				var inputA = document.getElementsByName("testRuleTime");
				var inputB = document.getElementsByName("testRuleItemNum");
				for(var i=0;i<inputA.length;i++){
					if(isNaN(inputA[i].value))
					{
						alert("时间域为数字类型，请重新输入！");
						return false;
					}	
				}
				for(var i=0;i<inputB.length;i++){
					if(isNaN(inputB[i].value))
					{
						alert("时间域为数字类型，请重新输入！");
						return false;
					}	
				}
				 return true;
			};
			
		</script>
	</head>

<body>
<c:if test="${'add_testRule' == message.state}">
	<script>
		alert("${message.content}");
	</script>
	</c:if>
	<c:if test="${'update_testRule' == message.state}">
	<script>
		alert("${message.content}");
	</script>
	</c:if>
	<c:if test="${'delete_testRule' == message.state}">
	<script>
		alert("${message.content}");
	</script>
	</c:if>
 <table width="100%" border="0" cellpadding="0" cellspacing="0">

        <td valign="middle"><span class="left_txt">
			<div id="addSubjectForm" align="center">
			<form id="formId"   onsubmit="return check()" action="${pageContext.request.contextPath}/TestRuleManagementServlet" method ="get">
				<% int i = 0; %>
				<table border="0" cellspacing="10" cellpadding="0" width="50%" >
				  <tr>
					<td colspan="2"><FONT color="red"><s:actionerror/></FONT></td>
				  </tr>
				  <tr>
				  	<input class='form-control' type = "hidden" name = "operate" value = "update_testRule"/>
				  	<input type = "hidden" name = "pageNum" value = "1"/>
					<input type = "hidden" name = "testRuleId" value = "${testRule.testRuleId }"/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<td ><label  class=" control-label">试卷名称:</label> </td>
					<td><input class='form-control'  type="text" name = "testRuleName" id="testRuleName" onblur="checkLength('testRuleName')"  value = "${testRule.testRuleName}"/></td>
				 <td><br/><br/><br/><br/><br/></td>
				
				  </tr>
				  
				  <c:forEach items="${testRule.testRuleItemList}" var="sum">
				  <tr>
				  	<input type="hidden" id="testRuleItemId<%=i%>" name="testRuleItemId<%=i%>" value="${sum.testRuleItemId}">
				  </tr>
				  <tr>
				  		<td><label  class=" control-label">试题类型:</label> </td>
					<td><select  class='form-control' name = "testTypeId<%=i%>" id="testTypeId<%=i%>">
								<option  value="">请选择</option>
								<c:forEach items="${testTypes}" var="num">
						     <option value="${num.testTypeId}"<c:if test="${sum.testType.testTypeId == num.testTypeId}">selected="selected"</c:if>> ${num.testTypeName}</option>
						     </c:forEach>
						   </select>
					</td>
					<td>&nbsp;&nbsp;&nbsp;<label  class=" control-label">题目个数：</label> 
					</td>
					<td>
					<input class='form-control'  type="number" maxlength="3" name="testRuleItemNum<%=i%>" value="${sum.testRuleItemNum}" onblur="check()">
					<td>
					个</td>
					<td><br/><br/><br/><br/><br/></td>
					</tr>
					<% i++; %>
					</c:forEach>
				   <tr>
					<td><label  class=" control-label">试卷时长:</label> </td>
					<td>
					<input name="testRuleTime" type="number" class='form-control' maxlength="3" value="${testRule.testRuleTime }" style="text-align=center" name="testRuleTime" onblur="check()"><td>min</td>
                   </tr>
				<tr>
				<td>
			</table>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				  <div class="form-group">
				      <button type="submit" class="btn btn-default">修改</button>
					  <a href="${pageContext.request.contextPath}/TestRuleManagementServlet?operate=findAll_testRule&pageNum=1"> <input class="btn btn-default" type="button" size = "20" value="返回"></a>
				  </div>
				  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			 </form>
			</div>
		</td>
    </table>
</body>
