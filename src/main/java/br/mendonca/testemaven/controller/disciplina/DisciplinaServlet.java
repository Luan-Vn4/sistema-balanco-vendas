package br.mendonca.testemaven.controller.disciplina;

import br.mendonca.testemaven.model.entities.Disciplina;
import br.mendonca.testemaven.model.entities.Professor;
import br.mendonca.testemaven.services.DisciplinaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@WebServlet("/disciplina")
public class DisciplinaServlet extends HttpServlet {

    private final DisciplinaService disciplinaService = new DisciplinaService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        int cargaHoraria = Integer.parseInt(req.getParameter("cargaHoraria"));
        // Verifica se o par�metro 'isAtiva' foi enviado (checkbox pode n�o estar presente se n�o marcado)
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
            // Redirecionar para a lista de disciplinas ap�s a adi��o bem-sucedida
            resp.sendRedirect(req.getContextPath() + "/disciplina");
        } catch (ClassNotFoundException | SQLException e) {
            // Lidar com erro, pode redirecionar para uma p�gina de erro ou mostrar uma mensagem
            e.printStackTrace(); // Loga o erro no console
            req.setAttribute("error", "Erro ao adicionar a disciplina");
            req.getRequestDispatcher("/disciplina").forward(req, resp); // Redireciona de volta para a p�gina de adi��o
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> params = req.getParameterMap();

        if (req.getParameterMap().isEmpty()) {
            getAll(req, resp);
        } else if (req.getServletPath().equals("/disciplina") && params.containsKey("uuid")){
            getViewPage(req, resp);
        }else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    public void getAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Disciplina> disciplinas = disciplinaService.listAllDisciplinas();
            req.setAttribute("disciplinas", disciplinas);
            req.getRequestDispatcher("/disciplina/disciplinas.jsp").forward(req, resp);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void getViewPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Optional<Disciplina> disciplina = Optional.ofNullable(disciplinaService.listDisciplinaByUuid(UUID.fromString(req.getParameter("uuid"))));

            if (disciplina.isEmpty()) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Não foi encontrado nenhuma disciplina com o " +
                        "UUID: " + req.getParameter("uuid"));
                return;
            }

            req.setAttribute("disciplina", disciplina.get());
            req.getRequestDispatcher("/disciplina/disciplinaView.jsp").forward(req, resp);
        } catch (IllegalArgumentException e) {
            System.out.println("UUID enviado é inválido: " + req.getParameter("uuid"));
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } catch (Exception e) {
            System.out.println("Erro ao obter página");
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuidParam = req.getParameter("uuid");

        if (uuidParam == null || uuidParam.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "UUID inv�lido ou ausente.");
            return;
        }

        try {
            UUID uuid = UUID.fromString(uuidParam);
            disciplinaService.deletar(uuid);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT); // Indica que a exclus�o foi bem-sucedida
        } catch (IllegalArgumentException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "UUID inv�lido.");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Erro ao deletar a disciplina", e);
        }
    }
}
