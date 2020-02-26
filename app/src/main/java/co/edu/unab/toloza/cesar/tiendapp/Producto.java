package co.edu.unab.toloza.cesar.tiendapp;

public class Producto {
    private String nombre;
    private String descripcion;
    private Double precio;

    public Producto(String nombre, Double precio) {
        this.nombre = nombre;
        this.descripcion = "";
        this.precio = precio;
    }

    public String toString(){
        return nombre + " (" + precio + ")";
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

    public Double getPrecio() {
        return precio;
    }
}
