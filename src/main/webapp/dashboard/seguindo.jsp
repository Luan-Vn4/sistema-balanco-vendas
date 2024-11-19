<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="br.mendonca.testemaven.services.dto.UserDTO"%>

<% if (session.getAttribute("user") != null) { %>

<!doctype html>
<html lang="pt-br" data-bs-theme="dark">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Usuários Seguidos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="style.css" rel="stylesheet">
</head>
<body class="d-flex align-items-center py-4 bg-body-tertiary">

<main class="w-100 m-auto form-container">
    <jsp:include page="../resources/components/header.jsp"/>

    <h1 class="h3 mb-3 fw-normal">Usuários Seguidos</h1>

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
            List<UserDTO> followedUsers = (List<UserDTO>) request.getAttribute("followedUsers");
            for (UserDTO user : followedUsers) {
        %>
        <tr>
            <td>Editar</td>
            <td>
                <form method="post" action="<%= request.getContextPath() + "/dashboard/seguindo" %>">
                    <input type="hidden" name="followedEmail" value="<%= user.getEmail() %>">
                    <button type="submit" class="btn btn-danger">Deixar de Seguir</button>
                </form>
            </td>
            <td><%= user.getName() %></td>
            <td><%= user.getEmail() %></td>
            <td>Apagar</td>
        </tr>
        <% } %>
        </tbody>
    </table>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

<% } else { %>
<p>Por favor, faça login para visualizar esta página.</p>
<% } %>
