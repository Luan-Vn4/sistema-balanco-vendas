<%--suppress unchecked --%>
<%@ page import="br.mendonca.testemaven.model.entities.Professor" %>
<%@ page import="br.mendonca.testemaven.utils.PagedResult" %>
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
<jsp:include page="../resources/components/header.jsp"/>

<%
  int pageSize = 3;
  var pagedResult = (PagedResult<Professor>) request.getAttribute("professores");
%>

<main class="p-3">
  <h1>Listagem de Professores Deletados</h1>
  <br>
  <h3>Total: <%= pagedResult.getTotalElements() %></h3>
  <table class="table">
    <thead>
    <tr>
      <th>Nome</th>
      <th>Salário</th>
      <th>Ativo</th>
      <th></th>
    </tr>
    </thead>
    <tbody>
    <%
      for (Professor professor : pagedResult) {
    %>
    <tr>
      <td><%= professor.getNome() %></td>
      <td><%= String.format("R$ %.2f", professor.getSalario()) %></td>
      <td><%= professor.isAtivo() ? "Ativo ✅" : "Inativo ❌" %></td>
      <td>
        <a class="text-decoration-none text-light"
           href="/professores?uuid=<%= professor.getUuid() %>">
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
           href="/professores/deleted?page=<%= pagedResult.getCurrentPage()-1 %>&page-size=<%= pageSize %>">
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
      <li class="page-item">
        <a class="page-link"
           href="/professores/deleted?page=<%=i%>&page-size=<%= pageSize %>">
          <%= i+1 %>
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
           href="/professores/deleted?page=<%= pagedResult.getCurrentPage()+1 %>&page-size=<%= pageSize %>">
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
