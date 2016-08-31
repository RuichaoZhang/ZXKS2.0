<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/examinee.js"></script>
<meta charset="utf-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.min.css">
</head>

<body>
<br>
<br>
<br>
<br>
<form action="${pageContext.request.contextPath}/ExamineeManagementServlet"
										method="get" class="form-horizontal" role="form">

	<div class="form-group">
    <label  class="col-sm-5 control-label">姓名:</label>
    <div class="col-sm-3">
    	<input type="hidden" name="operate" value="add_examinee_over">
		<input type="text" name="examineeName" value="${examineeName}" class="form-control"  maxlength="16" placeholder="请输入您的姓名"
		id="examineeName" onblur="check('examineeName')"
		>
    </div>
    </div>
    <div class="form-group">
		<label  class="col-sm-5 control-label">性别:</label>
			<div class="col-sm-2"> 
				<select  name="examineeSex"  id="examineeSex" class="form-control" onblur="checkSex('examineeSex')">
					<option selected="selected" value="">请选择</option> 
					<option value="男" <c:if test="${examineeSex == '男'}">selected="selected"</c:if> >男</option>
					<option value="女" <c:if test="${examineeSex == '女'}">selected="selected"</c:if> >女</option>
				</select>
			</div>
   </div>
   <div class="form-group">
		<label  class="col-sm-5 control-label">学校:</label>
			<div class="col-sm-3"> 
				<input type="text" name="examineeSchool"  class="form-control" maxlength="16"
				id="examineeSchool" placeholder="请输入正确的学校名称" onblur="check('examineeSchool')" value="${examineeSchool}"
				>
			</div>
   </div>
   <div class="form-group">
		<label  class="col-sm-5 control-label">职位:</label>
			<div class="col-sm-2"> 
				<select name="ppositionName" class="form-control">
			<option  value="">请选择</option>
			<c:forEach items="${ppositions}" var="c" varStatus="vs">
				<option value="${c.ppositionId}" <c:if test="${c.ppositionName == ppositionName}">selected="selected"</c:if>> ${c.ppositionName}</option>	
			</c:forEach>
		</select>
		</div>
   </div>
   <div class="form-group">
    <label  class="col-sm-5 control-label">联系方式:</label>
    <div class="col-sm-3">
      <input type="tel"  name="examineeTelephone" class="form-control" maxlength="16" placeholder="请输入正确的联系方式"
	  id="examineeTelephone" onblur="checkTel('examineeTelephone')" value="${examineeSchool}"
	  >
    </div>
  </div>
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-5 control-label">邮箱:</label>
    <div class="col-sm-3">
     <input type="text" name="examineeEmail" class="form-control"  placeholder="请输入你的邮箱地址"
	 id="examineeEmail" value="${examineeEmail}" onblur="checkEmail('examineeEmail')"
	 >
    </div>
  </div>
 
 <center>
  <div class="form-group">
      <button type="submit" class="btn btn-default">确认新增</button>
	  <a href="${pageContext.request.contextPath}/ExamineeManagementServlet?operate=findAll_examinee&pageNum=1" target="main" >
	  	
	</a>
  </div>
  </center>
</form>
</body>
</html>