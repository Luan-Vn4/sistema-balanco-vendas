<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="br.mendonca.testemaven.model.entities.Disciplina" %>

<% if (session.getAttribute("user") != null && request.getAttribute("disciplina") != null) {
    Disciplina disciplina = (Disciplina) request.getAttribute("disciplina");
%>

<!doctype html>
<html lang="pt-br" data-bs-theme="dark">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Detalhes da Disciplina</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="style.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../resources/components/header.jsp"/>
<main class="container mt-5">

    <h1 class="h3 mb-3 fw-normal">Detalhes da Disciplina</h1>

    <div class="card">
        <div class="card-body">
            <h5 class="card-title"><%= disciplina.getNome() %></h5>
            <p class="card-text"><strong>Carga Horária:</strong> <%= disciplina.getCargaHoraria() %> horas</p>
            <p class="card-text"><strong>Ativa:</strong> <%= disciplina.getIsAtiva() ? "Sim" : "Não" %></p>
            <p class="card-text"><strong>ID:</strong> <%= disciplina.getUuid() %></p>
        </div>
    </div>

    <div class="mt-3">
        <a href="javascript:history.back()" class="btn btn-secondary">Voltar</a>
        <form action="disciplina/delete?uuid=<%= disciplina.getUuid() %>" method="post">
            <button class="btn btn-danger">
                Deletar <i class="bi-trash"></i>
            </button>
        </form>

    </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>

<% } else { %>
<div class="alert alert-danger" role="alert">
    <strong>Erro:</strong> A disciplina não foi encontrada ou você não está logado.
</div>
<% } %>
