<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="br.mendonca.testemaven.model.entities.Aluno" %>

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

    <h1 class="h3 mb-3 fw-normal">Lista de Alunos</h1>
    <a class="d-flex justify-content-end text-decoration-none text-light" style="font-size:1.25em"
       href="${pageContext.request.contextPath}/dashboard/criar-aluno.jsp">
        <p>Adicionar <i class="bi bi-plus-circle"></i></p>
    </a>
    <table class="table">
        <thead>
        <tr>
            <th scope="col"></th>
            <th scope="col">Nome</th>
            <th scope="col">Média</th>
            <th scope="col">Ativo</th>
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
            <td><%= aluno.getNome() %></td>
            <td><%= aluno.getMedia() %></td>
            <td><%= aluno.isAtivo() %></td>
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