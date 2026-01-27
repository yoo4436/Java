<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg = "";
	String err = request.getParameter("errType");
	if (err != null){
		switch(err){
			case "1": msg = "Email EXIST!"; break;
			case "2": msg = "Password Err!"; break;
			case "3": msg = "ERR!"; break;
		}
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script>
			function checkForm(){
				
				return true;
			}
		</script>
	</head>
	<body>
		<div><%= msg %></div>
		<form action="Register" method="post" onsubmit="return checkForm();">
			Email: <input name="email" /><br />
			Password: <input name="pwd" type="password" /><br />
			Name: <input name="name" /><br />
			<input type="submit" value="Register" /><br />
		</form>
	</body>
</html>