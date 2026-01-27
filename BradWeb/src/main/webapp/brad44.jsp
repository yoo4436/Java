<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="member" class="tw.brad.apis.Member" scope="request"></jsp:useBean>
<jsp:setProperty property="email" value="${param.email }" name="member"/>
<jsp:setProperty property="pwd" value="${param.pwd }" name="member"/>
<jsp:setProperty property="name" value="${param.name }" name="member"/>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		Member: <%= member.getPwd() %> : <%= member.getEmail() %> : <%= member.getName() %><br />
		Member: <jsp:getProperty property="pwd" name="member"/> :
		<jsp:getProperty property="email" name="member"/> :
		<jsp:getProperty property="name" name="member"/><br />
	
		${Math.random() } 
		
	</body>
</html>