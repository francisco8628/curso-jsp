
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

<link rel= "stylesheet" href = "resourses/css/stilo.css"> <%-- devemos indicar o tipo css ="stylesheet" e apontar para a pagina  "resourses/css/stilo.css"  --%>	

</head>
<body>
	<div class="login-page">   <%-- apontar o classe da pagina --%>	
		<div class="form">      <%-- apontar o clase do form css --%>	

			<form action="LoginServelet" method="post" class="login-form">  <%--  apontar o clase login-form --%>	

				Login:<%-- � o label Login  da pagina --%>				
				 <input type="text" id="login" name="login">
				  <br /> 
				   Senha: <%-- � o label Senha  da pagina --%>	
				    <input type="text" id="senha" name="senha">
				     <br /> 
					   <button type="submit" value="logar">
					     Logar </button> <%-- � o label Logar do Bot�o--%>	

			</form>
		</div>
	</div>
</body>
</html>