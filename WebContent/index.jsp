
<%@page import="beans.BeanCursoJsp"%>
<jsp:useBean id="calcula" class="beans.BeanCursoJsp"	type="beans.BeanCursoJsp" scope="page" />
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
	

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
       <c:set var = "data"  scope="page" value = "${500*20}"/>
       
       <c:out value="${data}"></c:out>

<p/>
<p/>
<p/>
<p/>
	<form action="LoginServelet" method="post">

		Login:<%-- é o label da pagina --%>
		<input type="text" id="login" name="login">
		<br /> 
		Senha: 
		<input	type="text" id="senha" name="senha">
		<br /> 
		<input type="submit" value = "salvar"> <input />

	</form>



</body>
</html>