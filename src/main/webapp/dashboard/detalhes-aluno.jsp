<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="br.mendonca.testemaven.model.entities.Aluno" %>
<%@ page import="br.mendonca.testemaven.services.AlunoService" %>
<%@ page import="java.util.UUID" %>

<%
    String alunoId = request.getParameter("id");

    if (alunoId == null || alunoId.isEmpty()) {
        response.sendRedirect("dashboard/alunos");
        return;
    }

    AlunoService alunoService = new AlunoService();
    Aluno aluno = alunoService.searchById(UUID.fromString(alunoId));

    if (aluno == null) {
        response.sendRedirect("dashboard/alunos");
        return;
    }
%>

<!doctype html>
<html lang="pt-br" data-bs-theme="dark">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Detalhes do Aluno</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <link href="style.css" rel="stylesheet">
</head>
<body class="d-flex align-items-center py-4 bg-body-tertiary">

<main class="w-100 m-auto form-container">
    <jsp:include page="../resources/components/header.jsp"/>

    <h1 class="h3 mb-3 fw-normal">Detalhes do Aluno</h1>
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">Nome: <%= aluno.getNome() %></h5>
            <p><strong>Média:</strong> <%= aluno.getMedia() %></p>
            <p><strong>Status:</strong> <%= aluno.isAtivo() ? "Ativo" : "Inativo" %></p>

            <a href="${pageContext.request.contextPath}/dashboard/alunos" class="btn btn-primary">Voltar para a lista</a>
        </div>
    </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>
</html>
