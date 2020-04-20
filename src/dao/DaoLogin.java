package dao;    //

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnection;

public class DaoLogin {

	
	private Connection connection;
	
	public DaoLogin() {
		
		connection = SingleConnection.getConnection();
	}
	
	public boolean ValidarLoginSenha(String login, String senha) throws Exception{
		
		
	String sql = "SELECT *  FROM public.usuario where login = '"+login+"' and senha = '"+senha+"'";
    PreparedStatement statement = connection.prepareStatement(sql);
    ResultSet resultado = statement.executeQuery();
    
    if (resultado.next()) {
		return true;    //possue usuario
	} else {
        return false;  //não possue
	}
    		
	}
}
