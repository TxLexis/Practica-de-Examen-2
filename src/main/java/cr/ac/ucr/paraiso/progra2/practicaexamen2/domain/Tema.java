package cr.ac.ucr.paraiso.progra2.practicaexamen2.domain;

public class Tema {
    private int codigo;
    private String nombre;
    private int codArea;

    public Tema() {
    }

    public Tema(int codigo, String nombre, int codArea) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.codArea = codArea;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCodArea() {
        return codArea;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCodArea(int codArea) {
        this.codArea = codArea;
    }

    @Override
    public String toString() {
        return "Tema{" + "codigo=" + codigo + ", nombre=" + nombre + ", codArea=" + codArea + '}';
    }
    
}
