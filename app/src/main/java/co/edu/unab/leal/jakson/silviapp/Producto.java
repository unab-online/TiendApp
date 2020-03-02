package co.edu.unab.leal.jakson.silviapp;

import java.io.Serializable;

public class Producto implements Serializable {

    private String nombre;
    private String descripcion;
    private double precio;

    public Producto(String nombre, double precio, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio(){
       return precio;
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

    @Override
    public String toString() {
        return nombre + " = "+ precio + "usd";
    }
}
