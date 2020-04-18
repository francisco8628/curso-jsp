<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Usuario</title>
</head>
<body>

<h1>Cadastro de usuario</h1>

<form action="salvarUsuario" method="post">

	<table>
		<tr>
			<td>Login:</td>
			<td><input type="text" id="login" name="login"></td>
		</tr>

		<td>Senha:</td>
		<td><input type="password" id="senha" name="senha"></td>
		</tr>
	</table>
	<input type="submit" value="Salvar Usuario">

   </form>
</body>
</html>