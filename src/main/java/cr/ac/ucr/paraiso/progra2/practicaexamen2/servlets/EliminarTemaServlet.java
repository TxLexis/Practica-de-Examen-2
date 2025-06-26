/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package cr.ac.ucr.paraiso.progra2.practicaexamen2.servlets;

import cr.ac.ucr.paraiso.progra2.practicaexamen2.data.TemaXmlDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jdom2.JDOMException;

/**
 *
 * @author Lexis
 */
public class EliminarTemaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int codigo = Integer.parseInt(req.getParameter("codigo"));

        try {
            TemaXmlDAO data = TemaXmlDAO.abrirDocumento(getServletContext().getRealPath("/WEB-INF/data/temas.xml"));
            boolean eliminado = data.eliminarTema(codigo);

            if (eliminado) {
                req.setAttribute("mensaje", "Tema eliminado correctamente.");
            } else {
                req.setAttribute("mensaje", "No se encontró el tema.");
            }

            req.getRequestDispatcher("resultado_guardado.jsp").forward(req, resp);

        } catch (JDOMException e) {
            throw new ServletException("Error accediendo al XML", e);
        }
    }
}
