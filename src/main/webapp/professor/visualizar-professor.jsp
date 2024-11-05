<%--suppress unchecked --%>
<%@ page import="br.mendonca.testemaven.model.entities.Professor" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="pt-br" data-bs-theme="dark">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Visualizar Professor</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
        crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
</head>

<body>
  <jsp:include page="../resources/components/header.jsp"/>

  <% Professor professor = (Professor) request.getAttribute("professor"); %>

  <main class="p-3 d-flex flex-column justify-content-center">
    <h1>Visualizar Professor</h1>
    <h2 class="text-center">Nome</h2>
    <p class="text-center" style="font-size: 1.5em"><%= professor.getNome() %></p>
    <h2 class="text-center">Salario</h2>
    <p class="text-center" style="font-size: 1.5em"><%= String.format("R$ %.2f", professor.getSalario()) %></p>
    <h2 class="text-center">Ativo</h2>
    <p class="text-center" style="font-size: 1.5em"><%= professor.isAtivo() ? "Ativo ✅" : "Inativo ❌" %></p>
  </main>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
          crossorigin="anonymous"></script>
</body>
</html>
