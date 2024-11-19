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

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession(false) == null) {
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		Map<String, String[]> params = req.getParameterMap();
		resp.setContentType("text/html");

		try {
			UserService service = new UserService();

			// Verifica os parâmetros e decide a ação
			if (req.getServletPath().equals("/dashboard/users") && params.isEmpty()) {
				// Caso padrão: listar todos os usuários
				List<UserDTO> lista = service.listAllUsers();
				req.setAttribute("lista", lista);
				req.getRequestDispatcher("list-users.jsp").forward(req, resp);
			} else if (req.getServletPath().equals("/dashboard/users") && params.containsKey("search")) {
				// Caso a busca seja realizada
				String search = req.getParameter("search");
				List<UserDTO> lista = service.searchUsersByName(search);  // Busca por nome
				req.setAttribute("lista", lista);
				req.setAttribute("search", search);  // Passa o termo de busca para a JSP
				req.getRequestDispatcher("list-users.jsp").forward(req, resp);
			}else {
				// Caminho não reconhecido
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
		} catch (Exception e) {
			// Tratamento de erro
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);

			PrintWriter page = resp.getWriter();
			page.println("<html lang='pt-br'><head><title>Error</title></head><body>");
			page.println("<h1>Error</h1>");
			page.println("<code>" + sw.toString() + "</code>");
			page.println("</body></html>");
			page.close();
		}
	}
	

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter page = resp.getWriter();
		
		try {
			// A programação do servlet deve ser colocada neste bloco try.
			// Apague o conteúdo deste bloco try e escreva seu código.
			String parametro = req.getParameter("nomeparametro");
			
			page.println("Parametro: " + parametro);
			page.close();
			
			
		} catch (Exception e) {
			// Escreve as mensagens de Exception em uma página de resposta.
			// Não apagar este bloco.
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			
			page.println("<html lang='pt-br'><head><title>Error</title></head><body>");
			page.println("<h1>Error</h1>");
			page.println("<code>");
			page.println(sw.toString());
			page.println("</code>");
			page.println("</body></html>");
			page.close();
		} finally {
			
		}
	}
}
