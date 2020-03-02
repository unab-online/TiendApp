package co.edu.unab.amazonappexample;

import java.io.Serializable;

class Producto implements Serializable {
    private String nombre;
    private String descripcion;
    private double precio;


    public Producto(String nombre , double precio) {
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

    public double getPrecio() {
        return precio;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                '}';
    }
}
