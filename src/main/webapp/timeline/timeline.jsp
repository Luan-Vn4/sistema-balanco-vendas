<%@ page import="java.util.List" %>
<%@ page import="br.mendonca.testemaven.services.dto.TimelineItemDTO" %>
<%@ page pageEncoding="UTF-8" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="pt-br" data-bs-theme="dark">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Timeline</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
</head>

<body>
<jsp:include page="/resources/components/header.jsp"/>
<main class="p-3">
    <h1>Timeline</h1>

    <div>
        <%
            List<TimelineItemDTO> atividades = (List<TimelineItemDTO>) request.getAttribute("atividades");
            for (TimelineItemDTO atividade : atividades) {
        %>
        <div class="card mb-3" style="max-width: 100%;">
            <div class="card-body">
                <h5 class="card-title"><%= atividade.getDisplayName() %></h5>
                <h6 class="card-subtitle mb-2 text-muted"><%= atividade.getEntityType() %></h6>
                <p class="card-text">Curtidas: <%= atividade.getCurtidas() %></p>
                <form action="${pageContext.request.contextPath}/timeline" method="POST">
                    <input type="hidden" name="entityType" value="<%= atividade.getEntityType() %>">
                    <input type="hidden" name="entityId" value="<%= atividade.getEntityId() %>">
                    <button type="submit" class="btn btn-outline-primary">
                        Curtir <i class="bi bi-heart"></i>
                    </button>
                </form>
            </div>
        </div>
        <%
            }
        %>
    </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
