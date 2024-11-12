<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br" data-bs-theme="dark">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Adicionar Nova Disciplina</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<main class="container mt-5">
    <jsp:include page="../resources/components/header.jsp"/>

    <h1>Adicionar Nova Disciplina</h1>
    <form action="/disciplina/create" method="post">
        <div class="mb-3">
            <label for="nome" class="form-label">Nome da Disciplina</label>
            <input type="text" class="form-control" id="nome" name="nome" required>
        </div>
        <div class="mb-3">
            <label for="cargaHoraria" class="form-label">Carga Horária</label>
            <input type="number" class="form-control" id="cargaHoraria" name="cargaHoraria" required>
        </div>
        <select id="atividade" name="isAtiva" style="font-size: 1.4em">
            <option value="true">Ativo</option>
            <option value="false">Inativo</option>
        </select>
        <button type="submit" class="btn btn-primary">Adicionar Disciplina</button>
    </form>
</main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
