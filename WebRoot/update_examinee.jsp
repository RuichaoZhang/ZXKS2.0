<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/examinee.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<meta charset="utf-8">
	<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.min.css">
	<script type="text/javascript">
	</script>
</head>
<body>
<br>
<br>
<br>
<br>
<c:if test="${'save_all_examinee' == message.state}">
			<script>
				alert("${message.content}");
			</script>
</c:if>
<form action="${pageContext.request.contextPath}/ExamineeManagementServlet" method="get" class="form-horizontal" role="form">

	<div class="form-group">
    <label  class="col-sm-5 control-label">姓名:</label>
    <div class="col-sm-2">
	<input type="hidden" name="operate" value="update_examinee_over">
	<input type="hidden" name="examineeId" value="${examinee.examineeId}">
	<input type="text" value="${examinee.examineeName}" maxlength="16" class="form-control" 
		placeholder="请输入您的姓名" name="examineeName" id="examineeName" onblur="check('examineeName')">
    </div>
    </div>
    <div class="form-group">
		<label  class="col-sm-5 control-label">性别:</label>
			<div class="col-sm-1"> 
				<select  name="examineeSex"  class="form-control">
					<option selected="selected" value="">请选择</option> 
					<option value="男" <c:if test="${examinee.examineeSex == '男'}">selected="selected"</c:if> >男</option>
					<option value="女" <c:if test="${examinee.examineeSex == '女'}">selected="selected"</c:if> >女</option>
				</select>
			</div>
   </div>
   <div class="form-group">
		<label  class="col-sm-5 control-label">学校:</label>
			<div class="col-sm-2"> 
			<input maxlength="16" value="${examinee.examineeSchool}" name="examineeSchool" type="text" class="form-control" id="examineeSchool" onblur="check('examineeName')">
			</div>
   </div>
   <div class="form-group">
		<label  class="col-sm-5 control-label">职位:</label>
			<div class="col-sm-5"> 
				<select name="ppositionName" class="form-control" style="width: 256px; ">
					<option  value="">请选择</option>
					<c:forEach items="${ppositions}" var="c" varStatus="vs">
					<option value="${c.ppositionId}" <c:if test="${c.ppositionName == examinee.pposition.ppositionName}">selected="selected"</c:if>> ${c.ppositionName}</option>	
					</c:forEach>
				</select>
			</div>
   </div>
   <div class="form-group">
    <label  class="col-sm-5 control-label">联系方式:</label>
    <div class="col-sm-2">
      <input type="tel" value="${examinee.examineeTelephone}" name="examineeTelephone" 
      class="form-control"  placeholder="请输入正确的联系方式" id="examineeTelephone" onblur="change('examineeTelephone')">
    </div>
  </div>
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-5 control-label">邮箱:</label>
    <div class="col-sm-2">
     <input type="email" value="${requestScope.examinee.examineeEmail}" name="examineeEmail" id="examineeEmail"  class="form-control"  placeholder="请输入你的邮箱地址" onblur="checkEmail('examineeEmail')">
    </div>
  </div>
 <center>
  <div class="form-group">
      <button type="submit" class="btn btn-default">修改</button>
	   <a href="${pageContext.request.contextPath}/ExamineeManagementServlet?operate=findAll_examinee&pageNum=1" target="main" >
	  	<button type="button" class="btn btn-default">返回</button>
  </div>
  </center>
</form>
</body>
</html>