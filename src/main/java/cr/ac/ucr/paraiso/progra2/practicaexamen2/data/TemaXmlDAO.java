package cr.ac.ucr.paraiso.progra2.practicaexamen2.data;

import cr.ac.ucr.paraiso.progra2.practicaexamen2.domain.Tema;
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

public class TemaXmlDAO {
    private Document document;
    private Element raiz;
    private File archivo;

    public TemaXmlDAO(File archivo, String nombreRaiz) throws IOException {
        this.archivo = archivo;
        this.raiz = new Element(nombreRaiz);
        this.document = new Document(raiz);
        guardar();
    }

    public TemaXmlDAO(String rutaDocumento) throws IOException, JDOMException {
        SAXBuilder builder = new SAXBuilder();
        builder.setIgnoringElementContentWhitespace(true);
        this.document = builder.build(rutaDocumento);
        this.archivo = new File(rutaDocumento);
        this.raiz = document.getRootElement();
    }

    public static TemaXmlDAO abrirDocumento(String rutaDocumento) throws IOException, JDOMException {
        File archivo = new File(rutaDocumento);
        if (archivo.exists()) {
            return new TemaXmlDAO(rutaDocumento);
        } else {
            return new TemaXmlDAO(archivo, "temas");
        }
    }

    public void guardar() throws IOException {
        Format format = Format.getPrettyFormat();
        format.setEncoding("UTF-8");
        XMLOutputter out = new XMLOutputter(format);
        try (PrintWriter writer = new PrintWriter(archivo)) {
            out.output(document, writer);
        }
    }

    public void insertarTema(Tema tema) throws IOException {
        if (findTemaByCodigo(tema.getCodigo()).isPresent()) {
            return;
        }

        Element eTema = new Element("tema");
        eTema.addContent(new Element("codigo").setText(String.valueOf(tema.getCodigo())));
        eTema.addContent(new Element("nombre").setText(tema.getNombre()));
        eTema.addContent(new Element("codArea").setText(String.valueOf(tema.getCodArea())));

        raiz.addContent(eTema);
        guardar();
    }

    public Optional<Tema> findTemaByCodigo(int codigo) {
        for (Element e : raiz.getChildren("tema")) {
            if (Integer.parseInt(e.getChildText("codigo")) == codigo) {
                String nombre = e.getChildText("nombre");
                int codArea = Integer.parseInt(e.getChildText("codArea"));
                return Optional.of(new Tema(codigo, nombre, codArea));
            }
        }
        return Optional.empty();
    }

    public List<Tema> getTemasPorArea(int codArea) {
        List<Tema> lista = new ArrayList<>();
        for (Element e : raiz.getChildren("tema")) {
            int codigo = Integer.parseInt(e.getChildText("codigo"));
            String nombre = e.getChildText("nombre");
            int area = Integer.parseInt(e.getChildText("codArea"));
            if (area == codArea) {
                lista.add(new Tema(codigo, nombre, area));
            }
        }
        return lista;
    }

    public List<Tema> getTemas() {
        List<Tema> lista = new ArrayList<>();
        for (Element e : raiz.getChildren("tema")) {
            int codigo = Integer.parseInt(e.getChildText("codigo"));
            String nombre = e.getChildText("nombre");
            int codArea = Integer.parseInt(e.getChildText("codArea"));
            lista.add(new Tema(codigo, nombre, codArea));
        }
        return lista;
    }
    
    public boolean editarTema(Tema nuevoTema) throws IOException {
    for (Element e : raiz.getChildren("tema")) {
        if (Integer.parseInt(e.getChildText("codigo")) == nuevoTema.getCodigo()) {
            e.getChild("nombre").setText(nuevoTema.getNombre());
            e.getChild("codArea").setText(String.valueOf(nuevoTema.getCodArea()));
            guardar();
            return true;
        }
    }
    return false;
}
    public boolean eliminarTema(int codigo) throws IOException {
    List<Element> temas = raiz.getChildren("tema");
    for (Element tema : temas) {
        if (Integer.parseInt(tema.getChildText("codigo")) == codigo) {
            raiz.removeContent(tema);
            guardar();
            return true;
        }
    }
    return false;
}
}
