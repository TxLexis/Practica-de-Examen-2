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
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import org.jdom2.JDOMException;

public class InsertarTemaServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AreaConocimientoXmlDAO data = AreaConocimientoXmlDAO.abrirDocumento(getServletContext().getRealPath("/WEB-INF/data/areas.xml"));
            List<AreaConocimiento> areas = data.getAreas();
            request.setAttribute("areas", areas);
            request.getRequestDispatcher("insertar_tema.jsp").forward(request, response);
        } catch (JDOMException e) {
            throw new ServletException("Error cargando áreas", e);
        }
    }
}
