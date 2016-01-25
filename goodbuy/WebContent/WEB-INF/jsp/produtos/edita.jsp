<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edita</title>
</head>
<body>
   <!--  <form action="altera"> -->
   <form action="<c:url value="/produtos/${produto.id}"/>" method="POST">
      <fieldset>
         <legend>Editar Produtos</legend>
         <input type="hidden" name="produto.id"
           value="${produto.id}"/>
           
         <label for="nome">Nome:</label>
         <input id="nome" type="text" name="produto.nome"
         value="${produto.nome}"/> 
         
         <label for="nome">Descrição:</label>
         <textarea id="descricao" name="produto.descricao">
          ${produto.descricao}
         </textarea>
        
        <label for="preco">Preço:</label>
    <input id="preco" type="text" name="produto.preco" 
      value="${produto.preco }"/>    
    
    <button type="submit" name="_method" value="PUT">Enviar</button>
  </fieldset>
</form> 
<form action=
"<c:url value="/produtos/${produto.id}/imagem"/>"
  method="POST" enctype="multipart/form-data">
<fieldset>
  <legend>Upload de Imagem</legend>
  <img src="<c:url value="/produtos/${produto.id}/imagem"/>"
       width="100" height="100"/>
  <input type="file" name="imagem"/>
  <button type="submit">Enviar</button>
</fieldset>
</form>
</body>
</html>