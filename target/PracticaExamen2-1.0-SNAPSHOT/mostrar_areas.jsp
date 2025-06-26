<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="cr.ac.ucr.paraiso.progra2.practicaexamen2.domain.AreaConocimiento" %>
<%@ page import="cr.ac.ucr.paraiso.progra2.practicaexamen2.data.AreaConocimientoXmlDAO" %>
<%@ page import="org.jdom2.JDOMException" %>

<%
    List<AreaConocimiento> areas = null;
    try {
        AreaConocimientoXmlDAO data = AreaConocimientoXmlDAO.abrirDocumento(application.getRealPath("/WEB-INF/data/areas.xml"));
        areas = data.getAreas();
    } catch (JDOMException e) {
        out.println("<p><strong>Error cargando áreas:</strong> " + e.getMessage() + "</p>");
    }
%>

<html>
<head>
    <title>Mostrar Áreas</title>
</head>
<body>
<h2>Áreas de Conocimiento</h2>

<%
    if (areas == null || areas.isEmpty()) {
%>
    <p>No hay áreas disponibles.</p>
<%
    } else {
%>
    <table border="1">
        <tr><th>Código</th><th>Nombre</th></tr>
        <%
            for (AreaConocimiento area : areas) {
        %>
        <tr>
            <td><a href="TemasPorArea?codigo=<%= area.getCodigo()%>"><%= area.getCodigo()%></a></td>
            <td><a href="TemasPorArea?codigo=<%= area.getCodigo()%>"><%= area.getNombre()%></a></td>
        </tr>
        <% } %>
    </table>
<%
    }
%>

</body>
</html>
