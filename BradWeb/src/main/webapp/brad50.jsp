<%@page import="tw.brad.apis.Student"%>
<%@page import="tw.brad.apis.Bike"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String name = "Brad";
	pageContext.setAttribute("myName", name);
	
	
	Bike bike = new Bike();
	pageContext.setAttribute("bike", bike);

	Student s1 = new Student();
	s1.setName("Brad2");
	//s1.getBike().accelerate().accelerate();
	pageContext.setAttribute("student", s1);

%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		MyName: ${myName }<br />
		Bike: ${bike }<br /> 
		${student }<br />
		${student.name }<br /> 
		${student.bike }<br />
		${student.bike.speed }<br />
	</body>
</html>