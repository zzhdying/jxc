<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
</head>
<body>
	<img  src="${pageContext.request.contextPath}/weixin/authorize/qrImage?uuid=<%=request.getAttribute("uuid") %>">
	<input type="hidden" value="<%=request.getAttribute("uuid") %>" id="uuid">
</body>
<script type="text/javascript">
function check(){
	var uuid=$('#uuid').val();
	$.post('${pageContext.request.contextPath}/weixin/authorize/qrlogin',{'uuid':uuid},function(data){
   		if(data.status=='success'){
   			alert('登录成功');
   			location.href='index.jsp';
   		}
   	});
}
$(document).ready(function(){
	setTimeout(check,3000);
	setInterval(check,30000);
});
</script>
</html>