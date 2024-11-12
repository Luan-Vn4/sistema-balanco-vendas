<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="br.mendonca.testemaven.model.entities.Aluno" %>

<% if (session.getAttribute("user") != null && request.getAttribute("lista") != null) { %>

<!doctype html>
<html lang="pt-br" data-bs-theme="dark">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Gerência de Configuração</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="style.css" rel="stylesheet">
</head>
<body class="d-flex align-items-center py-4 bg-body-tertiary">

<main class="w-100 m-auto form-container">
    <jsp:include page="../resources/components/header.jsp"/>

    <h1 class="h3 mb-3 fw-normal">Lista de Alunos</h1>

    <div class="d-flex justify-content-end mb-3">
        <form method="get" action="alunos">
            <% if (request.getParameter("ocultar") != null) { %>
             <button type="submit" class="btn btn-primary">Mostrar alunos ativos</button>
            <% } else { %>
             <input type="hidden" name="ocultar" value="true">
            <button type="submit" class="btn btn-primary">Mostrar alunos ocultos</button>
            <% } %>
        </form>

        <a href="${pageContext.request.contextPath}/dashboard/criar-aluno.jsp" class="btn btn-success">
            Adicionar <i class="bi bi-plus-circle"></i>
        </a>
    </div>

    <table class="table">
        <thead>
        <tr>
            <th scope="col"></th>
            <th scope="col">Nome</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Aluno> lista = (List<Aluno>) request.getAttribute("lista");
            for (Aluno aluno : lista) {
        %>
        <tr>
            <td>Editar</td>
            <td>
                <a href="${pageContext.request.contextPath}/dashboard/detalhes-aluno.jsp?id=<%= aluno.getUuid() %>"><%= aluno.getNome() %>
                </a></td>
            <td><a href="${pageContext.request.contextPath}/dashboard/alunos?deletar=true&id=<%= aluno.getUuid() %>">Apagar</a>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>

    <div class="pagination">
        <%
            int currentPage = (Integer) request.getAttribute("currentPage");
            int totalPages = (Integer) request.getAttribute("totalPages");
            if (currentPage > 1) {
        %>
        <a href="?page=<%= currentPage - 1 %>" class="btn btn-primary">Anterior</a>
        <% } else { %>
        <button class="btn btn-secondary" disabled>Anterior</button>
        <% } %>

        <span>Página <%= currentPage %> de <%= totalPages %></span>

        <%
            if (currentPage < totalPages) {
        %>
        <a href="?page=<%= currentPage + 1 %>" class="btn btn-primary">Próxima</a>
        <% } else { %>
        <button class="btn btn-secondary" disabled>Próxima</button>
        <% } %>
    </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>

<% } %>
