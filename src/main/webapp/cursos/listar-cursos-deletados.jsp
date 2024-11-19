<%--suppress unchecked --%>
<%@ page import="br.mendonca.testemaven.model.entities.Curso" %>
<%@ page import="br.mendonca.testemaven.utils.PagedResult" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="pt-br" data-bs-theme="dark">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Listar Cursos Deletados</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
</head>

<body>
<jsp:include page="../resources/components/header.jsp"/>

<%
    int pageSize = 3;
    String searchAttribute = (String) request.getAttribute("search");
    String searchParam = searchAttribute != null ? "&search=" + searchAttribute : "";
    var pagedResult = (PagedResult<Curso>) request.getAttribute("cursos");
%>

<main class="p-3">
    <h1>Listagem de Cursos Deletados</h1>
    <br>
    <h3>Total: <%= pagedResult.getTotalElements() %></h3>
    <form class="d-flex" method="get" action="/cursos/deleted">
<%--        <input class="form-control mr-sm-2" type="search" name="search" placeholder="Buscar..." aria-label="Search">--%>
        <input name="page" value="0" hidden>
        <input name="page-size" value="<%= pageSize %>" hidden>
<%--        <button class="btn btn-primary">Buscar</button>--%>
    </form>
    <table class="table">
        <thead>
        <tr>
            <th>Nome</th>
            <th>Média MEC</th>
            <th>Ativo</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Curso curso : pagedResult) {
        %>
        <tr>
            <td><%= curso.getNome() %></td>
            <td><%= String.format("%.2f", curso.getMediaMec()) %></td>
            <td><%= curso.getAtivo() ? "Ativo ✅" : "Inativo ❌" %></td>
            <td>
                <a class="text-decoration-none text-light"
                   href="/cursos?uuid=<%= curso.getUuid() %>">
                    Visualizar <i class="bi bi-eye"></i>
                </a>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</main>

<nav aria-label="Page navigation" class="d-flex justify-content-center">
    <div class="overflow-auto">
        <!-- Botão de voltar -->
        <ul class="pagination">
            <%
                if (pagedResult.hasPrevious()) {
            %>
            <li class="page-item">
                <a class="page-link"
                   href="/cursos/deleted?page=<%= pagedResult.getCurrentPage() - 1 %>&page-size=<%= pageSize %><%= searchParam %>">
                    Voltar
                </a>
            </li>
            <%
                }
            %>

            <!-- Display das opções de páginas -->
            <%
                for (int i = 0; i < pagedResult.getTotalPages(); i++) {
            %>
            <li class="page-item <%= i == pagedResult.getCurrentPage() ? "active" : "" %>">
                <a class="page-link"
                   href="/cursos/deleted?page=<%= i %>&page-size=<%= pageSize %><%= searchParam %>">
                    <%= i + 1 %>
                </a>
            </li>
            <%
                }
            %>

            <!-- Botão de avançar -->
            <%
                if (pagedResult.hasNext()) {
            %>
            <li class="page-item">
                <a class="page-link"
                   href="/cursos/deleted?page=<%= pagedResult.getCurrentPage() + 1 %>&page-size=<%= pageSize %><%= searchParam %>">
                    Avançar
                </a>
            </li>
            <%
                }
            %>
        </ul>
    </div>
</nav>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
