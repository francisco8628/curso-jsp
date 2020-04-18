package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanCursoJsp;
import connection.SingleConnection;
import servelet.Usuario;

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
	
	public List<BeanCursoJsp> listar() throws Exception { // podemos lan�ar aqui por ser consulta

		List<BeanCursoJsp> listar = new ArrayList<BeanCursoJsp>();

		String sql = "select * from usuario";
		PreparedStatement todosUsuarios = connection.prepareStatement(sql);
		ResultSet resultado = todosUsuarios.executeQuery();

		while (resultado.next()) {
			BeanCursoJsp usuario = new BeanCursoJsp();
			usuario.setId(resultado.getLong("id"));
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

	public BeanCursoJsp consultar(String login)throws Exception {  //consulta usuarios para atualizar
		String sql = "select *  from usuario where login = '"+login+"'";
		PreparedStatement consulteUser = connection.prepareStatement(sql);
		ResultSet resultado = consulteUser.executeQuery();
		
		if (resultado.next()) {
			
			BeanCursoJsp user = new BeanCursoJsp();
			user.setId(resultado.getLong("id"));
			user.setLogin(resultado.getString("login"));
			user.setSenha(resultado.getString("senha"));
			
			return user;
		}
		
		return null;
	}

	public void atulizar(BeanCursoJsp usuario) {
		try {
		String sql = "update  usuario set login = ?, senha = ? where id = "+usuario.getId();
		PreparedStatement Usuarioupdate = connection.prepareStatement(sql);
		Usuarioupdate.setString(1, usuario.getLogin());
		Usuarioupdate.setString(2, usuario.getSenha());
		Usuarioupdate.executeUpdate();
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
