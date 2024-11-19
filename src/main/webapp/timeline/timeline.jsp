<%--suppress unchecked --%>
<%@ page import="java.util.List" %>
<%@ page import="br.mendonca.testemaven.services.dto.TimelineItemDTO" %>
<%@ page pageEncoding="UTF-8" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="pt-br" data-bs-theme="dark">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Listar Disicplinas</title>
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
            <th>Tipo de Atividade</th>
            <th>Curtidas</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<TimelineItemDTO> atividades = (List<TimelineItemDTO>) request.getAttribute("atividades");
            for (TimelineItemDTO atividade : atividades) {
        %>
        <tr>
            <td><%= atividade.getDisplayName() %></td>
            <td><%= atividade.getEntityType() %></td>


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
