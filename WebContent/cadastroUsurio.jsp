<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta  http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Usuario</title>
<link rel= "stylesheet" href = "resourses/css/cadastro.css">
</head>
<body>
   <center>
    <h1>Cadastro de Usuários</h1>
     <h3 style = "color: red;">${msg}</h3>
     </center>
    
	<form action="salvarUsuario" method="post" id= "formuser">
	<ul class="form-style-1">
		 <li>
			 <table>
					<tr>
						<td>Código:</td>
						<td><input type="text" id="id" readonly="readonly" name="id"
							value="${user.id }" class="field-long"></td>
					</tr>

					<tr>
						<td>Login:</td>
						<td><input type="text" id="login" name="login"
							value="${user.login }"></td>
					</tr>

					<tr>
						<td>Senha:</td>
						<td><input type="password" id="senha" name="senha"
							value="${user.senha}"></td>
					</tr>
					
					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome"
							value="${user.nome}"></td>
					</tr>
					


					<tr>
						<td></td>
						<td><input type="submit" value="Salvar ">
						<input type="submit" value="Cancelar" onclick="document.getElementById('formuser').action = 'salvarUsuario?acao=reset'"> </td><!-- pega o formulario pelo Id -->
					</tr>
					
				</table>
		
		    </li>
		</ul>
	</form>


	<div class="container">
		<table class="responsive-table">
			<tr>
				<%--  Adiciona linha --%>
				<th>Id</th>
				<%-- nome a  coluna 1 --%>
				<th>Login</th>
				<%-- nome a  coluna 2 --%>
				<th>Nome</th>
				<%-- nome a  coluna 3--%>
				<th>Delete</th>
				<%-- nome a  coluna 4 --%>
				<th>Editar</th>
				<%-- nome a  coluna 5 --%>

			</tr>

			<caption>Usuarios Cadastrados</caption>
			<c:forEach items="${usuarios}" var="user">
   		        <tr>
					<td Style="width: 150px"><c:out value="${user.id}">
							<%-- estilo da celula Id e o valor que vem do parametro da  ${} = expressão linguage JSP--%>
						</c:out></td>
					<td Style="width: 150px"><c:out value="${user.login}">
						</c:out></td>
					<td><c:out value="${user.nome}"></c:out></td>

					<td><a href="salvarUsuario?acao=delete&user=${user.id}"><img
							src="resourses/img/excluir.png" alt="Excluir" title="Excluir"
							width="20px" height="20px"></a></td>
					<%--  Adiciona icones Png --%>
					<td><a href="salvarUsuario?acao=editar&user=${user.id}"><img
							src="resourses/img/editar.png" alt="Editar" title="Editar"
							width="20px" height="20px"></a></td>
					<%--  Adiciona icones Png --%>
				</tr>
			</c:forEach>
		</table>

	</div>


</body>
</html>