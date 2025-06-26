<%@page import="cr.ac.ucr.paraiso.progra2.practicaexamen2.domain.AreaConocimiento"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>

<%
    List<AreaConocimiento> areas = (List<AreaConocimiento>) request.getAttribute("areas");
%>

<html>
<head>
    <title>Insertar Tema</title>
</head>
<body>
<h2>Insertar Tema</h2>
<form action="SalvarTema" method="post">
    Código: <input type="text" name="codigo" required><br>
    Nombre: <input type="text" name="nombre" required><br>
    Área:
    <select name="codArea">
        <%
            for (AreaConocimiento area : areas) {
        %>
        <option value="<%= area.getCodigo() %>">
            <%= area.getCodigo() %> - <%= area.getNombre() %>
        </option>
        <%
            }
        %>
    </select><br><br>
    <input type="submit" value="Guardar Tema">
</form>
</body>
</html>
