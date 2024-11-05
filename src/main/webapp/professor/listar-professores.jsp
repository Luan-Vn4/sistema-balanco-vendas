<%--suppress unchecked --%>
<%@ page import="java.util.List" %>
<%@ page import="br.mendonca.testemaven.model.entities.Professor" %>
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
<main class="p-3">
  <h1>Listagem de Professores</h1>
  <a class="d-flex justify-content-end text-decoration-none text-light" style="font-size:1.25em"
     href="${pageContext.request.contextPath}/professores/create">
    <p>Adicionar <i class="bi bi-plus-circle"></i></p>
  </a>
  <table class="table">
    <thead>
    <tr>
      <th>Nome</th>
      <th>Salário</th>
      <th>Ativo</th>
    </tr>
    </thead>
    <tbody>
    <%
      List<Professor> professores = (List<Professor>) request.getAttribute("professores");
      for (Professor professor : professores) {
    %>
    <tr>
      <td><%= professor.getNome() %></td>
      <td><%= String.format("R$ %.2f", professor.getSalario()) %></td>
      <td><%= professor.isAtivo() ? "Ativo ✅" : "Inativo ❌" %></td>
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
