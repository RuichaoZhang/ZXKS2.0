<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.min.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/testRule.js"></script>
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
        	.left_txt{
        	td :padding:10px 0px;
        	}
      </style>
		<script type="text/javascript">
			$().ready(function (){
				$("#addButton").unbind("click");
				$("#addButton").bind("click", function(){
					$("#table").append("<tr width='25%'><td>试题类型</td><td><select name='stlx'  onchange='change(this)' class='form-control'>"+$("#testRuleItem1").html()+"</select></td><td>题目个数：</td><td><input name='stsl' id='stsl' type='number' maxlength='3'  class='form-control'></td><td ><input type='button'  size='20' value='删除' onClick='remove_add(this);' class='form-control'></td><td></td></tr>");
				});
			});	
			function add_string()
			{
				var stlx = new Array();
				var stsl = new Array();
				$("#table tr select[name='stlx']").each(function(index, item){
					stlx.push($(item).val());
				});
				$("#table tr input[name='stsl']").each(function(index, item){
					stsl.push($(item).val());
				});
				$("#formId").action="TestRuleManagementServlet";
				$("#stlxx").val(stlx);
				$("#stsll").val(stsl);
				$("#formId").submit();
			}
			
			function remove_add(element)
			{
				var item = $(element);
				item.parent().parent().remove();
			}
		</script>
      <script type="text/javascript">
			function check(){
				var inputTestRuleName = document.getElementById("testRuleName");
				var inputTestRuleTime = document.getElementById("testRuleTime");
				var inputTestRuleItemNum1 = document.getElementById("testRuleItemNum1");
				var inputStsl = document.getElementById("stsl");
				if(inputTestRuleName.value.length > 200){
					alert("试卷名称长度超过限制,请输入200字符以内");
					return false;
				}
				if(isNaN(inputTestRuleTime.value)){
					alert("时间域为数字类型，请重新输入！");
					return false;
				}
				if(isNaN(inputTestRuleItemNum1.value)){
					alert("试题类型个数为数字类型，请重新输入！");
					return false;
				}
				if(inputStsl&&isNaN(inputStsl.value)){
					alert("试题类型个数为数字类型，请重新输入！");
					return false;
				}
				return true;
			};
		</script>
   </head>
   <body>
   <form onsubmit="return check()" action="${pageContext.request.contextPath}/TestRuleManagementServlet" method="get" id="formId">
	  <table width="100%" border="0" cellpadding="0" cellspacing="0">
		  <tr>
		    <td width="17" valign="top" ></td>
			    <table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" id="table2">
			      <tr>
			      </tr>
			    </table>
		    <td width="16" valign="top" ></td>
		  </tr>	
		  <tr>
		    <td>
		       <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
		      <tr>
		        <td width="53%" valign="top">&nbsp;</td>
		      </tr>
		      <tr>
		        <td >
				  <div align="center">
						<input type="hidden" name="stlx" id="stlxx"/>
						<input type="hidden" name="stsl" id="stsll"/>
						<input type="hidden" name="operate" value="add_testRule" size="20">
							<table class="left_txt">
								<tr style="padding:50px 0px">
									<td>试卷名称:<br></td>
									<td>
										<input type="text" id="testRuleName" value="${testRuleName }" class='form-control' size="20"  placeholder="请输入正确的试卷名称" name="testRuleName" onblur="check('testRuleName')">
									</td>
								<td>	<br><br>	<br></td>
						  		</tr>
		                 		
								<tr>
									<td>试卷时长:</td>
									<td>
										<input type="number" maxlength="3" id="testRuleTime" class='form-control' value="${testRuleTime }" size="20"  placeholder="请输入正确的数字时长" name="testRuleTime" onblur="check('testRuleTime')">
									</td>
									<td>
									min
									</td>
						  		</tr>
							</table>
							</div>
							<br/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<div style="margin-left:449px;">
							
							<table border="0" cellspacing="10" cellpadding="0" id="table" >
								<tr>
									<td colspan="2"></td>
						  		</tr>
						  		
								<tr width="25%">
									<td>
									<label  class=" control-label">试题类型:</label></td>
									<td>
										<select name="stlx" id="testRuleItem1" class='form-control' onchange="change(this)">
					   						<option value="">请选择</option>
				      					 		<c:forEach items="${testTypes}" var="testType">
					     			  				<option value="${testType.testTypeName}" <c:if test="${testType.testTypeName == testRuleItem1}">selected="selected"</c:if>> ${testType.testTypeName}</option>						
				      			 				</c:forEach>
										</select>
									</td>		                   
									<td>
										<label  class=" control-label">题目个数：</label><td >
										<input class='form-control' type="number" id="testRuleItemNum1" value="${testRuleItemNum1 }"  size="5" name="stsl" id="itemNum1"  placeholder="数字">
										<input class='form-control' type="hidden" name="testRuleItem1" id="input1" value="">
											
									</td>
									<td></td>
								</tr>
								<tr>
						 		</tr>
								</tbody>
							</table>
							</div>
							<br><br><br>
						<center>
							<input type="button" class="btn btn-default" id="addButton" size="20" value="增加">
							<input type="button" id="button1" class="btn btn-default" value="确认新增"   size="20" onclick="add_string()">
						</center>
			   </td>
		     </tr> 
		    </table>
		    </td>
		  </tr>
		  <tr>
		  </tr>
	</table>  
	</form>
	<c:if test="${'add_testRule' == message.state}">
			<script>
				alert("${message.content}");
			</script>
	</c:if> 
	<c:if test="${'delete_testRule' == message.state}">
			<script>
				alert("${message.content}");
			</script>
	</c:if> 
	<c:if test="${'update_testRule' == message.state}">
			<script>
				alert("${message.content}");
			</script>
	</c:if> 
  </body>
</html>
