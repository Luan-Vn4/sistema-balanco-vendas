package br.mendonca.testemaven.controller;

import br.mendonca.testemaven.model.entities.Curso;
import br.mendonca.testemaven.services.CursoService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/cursos", "/cursos/create", "/cursos/update")
public class CursoController extends HttpServlet {

    private static final CursoService cursoService = new CursoService();
    private static final Logger logger = Logger.getLogger(CursoController.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getSession(false) == null) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        Map<String, String[]> params = req.getParameterMap();
        if (params.isEmpty() || !params.containsKey("nome") || !params.containsKey("mediaMec") ||
                !params.containsKey("isAtivo") || !params.containsKey("user-uuid")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "É necessário enviar todos os dados necessários");
            return;
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


}
