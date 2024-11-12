<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--@elvariable id="userUuid" type="java.util.UUID"--%>
<%-- Imprime o UUID do usuário para depuração --%>
<html lang="pt-br" data-bs-theme="dark">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Adicionar Curso</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
</head>
<body>
<jsp:include page="../resources/components/header.jsp"/>
<h1>Adicionar Curso</h1>
<form action="" method="post" class="d-flex flex-column" style="margin: 0 30%">
    <label for="nome" style="font-size: 1.5em">Nome</label>
    <input type="text" id="nome" name="nome" required style="font-size: 2em">

    <label for="mediaMec" style="font-size: 1.5em">Média MEC</label>
    <input type="number" id="mediaMec" name="mediaMec" step="0.01" required style="font-size: 2em">

    <label for="isAtivo" style="font-size: 1.5em">Ativo</label>
    <select id="isAtivo" name="isAtivo" required style="font-size: 2em">
        <option value="true">Sim</option>
        <option value="false">Não</option>
    </select>

    <input type="text" name="user-uuid" value="${userUuid}" hidden="hidden">

    <div class="d-flex justify-content-center">
        <button class="btn btn-primary py-3 mt-5 px-5 shadow-lg"
                style="font-size: 1.5em; width: auto; background-color: #007bff; border-radius: 12px; transition: all 0.3s ease;">
            Adicionar
        </button>
    </div>
</form>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
<script src="../resources/js/cursos/listar-cursos.js"></script>
</body>
</html>
