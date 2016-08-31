<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML >
<html>
  <head>
  <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/delete.js"></script>
  <script src="bootstrap-3.3.5-dist/layer/layer.js"></script>
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
		<script>
			function show_confirm5(){
				var r=confirm("确认删除试题吗？");
			}
		</script>

  </head>
  <body>
	  <ol class="breadcrumb">
	  <li><i class="icon-home home-icon"></i><a href="${pageContext.request.contextPath}/GradeManagementServlet?operate=findAll_grade&pageNum=1">首页</a></li>
	  <li class="active">试题管理</li>
	  </ol>
	
	<form method="get" action="${pageContext.request.contextPath}/TestManagementServlet">
	<input type="hidden" name="operate" value="findAll_test" size="20">
    <input type="hidden" name="pageNum" value="1" size="20">
	<table border="0" cellpadding="0" cellspacing="0" width="100%" class="new_chen">
	   <tr>
	   <td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题目名称:
			<input type="text" class="input"  name="testSubject" value="${testSubject }" ></td>
		   
	       <td >正确答案:<input type="text" class="input"  name="testItemTrue" value="${testItemTrue }" ></td>
	       <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;试题类型:
	       <select name="testTypeName">
				<option value="">请选择</option>
				<c:forEach items="${testTypes}" var="testType">
                   	<option value="${testType.testTypeName}" <c:if test="${testType.testTypeName == testTypeName}">selected="selected"</c:if>>${testType.testTypeName}</option>	
		        </c:forEach>
			 </select></td>
			<td>
				<input type="submit"  class="btn btn-info"  value="查询试题" >  
			</td>
	   </tr>
	   
	</table>
	</form>
	</br>
	<table>
	<tr>
		<td align="center">
			<div  class="font">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			新增试题
			<a href="${pageContext.request.contextPath}/TestManagementServlet?operate=add_test" target="main" class="glyphicon glyphicon-plus" ></a>
			</div>
		</td>
			<td align="center">
			<div  class="font">
			下载模板
	 <a href="${pageContext.request.contextPath}/xls/Test_Info.xls" class="glyphicon glyphicon-plus"></a>
			</div>
		</td>
		<td> &nbsp;&nbsp;&nbsp;</td>
		<td align="center">
			<div  class="font">
				批量导入
			</div>
		</td>
		<td>
			<div  class="font">
			<form action="${pageContext.request.contextPath}/TestManagementServlet" enctype="multipart/form-data" method="post" id="upLoad">
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
				<tr >
					<th>题目名称</th>
					<th>选项1</th>
					<th>选项2</th>
					<th>选项3</th>
					<th>选项4</th>
					<th>正确答案</th>
					<th>试题类型</th>
					<th>分值</th>
					<th >操作</th>
				</tr>
		   </thead>
		   <tbody>
		     <c:forEach items="${page.records}" var="test" varStatus="vs">
			  <tr  style="font-size:11px">
			    <td>${test.testSubject }</td>
		    	<c:forEach items="${test.testItemList }" var="testItem">
			        <td>${testItem.testItemContent }</td>
		        </c:forEach>
			    <td>${test.testItemTrue }</td>
			    <td>${test.testType.testTypeName}</td>
			    <td>${test.testScore }</td>
			    <td>
			      <a href="${pageContext.request.contextPath}/TestManagementServlet?operate=update_test&testId=${test.testId}" class="glyphicon glyphicon-pencil"></a>&nbsp;
			      <a href="${pageContext.request.contextPath}/TestManagementServlet?operate=delete_test&testId=${test.testId}" class="glyphicon glyphicon-trash" onclick="show_confirm5()"></a>
			    </td>
             </tr>
			</c:forEach>
		   </tbody>
		 </table>
		</div>
		
			<center>第${page.pageNum }页/共${page.totalPage}页&nbsp;&nbsp;
			
			<a href="${pageContext.request.contextPath}/TestManagementServlet?operate=findAll_test&testSubject=${testSubject }&testItemTrue=${testItemTrue }&testTypeName=${testTypeName }&pageNum=1">首页</a>&nbsp;</li>
			<a href="${pageContext.request.contextPath}/TestManagementServlet?operate=findAll_test&testSubject=${testSubject }&testItemTrue=${testItemTrue }&testTypeName=${testTypeName }&pageNum=${page.pageNum - 1 == 0 ? 1 : page.pageNum - 1}">上一页</a>&nbsp;</li>
			<a href="${pageContext.request.contextPath}/TestManagementServlet?operate=findAll_test&testSubject=${testSubject }&testItemTrue=${testItemTrue }&testTypeName=${testTypeName }&pageNum=${page.pageNum + 1 >= page.totalPage ? page.totalPage : page.pageNum + 1}">下一页</a>&nbsp;</li>
			<a href="${pageContext.request.contextPath}/TestManagementServlet?operate=findAll_test&testSubject=${testSubject }&testItemTrue=${testItemTrue }&testTypeName=${testTypeName }&pageNum=${page.totalPage}">尾页</a>&nbsp;</li>
			
				<select id="s1">
		           	<c:forEach begin="1" end="${page.totalPage}" var="num">
				       <option value="${num}" ${page.pageNum==num} ? 'selected="selected"' : ''> ${num}</option>						
			        </c:forEach>
		         </select>
		         <a href="javascript:jump()">跳转 </a>
	             <script type="text/javascript">
		        	function jump(){
			      	var num = document.getElementById("s1").value;
		            window.location.href="${pageContext.request.contextPath}/TestManagementServlet?operate=findAll_test&testSubject=${testSubject }&testItemTrue=${testItemTrue }&testTypeName=${testTypeName }&pageNum=" + num;
			     }
		         </script>
		
	    
	    <c:if test="${'add_test' == message.state}">
			<script>
				alert("${message.content}");
			</script>
		</c:if>
		<c:if test="${'update_test' == message.state}">
			<script>
				alert("${message.content}");
			</script>
		</c:if>
		<c:if test="${'delete_test' == message.state}">
			<script>
				alert("${message.content}");
			</script>
		</c:if>
		</center>
		 <div class="layout_footer">
        <p>Copyright © 2015 - Jar组作品</p>
    </div>
	</body>
</html>
