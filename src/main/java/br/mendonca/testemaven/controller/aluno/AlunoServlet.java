package br.mendonca.testemaven.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import br.mendonca.testemaven.model.entities.Aluno;
import br.mendonca.testemaven.services.AlunoService;
import br.mendonca.testemaven.services.UserService;
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
        response.setContentType("text/html");
        PrintWriter page = response.getWriter();
        String path = request.getServletPath();
        try {
            List<Aluno> lista = service.listAllAlunos();
            request.setAttribute("lista", lista);
            request.getRequestDispatcher("list-alunos.jsp").forward(request, response);

        } catch (Exception e) {
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


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter page = response.getWriter();

        try {
            String parametro = request.getParameter("nomeparametro");
            String nome = request.getParameter("nome");
            Double media = Double.parseDouble(request.getParameter("media"));
            Boolean isAtivo = true;
            service.register(nome, media, isAtivo);
            request.getRequestDispatcher("/dashboard/alunos").forward(request, response);
            page.close();


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

        }
    }
}
