<%--suppress unchecked --%>
<%@ page import="java.util.List" %>
<%@ page import="br.mendonca.testemaven.model.entities.Curso" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="pt-br" data-bs-theme="dark">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Listar Cursos</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXhW+ALEwIH"
        crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
</head>

<body>
<jsp:include page="../resources/components/header.jsp"/>
<main class="p-3">
  <h1>Listagem de Cursos</h1>
  <a class="d-flex justify-content-end text-decoration-none text-light" style="font-size:1.25em"
     href="${pageContext.request.contextPath}/cursos/create">
    <p>Adicionar <i class="bi bi-plus-circle"></i></p>
  </a>
  <table class="table">
    <thead>
    <tr>
      <th>Nome</th>
      <th>Média MEC</th>
      <th>Ativo</th>
      <th></th>
      <th></th>
      <th></th>
    </tr>
    </thead>
    <tbody>
    <%
      List<Curso> cursos = (List<Curso>) request.getAttribute("cursos");
      for (Curso curso : cursos) {
    %>
    <tr>
      <td><%= curso.getNome() %></td>
      <td><%= String.format("%.2f", curso.getMediaMec()) %></td>
      <td><%= curso.getAtivo() ? "Ativo ✅" : "Inativo ❌" %></td>
      <td><a href="cursos?uuid=<%= curso.getUuid() %>" class="text-decoration-none text-light">
        Visualizar <i class="bi-eye"></i></a>
      </td>
        <form action="cursos/delete?uuid=<%= curso.getUuid() %>" method="post">
          <button class="text-decoration-none text-light btn btn-link p-0">
            Deletar <i class="bi-trash"></i>
          </button>
        </form>
      </td>
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
