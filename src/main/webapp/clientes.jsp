<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Clientes</title>
</head>
<body>
 <form action="ClienteServlet">
  
  Nome:<br>
   <input type="text" name="nome">
  
  <br><br>
  
  Email: <br>
  <input type="text" name="email">
  
  <br><br>
  
  Cpf: <br>
  <input type="number" name="cpf">
  
  <input type="submit" value="cadastrar cliente">
 
  </form>
 
 <a href="index.jsp">Voltar</a>
 
</body>
</html>