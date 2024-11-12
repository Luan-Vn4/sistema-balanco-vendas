<%--suppress unchecked --%>
<%@ page import="java.util.List" %>
<%@ page import="br.mendonca.testemaven.model.entities.Disciplina" %>
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
        </tr>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>

    <div class="d-flex justify-content-center">
        <nav>
            <ul class="pagination">
                <%
                    Integer currentPage = (Integer) request.getAttribute("currentPage");
                    Integer totalPages = (Integer) request.getAttribute("totalPages");
                    Boolean deleted = Boolean.valueOf(request.getParameter("deleted"));
                    if (currentPage > 1) {
                %>
                <li class="page-item">
                    <a class="page-link" href="disciplina?page=<%= currentPage - 1 %>&deleted=<%= deleted%>" aria-label="Anterior">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <%
                    }
                %>

                <!-- Exibe a página atual -->
                <h2><%= currentPage %></h2>

                <%-- Botão "Próxima" visível apenas se currentPage for menor que totalPages --%>
                <%
                    if (currentPage < totalPages) {
                %>
                <li class="page-item">
                    <a class="page-link" href="disciplina?page=<%= currentPage + 1 %>&deleted=<%= deleted%>" aria-label="Próxima">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
                <%
                    }
                %>
            </ul>
        </nav>
    </div>

    <%
        if (deleted) {
    %>
    <div class="text-center mt-3">
        <a href="disciplina?page=<%= 1 %>&deleted=false" class="btn btn-success">Ver Disciplinas</a>
    </div>
    <%
        } else {
    %>
    <div class="text-center mt-3">
        <a href="disciplina?page=<%= 1 %>&deleted=true" class="btn btn-danger">Ver Disciplinas Deletadas</a>
    </div>
    <%
        }
    %>

</main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
