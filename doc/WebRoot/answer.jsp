<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>这是试卷的测试</title>
	</head>
	<body>
		考生姓名:${sessionScope.examinee.examineeName},时间:${examinationPaper.time}
		总题数:${examinationPaper.totalTest}<br>
		<form action="${pageContext.request.contextPath}/OnlineTestManagementServlet" method="post">
			<%int j=0;%>
			<c:forEach items="${examinationPaper.testList}" var="test">
	
			<input type="hidden" id="testId<%=j%>" name="testId<%=j%>" value="${test.testId}">
			${test.testSubject}<br>
				<% int i=0; %>
				<c:forEach items="${test.testItemList}" var="testItem">
					<input type="radio" id="testItemId<%=j%><%=i%>" name="testItemId<%=j%><%=i%>" value="${testItem.testItemId}"/>${testItem.testItemContent}<br>
				</c:forEach>
				<%j++; %>
			</c:forEach>
			<input type="hidden" name="totalTest" value="${examinationPaper.totalTest}">
			<input type="submit" value="提交答案">
		</form>
	</body>
</html>