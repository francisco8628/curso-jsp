
<jsp:useBean id="calcula" class="beans.BeanCursoJsp"	type="beans.BeanCursoJsp" scope="page" />
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<jsp:setProperty property="*" name="calcula" />
	<h3>Seja bem vindo ao Sistema</h3>
	<a href ="salvarUsuario?acao=listartodos">Cadastro de usuarios</a>

</body>
</html>