<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="cr.ac.ucr.paraiso.progra2.practicaexamen2.domain.Tema" %>
<%@ page import="java.util.List" %>

<%
    List<Tema> temas = (List<Tema>) request.getAttribute("temas");
%>

<html>
<head>
    <title>Temas por Área</title>
</head>
<body>
<h2>Temas por Área</h2>

<% if (temas == null || temas.isEmpty()) { %>
    <p>No se encontraron temas para esta área.</p>
<% } else { %>
    <table border="1">
        <tr><th>Código</th><th>Nombre</th><th>Acción</th></tr>
        <%
            for (Tema t : temas) {
        %>
        <tr>
            <td><%= t.getCodigo() %></td>
            <td><%= t.getNombre() %></td>
            <td>
                <form action="EditarTema" method="get" style="display:inline">
                    <input type="hidden" name="codigo" value="<%= t.getCodigo() %>">
                    <input type="submit" value="Editar">
                </form>
                <form action="EliminarTema" method="post" style="display:inline">
                    <input type="hidden" name="codigo" value="<%= t.getCodigo() %>">
                    <input type="submit" value="Eliminar" onclick="return confirm('¿Está seguro de eliminar este tema?');">
                </form>
            </td>
        </tr>
        <% } %>
    </table>
<% } %>

<a href="MostrarAreas">← Volver a áreas</a>
</body>
</html>
