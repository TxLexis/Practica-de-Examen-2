/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package cr.ac.ucr.paraiso.progra2.practicaexamen2.servlets;

import cr.ac.ucr.paraiso.progra2.practicaexamen2.data.TemaXmlDAO;
import cr.ac.ucr.paraiso.progra2.practicaexamen2.domain.Tema;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.jdom2.JDOMException;

public class SalvarTemaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "GET no permitido en este recurso.");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        String nombre = request.getParameter("nombre");
        int codArea = Integer.parseInt(request.getParameter("codArea"));

        Tema tema = new Tema(codigo, nombre, codArea);

        try {
            TemaXmlDAO data = TemaXmlDAO.abrirDocumento(getServletContext().getRealPath("/WEB-INF/data/temas.xml"));
            if (data.findTemaByCodigo(codigo).isPresent()) {
                response.getWriter().println("Error: El tema ya existe.");
            } else {
                data.insertarTema(tema);
                response.getWriter().println("Datos guardados.");
            }
        } catch (JDOMException e) {
            throw new ServletException("Error guardando tema", e);
        }
    }
    
}
