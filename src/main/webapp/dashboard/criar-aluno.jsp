<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="br.mendonca.testemaven.model.entities.Aluno" %>

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

    <h1 class="h3 mb-3 fw-normal">Criar Alunos</h1>
    <form method="post" action="${pageContext.request.contextPath}/dashboard/alunos">
        <label for="nome" style="font-size: 1.5em">Nome aluno</label>
        <input type="text" id="nome" name="nome" required style="font-size: 2em">

        <label for="media" style="font-size: 1.5em">Média do aluno</label>
        <input type="number" step="0.01" id="media" name="media" required style="font-size: 2em">

        <div class="d-flex justify-content-center">
            <button class="btn btn-primary w-25 py-2 mt-5 px-5" style="font-size: 1.5em">Adicionar Aluno</button>
        </div>
    </form>


</main>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
