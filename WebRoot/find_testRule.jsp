<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/font-awesome.min.css" />
<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/chen.css">
<script type="text/javascript">
function show_confirm(url,a)
{
var r=confirm("确认删除试卷吗？");
if (r==false)
{
}else
{
	a.setAttribute("href", url);
}
}

function init()
{
}
</script>
<style type="text/css">
        
        /*CSS Code for Menu Begin:*/
        /* Root = Horizontal, Secondary = Vertical */
        ul#navmenu
        {
            margin: 0;
            border: 0 none;
            padding: 0;
            width: 100px; /*For KHTML*/
            list-style: none;
            height: 24px;
        }
        ul#navmenu li
        {
            margin: 0;
            border: 0 none;
            padding: 0;
            float: left; /*For Gecko*/
            display: inline;
            list-style: none;
            position: relative;
            height: 24px;
        }
        ul#navmenu ul
        {
            margin: 0;
            border: 0 none;
            padding: 0;
            width: 160px;
            list-style: none;
            display: none;
            position: absolute;
            top: 0px;
            left: 50px;
        }
        ul#navmenu ul li
        {
            float: none; /*For Gecko*/
            display: block !important;
            display: inline; /*For IE*/
        }
        /* Root Menu */
        ul#navmenu a
        {
            border: 1px solid #FFF;
            border-right-color: #CCC;
            border-bottom-color: #CCC;
            padding: 0 6px;
            float: none !important; /*For Opera*/
            float: left; /*For IE*/
            display: block;
            background: #EEE;
            color: #666;
            font: bold 10px/22px Verdana, Arial, Helvetica, sans-serif;
            text-decoration: none;
            height: auto !important;
            height: 1%; /*For IE*/
        }
        /* Root Menu Hover Persistence */
        ul#navmenu a:hover, ul#navmenu li:hover a, ul#navmenu li.iehover a
        {
            background: #CCC;
            color: #FFF;
        }
        /* 2nd Menu */
        ul#navmenu li:hover li a, ul#navmenu li.iehover li a
        {
            float: none;
            background: #EEE;
            color: #666;
        }
        /* 2nd Menu Hover Persistence */
        ul#navmenu li:hover li a:hover, ul#navmenu li:hover li:hover a, ul#navmenu li.iehover li a:hover, ul#navmenu li.iehover li.iehover a
        {
            background: #CCC;
            color: #FFF;
        }
        /* 3rd Menu */
        ul#navmenu li:hover li:hover li a, ul#navmenu li.iehover li.iehover li a
        {
            background: #EEE;
            color: #666;
        }
        /* 3rd Menu Hover Persistence */
        ul#navmenu li:hover li:hover li a:hover, ul#navmenu li:hover li:hover li:hover a, ul#navmenu li.iehover li.iehover li a:hover, ul#navmenu li.iehover li.iehover li.iehover a
        {
            background: #CCC;
            color: #FFF;
        }
        /* 4th Menu */
        ul#navmenu li:hover li:hover li:hover li a, ul#navmenu li.iehover li.iehover li.iehover li a
        {
            background: #EEE;
            color: #666;
        }
        /* 4th Menu Hover */
        ul#navmenu li:hover li:hover li:hover li a:hover, ul#navmenu li.iehover li.iehover li.iehover li a:hover
        {
            background: #CCC;
            color: #FFF;
        }
        ul#navmenu ul ul, ul#navmenu ul ul ul
        {
            display: none;
            position: absolute;
            top: 0;
            left: 160px;
        }
        /* Do Not Move - Must Come Before display:block for Gecko */
        ul#navmenu li:hover ul ul, ul#navmenu li:hover ul ul ul, ul#navmenu li.iehover ul ul, ul#navmenu li.iehover ul ul ul
        {
            display: none;
        }
        ul#navmenu li:hover ul, ul#navmenu ul li:hover ul, ul#navmenu ul ul li:hover ul, ul#navmenu li.iehover ul, ul#navmenu ul li.iehover ul, ul#navmenu ul ul li.iehover ul
        {
            display: block;
        }
   
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
<ol class="breadcrumb">
  <li><i class="icon-home home-icon"></i><a href="${pageContext.request.contextPath}/GradeManagementServlet?operate=findAll_grade&pageNum=1">首页</a></li>
  <li class="active">试卷管理</li>
