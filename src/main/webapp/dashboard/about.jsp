<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="br.mendonca.testemaven.services.dto.UserDTO" %>

<%
    if (session.getAttribute("user") != null) {
%>

<!doctype html>
<html lang="pt-br" data-bs-theme="dark">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Gerência de Configuração</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
            crossorigin="anonymous">
    <link href="style.css" rel="stylesheet">
</head>
<body class="d-flex align-items-center py-4 bg-body-tertiary">

<main class="w-100 m-auto form-container">
    <jsp:include page="../resources/components/header.jsp"/>

    <h1 class="h3 mb-3 fw-normal">About</h1>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Nome</th>
            <th scope="col">Entidade</th>
            <th scope="col">Regra de negócio</th>
        </tr>
        </thead>
        <tr>
            <td scope="col">Ewerton Mendonça</td>
            <td scope="col">Users</td>
            <td scope="col">Autenticação</td>
        </tr>
      <tr>
					<td scope="col">Melissa Rêgo Rodrigues</td>
        <td scope="col">Disciplina</td>
        <td scope="col">Lógica de disciplina do curso</td>
        <tr>
            <td scope="col">Erick Vinícius Bezerra Martins</td>
            <td scope="col">Aluno</td>
            <td scope="col">Coloque aqui sua regra de negócio</td>
        </tr>
        <tr>
            <td scope="col">Ana Beatriz de Oliveira Cavalcanti</td>
            <td scope="col">Curso</td>
            <td scope="col">3 - O usuário pode, na listagem de usuários, procurar por um usuário por parte do nome
             e utilizar filtros: um para a propriedade de número idade e outro para a propriedade booleana
             status. Os dados do banco devem vir filtrados por um ou mais endpoints.</td>
				<tr>
            <td scope="col">Luan Vilaça Nogueira</td>
            <td scope="col">Professor</td>
            <td scope="col">Chat entre usuários</td>
				</tr>
        <tbody>
        </tbody>
    </table>

<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous">

</script>
</body>
</html>
<%
}
%>
