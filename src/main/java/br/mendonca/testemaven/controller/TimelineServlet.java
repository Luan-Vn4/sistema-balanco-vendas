package br.mendonca.testemaven.controller;


import br.mendonca.testemaven.services.TimelineService;
import br.mendonca.testemaven.services.dto.TimelineItemDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@WebServlet("/timeline")
public class TimelineServlet extends HttpServlet {

    private final TimelineService timelineService = new TimelineService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TimelineItemDTO> atividades = null;
        try {
            atividades = timelineService.getTimeline();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("atividades", atividades);
        req.getRequestDispatcher("/timeline/timeline.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String entityType = req.getParameter("entityType");
        String entityId = req.getParameter("entityId");

        if (entityType != null && entityId != null) {
            UUID entityUuid = UUID.fromString(entityId);

            try {
                switch (entityType) {
                    case "Curso":
                        timelineService.incrementCurtidasForCurso(entityUuid);
                        break;
                    case "Disciplina":
                        timelineService.incrementCurtidasForDisciplina(entityUuid);
                        break;
                    case "Professor":
                        timelineService.incrementCurtidasForProfessor(entityUuid);
                        break;
                    case "Aluno":
                        timelineService.incrementCurtidasForAluno(entityUuid);
                        break;
                    default:
                        throw new IllegalArgumentException("Tipo de entidade desconhecido: " + entityType);
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException("Erro ao adicionar curtida para " + entityType, e);
            }
        }

        resp.sendRedirect(req.getContextPath() + "/timeline");
    }



}
