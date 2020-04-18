package servelet;   //servelet 

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao");
			String user = request.getParameter("user");
			
			System.out.println(acao);

			if (acao.equalsIgnoreCase("delete")) {
				daoUsuario.delete(user);
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsurio.jsp"); // prepara a pagina para
																								// redirecionar
				request.setAttribute("usuarios", daoUsuario.listar()); // pega a lista de usuarios para exibir na tela

				view.forward(request, response); // redireciona para uma paf=gina
			} else if (acao.equalsIgnoreCase("editar")) {  //caso seja editar
				
				BeanCursoJsp beanCursoJsp = daoUsuario.consultar(user);
				
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsurio.jsp"); // prepara a pagina para
				request.setAttribute("user",beanCursoJsp); // pega o usuario consultado
				view.forward(request, response); // redireciona para uma paf=gina

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//fim doPost
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id =  request.getParameter("id");
		String login =  request.getParameter("login");
		String senha=  request.getParameter("senha");
		
		System.out.println(login);
		System.out.println(senha);
		
		BeanCursoJsp usuario = new BeanCursoJsp();
		
		usuario.setId(!id.isEmpty()?Long.parseLong(id):0);
		usuario.setLogin(login);
		usuario.setSenha(senha);
		
		
		if(id==null||id.isEmpty()) {
			daoUsuario.Salvar(usuario);	//se não existir o id salva
			
		}else {
			daoUsuario.atulizar(usuario); //se  existir o id atualiza
		}

		try {
			RequestDispatcher view  = request.getRequestDispatcher("/cadastroUsurio.jsp"); //prepara a pagina para redirecionar
			request.setAttribute("usuarios", daoUsuario.listar());  //pega a lista de usuarios para exibir na tela
			
			view.forward(request, response); //redireciona para uma paf=gina
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
