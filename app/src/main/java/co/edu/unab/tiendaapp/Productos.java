package co.edu.unab.tiendaapp;

import java.io.Serializable;

public class Productos implements Serializable {

    private String Nombre;
    private String Descripcion;
    private int Precio;

    public Productos(String nombre, String descripcion, int precio) {
        this.Nombre = nombre;
        this.Precio = precio;
        this.Descripcion = descripcion;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public int getPrecio() {
        return Precio;
    }

    public void setDescripcion(String descripcion) {
        this.Descripcion = descripcion;
    }

    @Override
    public String toString() {
        return  Nombre + "  " +
                 Precio;
    }
}
