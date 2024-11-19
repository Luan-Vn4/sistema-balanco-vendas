<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="br.mendonca.testemaven.services.dto.UserDTO"%>
<%@ page import="br.mendonca.testemaven.dao.UserDAO" %>
<%@ page import="br.mendonca.testemaven.services.UserService" %>
<%@ page import="java.sql.SQLException" %>

<% if (session.getAttribute("user") != null && request.getAttribute("lista") != null) { %>

<!doctype html>
<html lang="pt-br" data-bs-theme="dark">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Gerência de Configuração</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  	<link href="style.css" rel="stylesheet">
  </head>
  <body class="d-flex align-items-center py-4 bg-body-tertiary">
  
    <main class="w-100 m-auto form-container">
		<jsp:include page="../resources/components/header.jsp"/>
    
    	<h1 class="h3 mb-3 fw-normal">Usuários</h1>
		<table class="table">
			<thead>
				<tr>
					<th scope="col"></th>
					<th scope="col"></th>
					<th scope="col">Nome</th>
					<th scope="col">E-mail</th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
			<%
			UserDTO loggedUser = (UserDTO) session.getAttribute("user");
			List<UserDTO> lista = (List<UserDTO>) request.getAttribute("lista");
			for (UserDTO user : lista) {
				UserService userService = new UserService();
                boolean isFollowing = false;
                try {
                    isFollowing = userService.getFollowedUsers(loggedUser.getEmail())
                            .stream()
                            .anyMatch(followed -> followed.getEmail().equals(user.getEmail()));
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            %>
			<tr>
				<td>Editar</td>
				<td>
					<% if (!loggedUser.getEmail().equals(user.getEmail())) { %>
					<form method="post" action="<%= request.getContextPath() + "/dashboard/users" %>">
						<input type="hidden" name="userEmail" value="<%= user.getEmail() %>">
						<input type="hidden" name="action" value="<%= isFollowing ? "" : "follow" %>">
						<button type="submit" class="btn btn-sm <%= isFollowing ? "btn-success" : "btn-primary" %>">
							<%= isFollowing ? "Seguindo" : "Seguir" %>
						</button>
					</form>
					<% } %>
				</td>
				<td><%= user.getName() %></td>
				<td><%= user.getEmail() %></td>
				<td>Apagar</td>
			</tr>
			<% } %>
			</tbody>
		</table>
		
		
	</main>
    
    
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
  </body>
</html>

<% } %>