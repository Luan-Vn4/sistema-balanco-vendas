<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en" data-bs-theme="dark">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Criar professor</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
        crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
</head>

<body>
  <jsp:include page="../resources/components/header.jsp"/>
  <h1>Criar Professor</h1>
  <main class="p-3">
    <form action="/professores" method="post" class="d-flex flex-column" style="margin: 0 30%">
      <label for="nome" style="font-size: 1.5em">Nome</label>
      <input type="text" id="nome" name="nome" required style="font-size: 2em">
      <label for="salario" style="font-size: 1.5em">Salário (R$)</label>
      <input type="number" step="0.01" id="salario" name="salario" required style="font-size: 2em">
      <label for="atividade" style="font-size: 1.5em">Atividade</label>
      <select id="atividade" name="ativo" style="font-size: 2em">
        <option value="true">Ativo</option>
        <option value="false">Inativo</option>
      </select>

      <div class="d-flex justify-content-center">
        <button class="btn btn-primary w-25 py-2 mt-5 px-5" style="font-size: 1.5em">Adicionar</button>
      </div>
    </form>
  </main>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
          crossorigin="anonymous"></script>
</body>
</html>
