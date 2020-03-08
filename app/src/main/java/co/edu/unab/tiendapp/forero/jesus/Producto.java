package co.edu.unab.tiendapp.forero.jesus;

import java.io.Serializable;

public class Producto implements Serializable {
    private String nombre;
    private String descripcion;
    private double precio;

    public Producto(String nombre, Double precio) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion="";
    }

    @Override
    public String toString() {
        return nombre+" ( "+precio+" )";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
