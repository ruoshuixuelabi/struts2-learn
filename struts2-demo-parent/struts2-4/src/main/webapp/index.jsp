<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="emp-input.action">Emp Input Page</a>
	<br>
	<br>
	<a href="testTag.action?name=fuxi">testTag</a>
	<%
		session.setAttribute("date",new Date());
	%>
</body>
</html>