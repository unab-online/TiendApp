package co.edu.unab.martinez.andrea.tiendapp;

import java.io.Serializable;

public class Producto implements Serializable {

    private String nombre;
    private String descripcion;
    private double precio;

    public Producto(String nombre,double precio, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion =descripcion;
    }

    @Override
    public String toString() {
        return nombre + "(" + precio + ")";
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }


}
