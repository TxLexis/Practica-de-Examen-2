package cr.ac.ucr.paraiso.progra2.practicaexamen2.data;

import cr.ac.ucr.paraiso.progra2.practicaexamen2.domain.AreaConocimiento;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class AreaConocimientoXmlDAO {
    private Document document;
    private Element raiz;
    private File archivo;

    public AreaConocimientoXmlDAO(File archivo, String nombreRaiz) throws IOException {
        this.archivo = archivo;
        this.raiz = new Element(nombreRaiz);
        this.document = new Document(raiz);
        guardar();
    }

    public AreaConocimientoXmlDAO(String rutaDocumento) throws IOException, JDOMException {
        SAXBuilder builder = new SAXBuilder();
        builder.setIgnoringElementContentWhitespace(true);
        this.document = builder.build(rutaDocumento);
        this.archivo = new File(rutaDocumento);
        this.raiz = document.getRootElement();
    }

    public static AreaConocimientoXmlDAO abrirDocumento(String rutaDocumento) throws IOException, JDOMException {
        File archivo = new File(rutaDocumento);
        if (archivo.exists()) {
            return new AreaConocimientoXmlDAO(rutaDocumento);
        } else {
            return new AreaConocimientoXmlDAO(archivo, "areas");
        }
    }

    public void guardar() throws IOException {
        Format format = Format.getPrettyFormat();
        format.setEncoding("UTF-8");
        XMLOutputter xmlOutputter = new XMLOutputter(format);
        try(PrintWriter printWriter = new PrintWriter(this.archivo)) {
            xmlOutputter.output(this.document, printWriter);
        }
    }

    public List<AreaConocimiento> getAreas() {
        List<AreaConocimiento> lista = new ArrayList<>();
        for (Element e : raiz.getChildren("area")) {
            int codigo = Integer.parseInt(e.getChildText("codigo"));
            String nombre = e.getChildText("nombre");
            lista.add(new AreaConocimiento(codigo, nombre));
        }
        return lista;
    }

    public Optional<AreaConocimiento> findByCodigo(int codigo) {
        for (Element e : raiz.getChildren("area")) {
            if (Integer.parseInt(e.getChildText("codigo")) == codigo) {
                String nombre = e.getChildText("nombre");
                return Optional.of(new AreaConocimiento(codigo, nombre));
            }
        }
        return Optional.empty();
    }
}
