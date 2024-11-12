package br.mendonca.testemaven.controller.aluno;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import br.mendonca.testemaven.model.entities.Aluno;
import br.mendonca.testemaven.services.AlunoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/dashboard/alunos")
public class AlunoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final AlunoService service = new AlunoService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNumber = 1;
        String pageParam = request.getParameter("page");
        if (pageParam != null) {
            pageNumber = Integer.parseInt(pageParam);
        }
        Map<String, String[]> params = request.getParameterMap();

        if (params.containsKey("deletar") && params.containsKey("id")) {
            UUID uuid = UUID.fromString(request.getParameter("id"));
            try {
                service.deletar(uuid);
                response.sendRedirect("/dashboard/alunos");
                return;
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Aluno não encontrado");
                return;
            }
        }

        try {
            List<Aluno> lista = service.listAlunosPaginated(pageNumber, false);
            int totalPages = service.getTotalPages(false);
            request.setAttribute("lista", lista);
            request.setAttribute("currentPage", pageNumber);
            request.setAttribute("totalPages", totalPages);
            request.getRequestDispatcher("list-alunos.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao buscar alunos");
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter page = response.getWriter();

        try {
            String parametro = request.getParameter("nomeparametro");
            String nome = request.getParameter("nome");
            Double media = Double.parseDouble(request.getParameter("media"));
            Boolean isAtivo = true;
            Boolean deletado = false;
            service.register(nome, media, deletado, isAtivo);
            String referer = request.getHeader("Referer");

            if (referer != null && referer.contains("/dashboard/alunos")) {
                // Redireciona de volta para a p�gina anterior (ou algum outro caminho)
                response.sendRedirect(referer);
            } else {
                // Caso o referer n�o esteja dispon�vel ou a URL n�o seja a esperada
                response.sendRedirect(request.getContextPath() + "/dashboard/alunos");
            }


        } catch (Exception e) {
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
            page.close();
        }
    }
}
