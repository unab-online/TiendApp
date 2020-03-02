package co.edu.unab.saavedra.juan.tiendapp;

import java.io.Serializable;

public class Producto implements Serializable {

    private String nombre;
    private String descripcion;
    private double precio;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = "";
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

    @Override
    public String toString() {
        if(this.descripcion.equals("")){
            return  nombre + "\n" +
                    precio+"$";
        }else{
            return  nombre + "\n" +
                    descripcion + "\n" +
                    precio+"$";
        }

    }
}