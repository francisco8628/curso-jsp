package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanCursoJsp;
import connection.SingleConnection;

public class DaoUsuario {

	
	private Connection connection;
	
	public DaoUsuario() {
		
		connection =SingleConnection.getConnection();
	}
	
	public void Salvar(BeanCursoJsp usuario) {
		try {

			String sql = "insert into usuario(login,senha) values(?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, usuario.getLogin());
			insert.setString(2, usuario.getSenha());
			insert.execute();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}

		}
	}// fim do metodo Salvar
	
	public List<BeanCursoJsp> listar() throws Exception { // podemos lançar aqui por ser consulta

		List<BeanCursoJsp> listar = new ArrayList<BeanCursoJsp>();

		String sql = "select * from usuario";
		PreparedStatement todosUsuarios = connection.prepareStatement(sql);
		ResultSet resultado = todosUsuarios.executeQuery();

		while (resultado.next()) {
			BeanCursoJsp usuario = new BeanCursoJsp();
			usuario.setLogin(resultado.getString("login"));
			usuario.setSenha(resultado.getString("senha"));
			listar.add(usuario);
		}

		return listar;
	}// fim listar
	
	public void delete(String login) {
		try {
		String sql = "delete  from usuario where login = '"+login+"'";
		PreparedStatement deleteUser = connection.prepareStatement(sql);
		 deleteUser.execute();
		 
		 connection.commit();
		 
		}catch (Exception e) {
			e.printStackTrace();
			try {
				 connection.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}
}
