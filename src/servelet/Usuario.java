package servelet;   //se

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCursoJsp;
import dao.DaoUsuario;

/**
 * Servlet para cadastro de usuarios
 */
@WebServlet("/salvarUsuario")  //indica quem esta apontando para a servelet
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DaoUsuario daoUsuario = new DaoUsuario();
    
    public Usuario() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login =  request.getParameter("login");
		String senha=  request.getParameter("senha");
		
		BeanCursoJsp usuario = new BeanCursoJsp();
		
		usuario.setLogin(login);
		usuario.setSenha(senha);
		daoUsuario.Salvar(usuario);
		
		
	}

}
