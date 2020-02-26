package co.edu.unab.tiendapp;

public class Producto {

    private String nombre;
    private String descripcion;
    private double precio;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.descripcion = "";
        this.precio = precio;
    }

    @Override
    public String toString() {
        return nombre + "(" + precio +")";
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
}
