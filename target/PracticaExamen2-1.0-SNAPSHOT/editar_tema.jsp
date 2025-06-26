<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="cr.ac.ucr.paraiso.progra2.practicaexamen2.domain.Tema" %>
<%
    Tema tema = (Tema) request.getAttribute("tema");
%>

<html>
<head>
    <title>Editar Tema</title>
</head>
<body>
<h2>Editar Tema</h2>
<form action="EditarTema" method="post">
    Código: <input type="text" name="codigo" value="<%= tema.getCodigo() %>" readonly><br>
    Nombre: <input type="text" name="nombre" value="<%= tema.getNombre() %>"><br>
    Código de Área: <input type="number" name="codArea" value="<%= tema.getCodArea() %>"><br>
    <input type="submit" value="Guardar Cambios">
</form>
</body>
</html>
