package br.mendonca.testemaven.controller.disciplina;

import br.mendonca.testemaven.model.entities.Disciplina;
import br.mendonca.testemaven.services.DisciplinaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@WebServlet
public class DisciplinaServlet extends HttpServlet {

    DisciplinaService disciplinaService = new DisciplinaService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        int cargaHoraria = Integer.parseInt(req.getParameter("cargaHoraria"));
        boolean isAtiva = Boolean.parseBoolean(req.getParameter("isAtiva"));

        Disciplina disciplina = new Disciplina();
        disciplina.setNome(nome);
        disciplina.setCargaHoraria(cargaHoraria);
        disciplina.setIsAtiva(isAtiva);

        try {
            disciplinaService.register(disciplina);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }


        req.getRequestDispatcher("/disciplina/disciplinas.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            if(req.getParameter("uuid") != null){
                UUID uuid = UUID.fromString(req.getParameter("uuid"));
               Disciplina disciplina = disciplinaService.listDisciplinaByUuid(uuid);
               req.setAttribute("disciplina", disciplina);
                req.getRequestDispatcher("/disciplina/disciplinaView.jsp").forward(req, resp);
            } else {
                List<Disciplina> disciplinas = disciplinaService.listAllDisciplinas();
                req.setAttribute("disciplinas", disciplinas);
                req.getRequestDispatcher("/disciplina/disciplinas.jsp").forward(req, resp);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
