package cr.ac.ucr.paraiso.progra2.practicaexamen2.domain;

public class AreaConocimiento {
    private int codigo;
    private String nombre;

    public AreaConocimiento() {
    }

    public AreaConocimiento(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "AreaConocimiento{" + "codigo=" + codigo + ", nombre=" + nombre + '}';
    }
    
    
}
