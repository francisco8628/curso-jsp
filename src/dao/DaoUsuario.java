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

			String sql = "insert into usuario(login,senha,nome,telefone) values(?,?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, usuario.getLogin());
			insert.setString(2, usuario.getSenha());
			insert.setString(3, usuario.getNome());
			insert.setString(4, usuario.getTelefone());
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
			usuario.setId(resultado.getLong("id"));
			usuario.setLogin(resultado.getString("login"));
			usuario.setSenha(resultado.getString("senha"));
			usuario.setNome(resultado.getString("nome"));
			usuario.setTelefone(resultado.getString("telefone"));
			listar.add(usuario);
		}

		return listar;
	}// fim listar
	
	public void delete(String id) {
		try {
		String sql = "delete  from usuario where id= '"+id+"'";
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

	public BeanCursoJsp consultar(String id)throws Exception {  //consulta usuarios para atualizar
		String sql = "select *  from usuario where id = '"+id+"'";
		PreparedStatement consulteUser = connection.prepareStatement(sql);
		ResultSet resultado = consulteUser.executeQuery();
		
		if (resultado.next()) {
			
			BeanCursoJsp user = new BeanCursoJsp();
			user.setId(resultado.getLong("id"));
			user.setLogin(resultado.getString("login"));
			user.setSenha(resultado.getString("senha"));
			user.setNome(resultado.getString("nome"));
			user.setTelefone(resultado.getString("telefone"));
			
			return user;
		}
		
		return null;
	}

	public void atulizar(BeanCursoJsp usuario) {
		try {
		String sql = "update  usuario set login = ?, senha = ?, nome=?, telefone=? where id = "+usuario.getId();
		PreparedStatement Usuarioupdate = connection.prepareStatement(sql);
		Usuarioupdate.setString(1, usuario.getLogin());
		Usuarioupdate.setString(2, usuario.getSenha());
		Usuarioupdate.setString(3,usuario.getNome());
		Usuarioupdate.setString(4,usuario.getTelefone());
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
	}//fim atualizar
	
	public boolean ValidarLogin(String login)throws Exception {
		
		String sql = "select count(1) as qtd from usuario where login = '"+login+"'";
		
		PreparedStatement UserValido = connection.prepareStatement(sql);
		ResultSet resultado =  UserValido.executeQuery();
		
		if (resultado.next()) {
			
			return resultado.getInt("qtd")<=0;/* se a quantidade for igual a 0 retorna verdadeiro*/
		}
	        return false;                       /* se a quantidade maior que usuario ja existe 0 retorna verdadeiro*/                 
		
	}//fim validar login
	
public boolean ValidarLoginUpdate(String login, String id)throws Exception {
		
		String sql = "select count(1) as qtd from usuario where login = '"+login+"'and id<> "+id;
		//selecionar uma contagem da tabela usuario onde o login não seja igual ao login passado
		// e o id seja diferente do id passado
		PreparedStatement UserValido = connection.prepareStatement(sql);
		ResultSet resultado =  UserValido.executeQuery();
		
		if (resultado.next()) {
			
			return resultado.getInt("qtd")<=0;/* se a quantidade for igual a 0 retorna verdadeiro*/
		}
	        return false;                       /* se a quantidade maior que usuario ja existe 0 retorna verdadeiro*/                 
		
	}//fim atualizarUpdate

public boolean ValidarSenha(String senha)throws Exception {
	
	String sql = "select count(1) as qtd from usuario where senha = '"+senha+"'";
	
	PreparedStatement UserValido = connection.prepareStatement(sql);
	ResultSet resultado =  UserValido.executeQuery();
	
	if (resultado.next()) {
		
		return resultado.getInt("qtd")<=0;/* se a quantidade for igual a 0 retorna verdadeiro*/
	}
        return false;                       /* se a quantidade maior que usuario ja existe 0 retorna verdadeiro*/                 
	
}//fim validar senha

public boolean ValidarSenhaUpdate(String senha, String id)throws Exception {
	
	String sql = "select count(1) as qtd from usuario where senha= '"+senha+"'and id<> "+id;
	//selecionar uma contagem da tabela usuario onde o login não seja igual ao login passado
	// e o id seja diferente do id passado
	PreparedStatement UserValido = connection.prepareStatement(sql);
	ResultSet resultado =  UserValido.executeQuery();
	
	if (resultado.next()) {
		
		return resultado.getInt("qtd")<=0;/* se a quantidade for igual a 0 retorna verdadeiro*/
	}
        return false;                       /* se a quantidade maior que usuario ja existe 0 retorna verdadeiro*/                 
	
}//fim validar senhaupdate
}
