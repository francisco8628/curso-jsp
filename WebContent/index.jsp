
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

<link rel= "stylesheet" href = "resourses/css/stilo.css">

</head>
<body>
	<div class="login-page">
		<div class="form">

			<form action="LoginServelet" method="post" class="login-form">

				Login:
				<%-- é o label da pagina --%>
				<input type="text" id="login" name="login"> <br /> Senha: <input
					type="text" id="senha" name="senha"> <br /> 
					<button type="submit" value="logar">Logar </button>

			</form>
		</div>
	</div>




</body>
</html>