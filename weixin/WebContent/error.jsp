<html>
<head>
<title>
ERROR
</title>
</head>
<body>

<%
String msg = "";

Exception e = (Exception)request.getAttribute("ex");
if(e != null){
	java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	e.printStackTrace(new java.io.PrintStream(baos));
	msg = new String(baos.toByteArray());
}else{
	msg = "...";
}

%>

<%=msg%>

</body>

</html>