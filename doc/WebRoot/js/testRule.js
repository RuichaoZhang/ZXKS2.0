function change(select){
	var testTypeName = $(select).val();
	$.get("TestRuleManagementServlet?operate=getTestNum&testTypeName="+testTypeName, null, function(data, textStatus){
		$(select).parent().parent().find("td:last").remove();
		$(select).parent().parent().append("<td>该类型题目的最大个数为"+data+"</td>");
	});	
}
