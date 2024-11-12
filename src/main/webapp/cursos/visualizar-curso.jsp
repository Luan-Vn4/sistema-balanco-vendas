<%@ page import="br.mendonca.testemaven.model.entities.Curso" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="pt-br" data-bs-theme="dark">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Visualizar Curso</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
        crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
  <style>
    body {
      background-color: #121212;
      color: #f1f1f1;
    }

    h1, h2 {
      font-family: 'Arial', sans-serif;
      color: #f39c12;
      margin-bottom: 20px;
    }

    h2 {
      font-size: 1.8em;
    }

    p {
      font-size: 1.5em;
      line-height: 1.5;
      margin-bottom: 30px;
    }

    .course-details {
      background-color: #484D50;
      border-radius: 8px;
      padding: 20px;
      box-shadow: 0 4px 10px rgba(0, 0, 0, 0.5);
    }

    .status-active {
      color: #2ecc71;
    }

    .status-inactive {
      color: #e74c3c;
    }

    .container {
      max-width: 900px;
      margin: auto;
    }

    .btn-back {
      background-color: #007bff;
      color: white;
      padding: 10px 20px;
      border-radius: 10px;
      text-decoration: none;
      display: inline-block;
      font-size: 1.2em;
      margin-top: 30px;
      transition: background-color 0.3s ease;
    }

    .btn-back:hover {
      background-color: #0056b3;
    }
  </style>
</head>

<body>

<jsp:include page="../resources/components/header.jsp"/>

<% Curso curso = (Curso) request.getAttribute("curso"); %>

<main class="container p-4">
  <h1 class="text-center">Visualizar Curso</h1>

  <div class="course-details">
    <h2 class="text-center">Nome do Curso</h2>
    <p class="text-center"><%= curso.getNome() %></p>

    <h2 class="text-center">Média MEC</h2>
    <p class="text-center"><%= String.format("%.2f", curso.getMediaMec()) %></p>

    <h2 class="text-center">Status</h2>
    <p class="text-center <%= curso.getAtivo() ? "status-active" : "status-inactive" %>">
      <%= curso.getAtivo() ? "Ativo ✅" : "Inativo ❌" %>
    </p>

    <h2 class="text-center">UUID do Usuário</h2>
    <p class="text-center"><%= curso.getUserUuid() %></p>
  </div>

  <a href="/cursos" class="btn-back">Voltar para a lista de cursos</a>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>

</html>
