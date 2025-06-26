/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package cr.ac.ucr.paraiso.progra2.practicaexamen2.servlets;

import cr.ac.ucr.paraiso.progra2.practicaexamen2.data.AreaConocimientoXmlDAO;
import cr.ac.ucr.paraiso.progra2.practicaexamen2.domain.AreaConocimiento;
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
public class MostrarAreasServlet extends HttpServlet {
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String ruta = getServletContext().getRealPath("/WEB-INF/data/areas.xml");
            AreaConocimientoXmlDAO data = AreaConocimientoXmlDAO.abrirDocumento(ruta);
            List<AreaConocimiento> lista = data.getAreas();

            request.setAttribute("areas", lista);
            request.getRequestDispatcher("mostrar_areas.jsp").forward(request, response);
        } catch (JDOMException e) {
            throw new ServletException("Error cargando XML de áreas", e);
        }
    }
}
