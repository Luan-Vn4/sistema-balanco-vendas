package br.mendonca.testemaven.controller;

import br.mendonca.testemaven.model.entities.Curso;
import br.mendonca.testemaven.services.CursoService;
import br.mendonca.testemaven.services.dto.UserDTO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet({"/cursos", "/cursos/create", "/cursos/update"})
public class CursoController extends HttpServlet {

    private static final CursoService cursoService = new CursoService();
    private static final Logger logger = Logger.getLogger(CursoController.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getSession(false) == null) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String userUuidStr = req.getParameter("user-uuid");
        System.out.println("UUID do usuário recebido: " + userUuidStr);

        Map<String, String[]> params = req.getParameterMap();
        if (params.isEmpty() || !params.containsKey("nome") || !params.containsKey("mediaMec") ||
                !params.containsKey("isAtivo") || !params.containsKey("user-uuid")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "É necessário enviar todos os dados necessários");
        }

        Curso curso = new Curso();
        curso.setNome(req.getParameter("nome"));
        curso.setMediaMec(Double.parseDouble(req.getParameter("mediaMec")));
        curso.setAtivo(Boolean.parseBoolean(req.getParameter("isAtivo")));
        curso.setUserUuid(UUID.fromString(req.getParameter("user-uuid")));

        try {
            cursoService.register(curso);
            resp.sendRedirect(req.getContextPath() + "/cursos");
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getSession(false) == null) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        if (req.getServletPath().equals("/cursos/create")) {
            getCreationPage(req, resp);
        } else if (req.getServletPath().equals("/cursos/update")) {
            getUpdatePage(req, resp);
        } else if (req.getParameterMap().isEmpty()) {
            getAll(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void getCreationPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getSession(false) == null) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        try {
            req.setAttribute("userUuid", ((UserDTO) req.getSession().getAttribute("user")).getUuid());
            req.getRequestDispatcher("/cursos/criar-curso.jsp").forward(req, resp);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    public void getUpdatePage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //TODO página de update
    }

    private void getAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            List<Curso> cursos = cursoService.listAllCurso();
            req.setAttribute("cursos", cursos);
            getServletContext().getRequestDispatcher("/cursos/listar-cursos.jsp").forward(req, resp);
        } catch (Exception e) {
            logger.log(Level.SEVERE , e.getMessage(), e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        if (req.getSession(false) == null) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        UserDTO user = (UserDTO) req.getSession().getAttribute("user");
        System.out.println(user.getName());

        if (!req.getParameterMap().containsKey("uuid")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String uuid = req.getParameter("uuid");
        try {
            cursoService.deletar(UUID.fromString(uuid));
        } catch (Exception e) {
            logger.log(Level.SEVERE,"UUID" + uuid + "inválido", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }




}
