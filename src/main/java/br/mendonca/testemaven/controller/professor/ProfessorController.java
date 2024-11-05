package br.mendonca.testemaven.controller.professor;

import br.mendonca.testemaven.model.entities.Professor;
import br.mendonca.testemaven.services.professor.ProfessorService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@SuppressWarnings("CallToPrintStackTrace")
@WebServlet(value = {"/professores", "/professores/create"})
public class ProfessorController extends HttpServlet {

    ProfessorService professorService = new ProfessorService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getSession(false) == null) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        Map<String, String[]> params = req.getParameterMap();
        if (params.isEmpty() | !params.containsKey("nome") | !params.containsKey("salario")
            | !params.containsKey("ativo")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Há dados do professor que estão faltando");
        }

        Professor professor = new Professor();
        professor.setNome(req.getParameter("nome"));
        professor.setSalario(Double.parseDouble(req.getParameter("salario")));
        professor.setAtivo(Boolean.parseBoolean(req.getParameter("ativo")));

        try {
            professorService.create(professor);
            resp.sendRedirect(req.getContextPath() + "/professores");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar professor: " + professor);
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getSession(false) == null) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        if (req.getServletPath().equals("/professores")) {
            getListPage(req, resp);
        } else if (req.getServletPath().equals("/professores/create")) {
            getCreationPage(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void getListPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            List<Professor> list = professorService.getAll();
            req.setAttribute("professores", list);
            req.getRequestDispatcher("/professor/listar-professores.jsp").forward(req, resp);
        } catch (Exception e) {
            System.out.println("Erro ao listar os professores");
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void getCreationPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            req.getRequestDispatcher("/professor/criar-professor.jsp").forward(req, resp);
        } catch (Exception e) {
            System.out.println("Erro ao obter página de criar professor");
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}
