package co.edu.unab.casadiegos.andres.tiendaapp;

public class Producto {

    private String nombre,descripcion;
    private double precio;

    Producto(String nombre,double precio)
    {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = "";
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

