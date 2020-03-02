package com.unab.edu.co.tiendaapp;

public class Productos {

    private String Nombre;
    private String Descripcion;
    private int Precio;

    public Productos(String nombre, int precio) {
        this.Nombre = nombre;
        this.Precio = precio;
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
