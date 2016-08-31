function check(id){
	var element = $("#"+id+"").val();
	var size = element.length;
	if(size>16){
		alert("您输入的长度大于16");
	}
	if(size==0){
		alert("您输入的内容不能为空");
	}
}
function checkSex(id){
	var element = $("#"+id+"").val();
	var size = element.length;
	if(size==0){
		alert("请选择性别");
	}
}
function checkTestRule(id){
	var element = $("#"+id+"").val();
	var size = element.length;
	if(size==0){
		alert("请选择试题模板");
	}
}
function checkEmail(id){
	var expr = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
	var element = $("#"+id+"").val();
	var size = element.length;
	var flag = expr.test(element);
	if(!flag){
		alert("请输入正确格式的邮箱 ");
	}
}
function checkTel(id){
	var expr = /^1[3458]\d{9}$/;
	var element = $("#"+id+"").val();
	var flag = expr.test(element);
	if(!flag){
		alert("请输入正确格式的手机号");
	}
}

