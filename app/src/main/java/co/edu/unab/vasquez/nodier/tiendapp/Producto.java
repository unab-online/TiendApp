package co.edu.unab.vasquez.nodier.tiendapp;

import androidx.annotation.NonNull;

public class Producto {

    private String nombre;
    private String descripcion;
    private Double precio;

    public Producto(String nombre, Double precio) {
        this.nombre = nombre;
        this.descripcion = "";
        this.precio = precio;

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

    public Double getPrecio() {
        return precio;
    }


    @Override
    public String toString() {
        return nombre +"("+precio+")";
    }
}
