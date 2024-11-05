package br.mendonca.testemaven.controller.professor;

import br.mendonca.testemaven.model.entities.Professor;
import br.mendonca.testemaven.services.professor.ProfessorService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@SuppressWarnings("CallToPrintStackTrace")
@WebServlet(value = {"/professores", "/professores/create"})
public class ProfessorController extends HttpServlet {

    ProfessorService professorService = new ProfessorService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getSession(false) == null) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        if (req.getServletPath().equals("/professores")) {
            getListPage(req, resp);
        } else if (req.getServletPath().equals("/professores/create")) {
            // TODO servlet para criação do professor
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

}
