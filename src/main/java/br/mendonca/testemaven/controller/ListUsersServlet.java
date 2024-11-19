package br.mendonca.testemaven.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.UUID;

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
			List<UserDTO> lista = service.listAllUsers();

			// Anexa à requisição um objeto ArrayList e despacha a requisição para uma JSP.
			request.setAttribute("lista", lista);

			UserDTO currentUser = (UserDTO) request.getSession().getAttribute("user");
			System.out.println(currentUser.getEmail());
//			List<UUID> seguidores = service.listFollowers();
//			request.setAttribute("seguidores", seguidores);

			request.getRequestDispatcher("list-users.jsp").forward(request, response);
		} catch (Exception e) {
			// Escreve as mensagens de Exception em uma página de resposta.
			// Não apagar este bloco.
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);

			page.println("<html lang='pt-br'><head><title>Error</title></head><body>");
			page.println("<h1>Error</h1>");
			page.println("<code>" + sw.toString() + "</code>");
			page.println("</body></html>");
			page.close();
		} finally {

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