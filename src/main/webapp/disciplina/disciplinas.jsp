<%--suppress unchecked --%>
<%@ page import="java.util.List" %>
<%@ page import="br.mendonca.testemaven.model.entities.Professor" %>
<%@ page import="br.mendonca.testemaven.model.entities.Disciplina" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="pt-br" data-bs-theme="dark">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Listar Professores</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
</head>

<body>
<jsp:include page="/resources/components/header.jsp"/>
<main class="p-3">
    <h1>Listagem de Disciplinas</h1>
    <a class="d-flex justify-content-end text-decoration-none text-light" style="font-size:1.25em"
       href="${pageContext.request.contextPath}/disciplina/disciplinaAdd.jsp">
        <p>Adicionar <i class="bi bi-plus-circle"></i></p>
    </a>
    <table class="table">
        <thead>
        <tr>
            <th>Nome</th>
            <th>Carga horária</th>
            <th>Ativo</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Disciplina> disciplinas = (List<Disciplina>) request.getAttribute("disciplinas");
            for (Disciplina disciplina : disciplinas) {
        %>
        <tr>
            <td><a href="disciplina?uuid=<%= disciplina.getUuid() %>"><%= disciplina.getNome() %></a></td>
            <td><%= disciplina.getCargaHoraria() %></td>
            <td><%= disciplina.getIsAtiva() ? "Ativo ✅" : "Inativo ❌" %></td>
        </tr>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
