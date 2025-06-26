/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package cr.ac.ucr.paraiso.progra2.practicaexamen2.servlets;

import cr.ac.ucr.paraiso.progra2.practicaexamen2.data.TemaXmlDAO;
import cr.ac.ucr.paraiso.progra2.practicaexamen2.domain.Tema;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import org.jdom2.JDOMException;

/**
 *
 * @author Lexis
 */
public class TemasPorAreaServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String codigoParam = request.getParameter("codigo");
    System.out.println("codigoParam recibido: " + codigoParam);

    if (codigoParam == null || codigoParam.isBlank()) {
        response.sendRedirect("MostrarAreas");
        return;
    }

    int codigoArea;
    try {
        codigoArea = Integer.parseInt(codigoParam);
    } catch (NumberFormatException e) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El código debe ser un número válido.");
        return;
    }

    try {
        TemaXmlDAO data = TemaXmlDAO.abrirDocumento(getServletContext().getRealPath("/WEB-INF/data/temas.xml"));
        List<Tema> temas = data.getTemasPorArea(codigoArea);
        request.setAttribute("temas", temas);
        request.getRequestDispatcher("temas_por_area.jsp").forward(request, response);
    } catch (JDOMException e) {
        throw new ServletException("Error mostrando temas", e);
    }
}
}
