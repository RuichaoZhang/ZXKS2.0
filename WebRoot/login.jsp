<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<head>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
		<title>用户登录</title>
		<style type="text/css">
			*{margin:0px;padding:0px;}
			.top{width:1255px;
			height:60px;margin:0px auto;padding: 10px 0}
		    .login{width:1190px;height:219px;margin:100px auto;position:relative;}
		    .login_left{width:324px;height:219px;float:left;}
		    .login_right{width:400px;height:219px;float:right; }
		    .login_right_center{width:320px;height:200px;float:right;top:40px;right:30px;position: absolute;color:#888888;font-size:12px;
			font-family:"微软雅黑";}
		    .user{width:320px;height:64px;background:white;}
		    .user_text{width:320px;}
			.user_in{width:282px;height:28px;line-height:28px;font-size:14px;bordor:1px soild #cccccc}
			.userauto{width:320px;height:30px;color:#000000;}
			.button{width:320px;height:52px;}
			.user_login{width:307px;height:34px;background:url('images/login-img2015.png') no-repeat  0 0; border:none;}
			.user_login:hover{background:url('images/login-img2015.png') no-repeat 0  -34px;}
		</style>
		<script type="text/javascript">
			function check(id){
				var input = document.getElementById(id);
				if(id == "userName"){
					if(input.value == ""){
						alert("用户名不能为空");
					}
					if(input.value.length > 16){
						alert("用户名长度超过限制,请输入16字符以内");
					}
					
				}
				if(id == "password"){
					if(input.value == ""){
						alert("密码不能为空");
					}
					if(input.value.length > 16){
						alert("密码长度超过限制,请输入16字符以内");
					}
				}
			};
			var radioObjs = document.getElementsByName("type");
			for(var i = 0; i < radioObjs.length; i++){
				if(radioObjs[i].value == "hr"){
					if(userName.value == null){
						alert("Hr用户名不能为空");
					}		
				}
				if(radioObjs[i].value == "examinee"){
					if(userName.value == null){
						alert("Hr用户名不能为空");
					}					
				}
			}
		</script>
	</head>
	<body>
		<c:if test="${'login' == (message.state)}">
			<script>
			alert("${message.content}");		
			</script>
		</c:if>
		<div class="top">
    		<img src="bootstrap-3.3.5-dist/images/login.png" alt="凯捷中国" width="214" height="50">
		</div> 
	
		<div class="login">
    		<div class="login_left">
          		<img src="./images/1.jpg" alt="登录图片" width="700" height="300">
    		</div>
   			<div class="login_right">
        		<div class="login_right_center">
        			<form action="${pageContext.request.contextPath}/LoginServlet" method="post">
	            		<div class="user">
	                		<span>姓名/已验证手机号</span>
	                		<div class="user_text">
					    		<input id="userName" name="userName" type="text" class="user_in" onblur="check('userName')"><div id = '1'></div>
				    		</div>
	            		</div>
	            		<div class="user">
							<span>密码</span>
	                		<div class="user_text">
					    		<input id="password" name="password" type="password" class="user_in" onblur="check('password')"><div id = '2' ></div>
							</div>
	            		</div> 
	            		<div class="user">
	                		<div class="user_text">
						  		<center>
						  			Hr登录<input name="type" type="radio" value="hr" checked="checked">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						    		考生登录<input name="type" type="radio" value="examinee" >
						    	</center>
							</div>
	            		</div> 
						<div class="userauto">
							
			    		</div>
						<div class="button">
						<input type="submit" class="user_login" value="" border="0">
						</div>
					</form>
        		</div>
    		</div>
		</div>
	</body>
</html>