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
import java.util.Optional;
import org.jdom2.JDOMException;

/**
 *
 * @author Lexis
 */
public class EditarTemaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int codigo = Integer.parseInt(req.getParameter("codigo"));
        try {
            TemaXmlDAO data = TemaXmlDAO.abrirDocumento(getServletContext().getRealPath("/temas.xml"));
            Optional<Tema> temaOpt = data.findTemaByCodigo(codigo);
            if (temaOpt.isPresent()) {
                req.setAttribute("tema", temaOpt.get());
                req.getRequestDispatcher("editar_tema.jsp").forward(req, resp);
            } else {
                resp.getWriter().println("Tema no encontrado.");
            }
        } catch (JDOMException e) {
            throw new ServletException("Error accediendo a datos XML", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int codigo = Integer.parseInt(req.getParameter("codigo"));
        String nombre = req.getParameter("nombre");
        int codArea = Integer.parseInt(req.getParameter("codArea"));

        Tema nuevoTema = new Tema(codigo, nombre, codArea);

        try {
            TemaXmlDAO data = TemaXmlDAO.abrirDocumento(getServletContext().getRealPath("/WEB-INF/data/temas.xml"));
            if (data.editarTema(nuevoTema)) {
                resp.getWriter().println("Tema actualizado correctamente.");
            } else {
                resp.getWriter().println("Tema no encontrado.");
            }
        } catch (JDOMException e) {
            throw new ServletException("Error accediendo a XML", e);
        }
    }
}
