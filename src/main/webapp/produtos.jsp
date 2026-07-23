<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Produtos</title>
</head>
<body>

<h1>Cadastro de Produtos</h1>
<!-- ENVIA DADOS PRO PRODUTOSERVLET -->
<form action="ProdutoServlet" method="post">

Descrição:<br>
<input type="text" name="descricao">

<br><br>

Preço:<br>
<input type="number" step="0.01" name="preco">

<br><br>

Estoque:<br>
<input type="number" name="estoque">

<br><br>

<input type="submit" value="Cadastrar Produto">

</form>

<br>

<a href="index.jsp">Voltar</a>

</body>
</html>