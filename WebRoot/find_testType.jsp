<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML >
<html>
<head>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/font-awesome.min.css" />
<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/chen.css">
<script type="text/javascript">
	var pageNum = $("#pageNum"); 
	var url = href="${pageContext.request.contextPath}/TestTypeManagerServlet?operate=findAll_testType&pageNum=";
	var liArray = new Array();
	
	function beforePage(){
		$("ul li:first-child").each(function(index,item){
			if(pageNum >= 1){
				$(item).attr({ href: "" + url + pageNum + ""});
				$("" + pageNum + "").appendTo(item);
			}
		});;
	}
	function nextPage(){
		
	}
</script>
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


	<c:if test="${'add_testType' == message.state}">
	<script>
		alert("${message.content}");
	</script>
	</c:if>
	<c:if test="${'update_testType' == message.state}">
	<script>
		alert("${message.content}");
	</script>
	</c:if>
	<c:if test="${'delete_testType' == message.state}">
	<script>
		alert("${message.content}");
	</script>
	</c:if>
	<c:if test="${empty page.records}">
		对不起,您所查询的信息不存在
	</c:if>
<ol class="breadcrumb">
  <li><i class="icon-home home-icon"></i><a href="${pageContext.request.contextPath}/GradeManagementServlet?operate=findAll_grade&pageNum=1">首页</a></li>
  <li class="active">试题类型管理</li>
  
</ol>
<br>
<input type="hidden" id="pageNum" value="${page.pageNum}">

<input type="hidden" id="" value="">
<div  class="font">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

     	 新增试题类型 
		<a href="${pageContext.request.contextPath}/add_testtype.jsp" target="main" class="glyphicon glyphicon-plus">
		</a>
</div>
	<div class="table-responsive">
	   <table class="table table-bordered table-striped table-hover">
		<thead>
			<tr>
				<th>试题类型名称</th>
				<th>操作</th>
			</tr>
	   </thead>
	   <tbody>
	   <%int i=0;%>
	   	<c:forEach items="${page.records}" var="c" varStatus="vs">
			<tr >
			<%i++;%>
				<td>${c.testTypeName}</td>
				<td>
					<input type="hidden" id="id" value="${c.testTypeId}" >
					<a class="glyphicon glyphicon-pencil" href="${pageContext.request.contextPath}/TestTypeManagerServlet?operate=findById_testType&testTypeId=${c.testTypeId}"></a>&nbsp;
					<a class="glyphicon glyphicon-trash" href="#" onclick="show_confirm5()" id="a"></a></td>
					
			</tr>
		</c:forEach>
		
	   </tbody>
	   
	 </table>
	 <center>
				第${page.pageNum }页/共${page.totalPage}页&nbsp;&nbsp;
				<a href="${pageContext.request.contextPath}/TestTypeManagerServlet?operate=findAll_testType&pageNum=1">首页</a>&nbsp;
				<a href="${pageContext.request.contextPath}/TestTypeManagerServlet?operate=findAll_testType&pageNum=${page.pageNum - 1 == 0 ? 1 : page.pageNum - 1}">上一页</a>&nbsp;
				<a href="${pageContext.request.contextPath}/TestTypeManagerServlet?operate=findAll_testType&pageNum=${page.pageNum + 1 >= page.totalPage ? page.totalPage : page.pageNum + 1}">下一页</a>&nbsp;
				<a href="${pageContext.request.contextPath}/TestTypeManagerServlet?operate=findAll_testType&pageNum=${page.totalPage}">尾页</a>&nbsp;
				<select id="s1">
					<c:forEach begin="1" end="${page.totalPage}" var="num">
						<option value="${num}" ${page.pageNum==num} ? 'selected="selected"' : ''> ${num}</option>						
					</c:forEach>
				</select>
				<a href="javascript:jump()">跳转 </a>
				<script type="text/javascript">
					function jump(){
						var num = document.getElementById("s1").value;
						window.location.href="${pageContext.request.contextPath}/TestTypeManagerServlet?operate=findAll_testType&pageNum=" + num;
					}
					function show_confirm5(){
						var id = $("#id").val();
						url="TestTypeManagerServlet?operate=delete_testType&testTypeId=" + id;
						var r=confirm("确认删除shitileixing吗？");
						if (r==false){
							$("#a").attr({href : "#"});
							alert("删除失败");
						}else{
							$("#a").attr({href : url});
						}
					}
				</script>
	</center>
	 <div class="layout_footer">
        <p>Copyright © 2015 - Jar组作品</p>
    </div>
	
	</div>
</body>
</html>