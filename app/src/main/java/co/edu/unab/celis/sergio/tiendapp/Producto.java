package co.edu.unab.celis.sergio.tiendapp;

import java.io.Serializable;

public class Producto implements Serializable {

    private String nombre;
    private String descripción;
    private double precio;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.descripción = "";
        this.precio = precio;
    }

    @Override
    public String toString() {
        return  nombre + " (" + precio + ')';
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public double getPrecio() {
        return precio;
    }
}
