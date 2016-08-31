function checkLength(id){
	var element = $("#" + id + "").val();
	var size = element.length;
		if(size > 16){
		alert("您输入的长度大于16");
	}
	if(size == 0){
		alert("您输入的内容不能为空");
	}
}
