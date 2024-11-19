package br.mendonca.testemaven.controller.seguir;

import br.mendonca.testemaven.services.UserService;
import br.mendonca.testemaven.services.dto.UserDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/dashboard/seguindo")
public class SeguirServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = new UserService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDTO userDTO = (UserDTO) request.getSession().getAttribute("user");

        try {
            List<UserDTO> followedUsers = userService.getFollowedUsers(userDTO.getEmail());

            System.out.println(followedUsers);

            request.setAttribute("followedUsers", followedUsers);

            RequestDispatcher dispatcher = request.getRequestDispatcher("seguindo.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao recuperar dados dos seguidores");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDTO userDTO = (UserDTO) request.getSession().getAttribute("user");
        String loggedUserEmail = userDTO.getEmail();
        String followedEmail = request.getParameter("followedEmail");

        try {
            userService.unfollowUser(loggedUserEmail, followedEmail);
            response.sendRedirect(request.getContextPath() + "/dashboard/seguindo");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao atualizar seguidores");
        }
    }
}
