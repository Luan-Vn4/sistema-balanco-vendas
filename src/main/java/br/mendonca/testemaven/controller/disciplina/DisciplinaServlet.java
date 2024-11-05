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

@WebServlet("/disciplina")
public class DisciplinaServlet extends HttpServlet {

    private final DisciplinaService disciplinaService = new DisciplinaService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        int cargaHoraria = Integer.parseInt(req.getParameter("cargaHoraria"));
        // Verifica se o parâmetro 'isAtiva' foi enviado (checkbox pode não estar presente se não marcado)
        boolean isAtiva = req.getParameter("isAtiva") != null;

        Disciplina disciplina = new Disciplina();
        disciplina.setNome(nome);
        disciplina.setCargaHoraria(cargaHoraria);
        disciplina.setIsAtiva(isAtiva);

        System.out.println("Disciplina: " + disciplina.getNome());
        System.out.println("CargaHoraria: " + cargaHoraria);
        System.out.println("isAtiva" + isAtiva);

        try {
            disciplinaService.register(disciplina);
            // Redirecionar para a lista de disciplinas após a adição bem-sucedida
            resp.sendRedirect(req.getContextPath() + "/disciplina/disciplinas.jsp"); // Redireciona para o método doGet
        } catch (ClassNotFoundException | SQLException e) {
            // Lidar com erro, pode redirecionar para uma página de erro ou mostrar uma mensagem
            e.printStackTrace(); // Loga o erro no console
            req.setAttribute("error", "Erro ao adicionar a disciplina");
            req.getRequestDispatcher("/disciplina").forward(req, resp); // Redireciona de volta para a página de adição
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet chamado!");
        try {
            List<Disciplina> disciplinas = disciplinaService.listAllDisciplinas();
            req.setAttribute("disciplinas", disciplinas);
            System.out.println(disciplinas);// Definindo a lista no request
            req.getRequestDispatcher("/disciplina/disciplinas.jsp").forward(req, resp);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuidParam = req.getParameter("uuid");

        if (uuidParam == null || uuidParam.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "UUID inválido ou ausente.");
            return;
        }

        try {
            UUID uuid = UUID.fromString(uuidParam);
            disciplinaService.deletar(uuid);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT); // Indica que a exclusão foi bem-sucedida
        } catch (IllegalArgumentException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "UUID inválido.");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Erro ao deletar a disciplina", e);
        }
    }
}
