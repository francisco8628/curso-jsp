package servelet; //servelet 

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
@WebServlet("/salvarUsuario") // indica quem esta apontando para a servelet
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();

	public Usuario() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { // se o parametro vem por link sempre cai no get
		try {
			String acao = request.getParameter("acao");
			String user = request.getParameter("user");

			// System.out.println(acao);

			if (acao.equalsIgnoreCase("delete")) {
				daoUsuario.delete(user);
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsurio.jsp"); // prepara a pagina para
																								// redirecionar
				request.setAttribute("usuarios", daoUsuario.listar()); // pega a lista de usuarios para exibir na tela

				view.forward(request, response); // redireciona para uma paf=gina
			} else if (acao.equalsIgnoreCase("editar")) {
				// caso seja editar

				BeanCursoJsp beanCursoJsp = daoUsuario.consultar(user);

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsurio.jsp"); // prepara a pagina para
				request.setAttribute("user", beanCursoJsp); // pega o usuario consultado
				view.forward(request, response); // redireciona para uma paf=gina

			} else if (acao.equalsIgnoreCase("listartodos")) {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsurio.jsp"); // prepara a pagina para
																								// redirecionar
				request.setAttribute("usuarios", daoUsuario.listar()); // pega a lista de usuarios para exibir na tela

				view.forward(request, response); // redireciona para uma pagina
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// fim doget

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		System.out.println(acao);

		if (acao != null && acao.equalsIgnoreCase("reset")) { // se for diferente de null e igual a"reset"
			try {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsurio.jsp"); // prepara a pagina para
																								// redirecionar
				request.setAttribute("usuarios", daoUsuario.listar()); // pega a lista de usuarios para exibir na tela
				view.forward(request, response); // redireciona para uma paf=gina

			} catch (Exception e) {
				e.printStackTrace();

			}
		} else {
			String id = request.getParameter("id"); //// se vier por formulario cai no post
			String login = request.getParameter("login");// o nome tem que estar igual ao escrito na jsp ou tela
			String senha = request.getParameter("senha");
			String nome = request.getParameter("nome");
			String telefone = request.getParameter("telefone");

			BeanCursoJsp usuario = new BeanCursoJsp(); // cria o usuario para setar parametros para salvar ou atualizar
														// no banco

			usuario.setId(!id.isEmpty() ? Long.parseLong(id) : 0);
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setNome(nome);
			usuario.setTelefone(telefone);

			try {
				if (id == null || id.isEmpty() && !daoUsuario.ValidarLogin(login)||!daoUsuario.ValidarSenha(senha)) {
					 String msg = "";
					if(id == null || id.isEmpty() && !daoUsuario.ValidarLogin(login)) {
					 msg = "Este login n�o � valido, ja existe";
					}
					if(id == null || id.isEmpty() && !daoUsuario.ValidarSenha(senha)) {
						
						msg = "Esta senha n�o � valida, ja existe";
					}
					
                   
					request.setAttribute("msg", msg);
				}

			    if (id == null || id.isEmpty() && daoUsuario.ValidarLogin(login)&&daoUsuario.ValidarSenha(senha)) { //salva usuario
					daoUsuario.Salvar(usuario); // se n�o existir o id salva

				} else if (id != null && !id.isEmpty()) {   //atualiza usuario
					 String msg = "";
					if(!daoUsuario.ValidarLoginUpdate(login, id)||!daoUsuario.ValidarSenhaUpdate(senha, id)) {//express�o negada
						if(!daoUsuario.ValidarLoginUpdate(login, id)) {
						
					     msg = "Nome de Usuario invalido";
						}else if(!daoUsuario.ValidarSenhaUpdate(senha, id)) {
							
							  msg = "senha invalida";	
						}
					}else {
					daoUsuario.atulizar(usuario); // se existir o id atualiza
					}//fim else atulizar
					request.setAttribute("msg",msg);
				} // fim else

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsurio.jsp"); // prepara a pagina para
																								// redirecionar
				request.setAttribute("usuarios", daoUsuario.listar()); // pega a lista de usuarios para exibir na tela

				view.forward(request, response); // redireciona para uma pagina

			} catch (Exception e) {
				e.printStackTrace();
			} // fim catch

		} // fim else

	}// fim doPost
}// fim da servelet
