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
}
