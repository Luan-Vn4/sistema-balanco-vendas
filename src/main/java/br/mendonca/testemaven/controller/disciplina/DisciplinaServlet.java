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

@WebServlet(value = {"/disciplina", "/disciplina/create", "/disciplina/delete"})
public class DisciplinaServlet extends HttpServlet {

    private final DisciplinaService disciplinaService = new DisciplinaService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession(false) == null) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        Map<String, String[]> params = req.getParameterMap();
        if (req.getServletPath().equals("/disciplina/create") && !params.isEmpty() && params.containsKey("nome") && params.containsKey("cargaHoraria")
                && params.containsKey("isAtiva")) {
            create(req, resp);
        } else if (req.getServletPath().equals("/disciplina/delete") && params.containsKey("uuid")) {
            delete(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        int cargaHoraria = Integer.parseInt(req.getParameter("cargaHoraria"));

        Disciplina disciplina = new Disciplina();
        disciplina.setNome(nome);
        disciplina.setCargaHoraria(cargaHoraria);
        disciplina.setIsAtiva(Boolean.parseBoolean(req.getParameter("isAtiva")));

        System.out.println("Disciplina: " + disciplina.getNome());
        System.out.println("CargaHoraria: " + cargaHoraria);
        System.out.println("isAtiva" + req.getParameter("isAtiva"));

        try {
            disciplinaService.register(disciplina);

            resp.sendRedirect(req.getContextPath() + "/disciplina");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "Erro ao adicionar a disciplina");
            req.getRequestDispatcher("/disciplina").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> params = req.getParameterMap();

        if (req.getParameterMap().isEmpty()) {
            getAll(req, resp);
        } else if (req.getServletPath().equals("/disciplina") && params.containsKey("page") && "true".equals(req.getParameter("deleted"))) {
            getAllDeleted(req, resp);
        }  else if (req.getServletPath().equals("/disciplina") && params.containsKey("page")) {
            getAll(req, resp);
        } else if (req.getServletPath().equals("/disciplina") && params.containsKey("uuid")){
            getViewPage(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    public void getAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int page = 1;
            int pageSize = 3;

            if (req.getParameter("page") != null) {
                page = Integer.parseInt(req.getParameter("page"));
            }

            int totalDisciplinas = disciplinaService.countDisciplinas();
            int totalPages = (int) Math.ceil((double) totalDisciplinas / pageSize);

            List<Disciplina> disciplinas = disciplinaService.getDisciplinasPaginated(page, pageSize);

            req.setAttribute("disciplinas", disciplinas);
            req.setAttribute("currentPage", page);
            req.setAttribute("totalPages", totalPages);

            req.getRequestDispatcher("/disciplina/disciplinas.jsp").forward(req, resp);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Erro ao listar disciplinas paginadas", e);
        }
    }

    public void getAllDeleted(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int page = 1;
            int pageSize = 3;

            if (req.getParameter("page") != null) {
                page = Integer.parseInt(req.getParameter("page"));
            }

            int totalDisciplinas = disciplinaService.countDeletedDisciplinas();
            int totalPages = (int) Math.ceil((double) totalDisciplinas / pageSize);

            List<Disciplina> disciplinas = disciplinaService.getDeletedDisciplinasPaginated(page, pageSize);
            System.out.println("Disciplinas deletadas: " + disciplinas);

            req.setAttribute("disciplinas", disciplinas);
            req.setAttribute("currentPage", page);
            req.setAttribute("totalPages", totalPages);

            req.getRequestDispatcher("/disciplina/disciplinas.jsp").forward(req, resp);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Erro ao listar disciplinas deletadas paginadas", e);
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

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuidParam = req.getParameter("uuid");

        if (uuidParam == null || uuidParam.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "UUID inválido ou ausente.");
            return;
        }

        try {
            UUID uuid = UUID.fromString(uuidParam);

            boolean updated = disciplinaService.deletar(uuid);

            if (updated) {
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                resp.sendRedirect(req.getContextPath() + "/disciplina");
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Disciplina não encontrada.");
            }
        } catch (IllegalArgumentException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "UUID inválido.");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Erro ao marcar a disciplina como invisível", e);
        }
    }
}
