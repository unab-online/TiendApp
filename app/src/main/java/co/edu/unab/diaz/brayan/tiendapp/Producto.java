package co.edu.unab.diaz.brayan.tiendapp;

import java.io.Serializable;

public class Producto implements Serializable {

    private String nombre,descripcion;
    private double precio;

    Producto(String nombre,double precio){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public String toString(){
        return nombre + "(" + precio +")";
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