</ol>
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
	<br>
	<table>
		<tr>
			<td>
			<div  class="font">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				新增组卷方案
				<a href="${pageContext.request.contextPath}/TestRuleManagementServlet?operate=add_testRule" target="main" class="glyphicon glyphicon-plus">
					
				</a>
			</div>
			</td>
		</tr>	
	</table>
	<div class="table-responsive">
		<table class="table table-bordered table-striped table-hover">
			<thead>
				<tr>
					<th>试卷名称</th>
					<th>试卷时长</th>
					<th>试题类型&nbsp;|&nbsp;题目个数</th>
					<th>操作</th>
				</tr>
			</thead>
		<tbody>
		<c:forEach items="${page.records}" var="testRule"
			varStatus="vs">
			<tr>
				<td style="algin : center">${testRule.testRuleName }</td>
				<td>${testRule.testRuleTime }</td>
				<td>
					<ul id="navmenu" style="align:center">
						<li><a href="#" >总&nbsp;览 +</a>
							<ul>
								<c:forEach items="${testRule.testRuleItemList}"
									var="testRuleItem">
									<li><a href="#">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${testRuleItem.testType.testTypeName}
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											${testRuleItem.testRuleItemNum }</a></li>
								</c:forEach>
							</ul></li>
					</ul>
				</td>
				<td><a
					href="${pageContext.request.contextPath}/TestRuleManagementServlet?operate=jump_updateTestRule&testRuleId=${testRule.testRuleId}" class="glyphicon glyphicon-pencil"></a>&nbsp;
					<a
					 href="javascript:void(0);" onclick="show_confirm('TestRuleManagementServlet?operate=delete_testRule&testRuleId=${testRule.testRuleId}',this);" class="glyphicon glyphicon-trash"></a>
				</td>
			</tr>
		</c:forEach>
	   </tbody>
	 </table>
	</div><center>
	第${page.pageNum
				}页/共${page.totalPage}页&nbsp;&nbsp; <a
				href="${pageContext.request.contextPath}/TestRuleManagementServlet?operate=findAll_testRule&pageNum=1">首页</a>&nbsp;
				<a
				href="${pageContext.request.contextPath}/TestRuleManagementServlet?operate=findAll_testRule&pageNum=${page.pageNum - 1 == 0 ? 1 : page.pageNum - 1}">上一页</a>&nbsp;
				<a
				href="${pageContext.request.contextPath}/TestRuleManagementServlet?operate=findAll_testRule&pageNum=${page.pageNum + 1 >= page.totalPage ? page.totalPage : page.pageNum + 1}">下一页</a>&nbsp;
				<a
				href="${pageContext.request.contextPath}/TestRuleManagementServlet?operate=findAll_testRule&pageNum=${page.totalPage}">尾页</a>&nbsp;
				<select id="s1">
					<c:forEach begin="1" end="${page.totalPage}" var="num">
						<option value="${num}" ${page.pageNum==num} ? 'selected="selected"' : ''>
							${num}</option>
					</c:forEach>
			</select> <a href="javascript:jump()">跳转 </a> <script type="text/javascript">
				function jump() {
					var num = document.getElementById("s1").value;
					window.location.href = "${pageContext.request.contextPath}/TestRuleManagementServlet?operate=findAll_testRule&pageNum="
							+ num;
				}
			</script>
</center>

 <div class="layout_footer">
        <p>Copyright © 2015 - Jar组作品</p>
    </div>

</body>

</html>