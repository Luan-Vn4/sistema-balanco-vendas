package br.mendonca.testemaven.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import br.mendonca.testemaven.services.UserService;
import br.mendonca.testemaven.services.dto.UserDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/dashboard/users")
public class ListUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter page = response.getWriter();

		try {
			UserService service = new UserService();

			// Verifica se o usuário está autenticado
			if (request.getSession(false) == null) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				return;
			}

			// Parâmetros da requisição
			Map<String, String[]> params = request.getParameterMap();

			// Caso padrão: listar todos os usuários
			if (request.getServletPath().equals("/dashboard/users") && params.isEmpty()) {
				List<UserDTO> lista = service.listAllUsers();
				request.setAttribute("lista", lista);
				request.getRequestDispatcher("list-users.jsp").forward(request, response);
			} else if (request.getServletPath().equals("/dashboard/users") && params.containsKey("search")) {
				// Caso a busca seja realizada
				String search = request.getParameter("search");
				List<UserDTO> lista = service.searchUsersByName(search);  // Busca por nome
				request.setAttribute("lista", lista);
				request.setAttribute("search", search);  // Passa o termo de busca para a JSP
				request.getRequestDispatcher("list-users.jsp").forward(request, response);
			} else {
				// Caminho não reconhecido
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}

		} catch (Exception e) {
			// Tratamento de erro
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));

			page.println("<html lang='pt-br'><head><title>Error</title></head><body>");
			page.println("<h1>Error</h1>");
			page.println("<code>" + sw.toString() + "</code>");
			page.println("</body></html>");
			page.close();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter page = response.getWriter();

		try {
			// Recupera o usuário logado
			UserDTO loggedUser = (UserDTO) request.getSession().getAttribute("user");
			if (loggedUser == null) {
				throw new IllegalStateException("Usuário não está logado.");
			}

			// Obtém os parâmetros da requisição
			String followedUserEmail = request.getParameter("userEmail");
			String action = request.getParameter("action");

			if (followedUserEmail == null || action == null) {
				throw new IllegalArgumentException("Parâmetros ausentes na requisição.");
			}

			String followerEmail = loggedUser.getEmail();
			String followedEmail = followedUserEmail;

			// Instancia o serviço e realiza a ação apropriada
			UserService userService = new UserService();
			if ("follow".equalsIgnoreCase(action)) {
				userService.followUser(followerEmail, followedEmail);
			} else if ("unfollow".equalsIgnoreCase(action)) {
				userService.unfollowUser(followerEmail, followedEmail);
			}

			// Redireciona para a página de usuários
			response.sendRedirect(request.getContextPath() + "/dashboard/users");

		} catch (Exception e) {
			// Exibe erro na página caso ocorra uma falha
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));

			page.println("<html lang='pt-br'><head><title>Error</title></head><body>");
			page.println("<h1>Error</h1>");
			page.println("<code>" + sw.toString() + "</code>");
			page.println("</body></html>");
			page.close();
		}
	}
}
