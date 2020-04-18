package connection;              

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {         //a conexão chama essa classe uma unica vez
	private static String url = "jdbc:postgresql://localhost:5432/curso-jsp1?autorReconnect=true";// url para conexao padrao do POSTGRES
	                                                                                              //colocar autorReconnect = true para caso a conexão com o banco caia
	private static String password = "admin";     //senha do banco
	private static String user = "postgres";      //usuario do banco
	private static Connection conection = null; // tem que ser do pacote SQL java

	static {

		conectar();
	}

	public  SingleConnection() {
 	
 	conectar();
 }

	private static void conectar() {
		try {
			if (conection == null) {
				Class.forName("org.postgresql.Driver");
				conection = DriverManager.getConnection(url, user, password);
				conection.setAutoCommit(false);// setar falso para nao salvar automatico
				System.out.println("Conexão OK");// para teste da conexão
			} else {

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static Connection getConnection() { // metodo que retorna a conexão

		return conection;
	}

}