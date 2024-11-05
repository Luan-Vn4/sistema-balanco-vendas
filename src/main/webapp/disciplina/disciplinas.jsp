<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="br.mendonca.testemaven.model.entities.Disciplina" %>

<% if (session.getAttribute("user") != null ) { %>

<!doctype html>
<html lang="pt-br" data-bs-theme="dark">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Gerência de Disciplinas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="style.css" rel="stylesheet">
</head>
<body class="d-flex align-items-center py-4 bg-body-tertiary">

<main class="w-100 m-auto form-container">
    <jsp:include page="/resources/components/header.jsp"/>

    <h1 class="h3 mb-3 fw-normal">Lista de Disciplinas</h1>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Nome</th>
            <th scope="col">Carga Horária</th>
            <th scope="col">Ativo</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Disciplina> disciplinas = (List<Disciplina>) request.getAttribute("disciplinas");
            System.out.println("Disciplinas recebidas: " + disciplinas);
            if (disciplinas != null && !disciplinas.isEmpty()) {
                for (Disciplina disciplina : disciplinas) {
        %>
        <tr>
            <td><%= disciplina.getNome() %></td>
            <td><%= disciplina.getCargaHoraria() %> horas</td>
            <td><%= disciplina.getIsAtiva() ? "Sim" : "Não" %></td>
            <td>
                <a href="/disciplina/edit?uuid=<%= disciplina.getUuid() %>" class="btn btn-primary btn-sm">Editar</a>
                <a href="/disciplina/delete?uuid=<%= disciplina.getUuid() %>" class="btn btn-danger btn-sm" onclick="return confirm('Deseja realmente apagar esta disciplina?');">Apagar</a>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="4">Nenhuma disciplina cadastrada.</td>
        </tr>
        <%
            }
        %>

        </tbody>

    </table>

    <a href="/disciplina/disciplinaAdd.jsp" class="btn btn-success">Adicionar Nova Disciplina</a>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>

<% } %>
