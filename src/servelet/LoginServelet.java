package servelet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCursoJsp;
import dao.DaoLogin;

@WebServlet("/LoginServelet")
public class LoginServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoLogin daoLogin = new DaoLogin();

	public LoginServelet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);// por padrão fazer o doGet chamar o doPost assim não precisa declar o metodo
									// doGet envia os dados por URL
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			BeanCursoJsp beanCursoJsp = new BeanCursoJsp();
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");

			
			if (daoLogin.ValidarLoginSenha(login, senha)) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("acessoliberado.jsp");//se a validação for verdadeira prepara o dispacher
				dispatcher.forward(request, response);

			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("acessonegado.jsp");
				dispatcher.forward(request, response);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
