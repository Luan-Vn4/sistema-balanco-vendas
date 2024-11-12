package br.mendonca.testemaven.controller;

import br.mendonca.testemaven.model.entities.Curso;
import br.mendonca.testemaven.model.entities.Professor;
import br.mendonca.testemaven.services.CursoService;
import br.mendonca.testemaven.utils.PageRequest;
import br.mendonca.testemaven.utils.PagedResult;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@WebServlet({"/cursos", "/cursos/create", "/cursos/delete"})
public class CursoController extends HttpServlet {

    private static final CursoService cursoService = new CursoService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getSession(false) == null) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }


        Map<String, String[]> params = req.getParameterMap();
        if (params.isEmpty() || !params.containsKey("nome") || !params.containsKey("mediaMec") ||
                !params.containsKey("isAtivo")) {
            create(req, resp);
        }else if (req.getServletPath().equals("/cursos/delete") && params.containsKey("uuid")) {
            delete(req, resp);
        }else{
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Curso curso = new Curso();
        curso.setNome(req.getParameter("nome"));
        curso.setMediaMec(Double.parseDouble(req.getParameter("mediaMec")));
        curso.setAtivo(Boolean.parseBoolean(req.getParameter("isAtivo")));

        try {
            cursoService.register(curso);
            resp.sendRedirect(req.getContextPath() + "/cursos");
        } catch (Exception e) {
            System.out.println("Erro ao registrar curso: " + curso);
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            cursoService.delete(UUID.fromString(req.getParameter("uuid")));

            Map<String, String[]> params = req.getParameterMap();
            if (params.containsKey("page") && params.containsKey("page-size")) {
                resp.sendRedirect(String.format("/cursos?page=%d&page-size=%d",
                        Integer.parseInt(req.getParameter("page")),
                        Integer.parseInt(req.getParameter("page-size")))
                );
                return;
            }
            resp.sendRedirect("/cursos");
        } catch (Exception e) {
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

        Map<String, String[]> params = req.getParameterMap();
        if (req.getServletPath().equals("/cursos/create")) {
            getCreationPage(req, resp);
        } else if (req.getServletPath().equals("/cursos") && params.isEmpty()) {
            resp.sendRedirect("/cursos?page=0&page-size=3");
        } else if (req.getServletPath().equals("/cursos") && params.containsKey("uuid")) {
            getViewPage(req, resp);
        } else if (req.getServletPath().equals("/cursos") && params.containsKey("page")
            && params.containsKey("page-size")) {
            getPaginatedListPage(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void getCreationPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            req.getRequestDispatcher("/cursos/criar-curso.jsp").forward(req, resp);
        } catch (Exception e) {
            System.out.println("Erro ao obter página de criar curso");
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


    private void getPaginatedListPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int page = Integer.parseInt(req.getParameter("page"));
        int pageSize = Integer.parseInt(req.getParameter("page-size"));
        PageRequest pageRequest = new PageRequest(page, pageSize);

        try {
            PagedResult<Curso> cursos = cursoService.getAll(pageRequest);
            req.setAttribute("cursos", cursos);
            req.getRequestDispatcher("/cursos/listar-cursos.jsp").forward(req, resp);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }




    private void getViewPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Optional<Curso> curso = cursoService.getByUuid(UUID.fromString(req.getParameter("uuid")));

            if (curso.isEmpty()) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Não foi encontrado nenhum curso com o " +
                        "UUID: " + req.getParameter("uuid"));
                return;
            }

            req.setAttribute("curso", curso.get());
            req.getRequestDispatcher("/cursos/visualizar-curso.jsp").forward(req, resp);
        } catch (IllegalArgumentException e) {
            System.out.println("UUID enviado é inválido: " + req.getParameter("uuid"));
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } catch (Exception e) {
            System.out.println("Erro ao obter página de criar curso");
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }





}
