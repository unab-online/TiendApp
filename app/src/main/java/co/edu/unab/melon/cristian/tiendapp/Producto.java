package co.edu.unab.melon.cristian.tiendapp;

public class Producto {

    private String nombre;

    public Producto(String nombre, Double precio) {
        this.nombre = nombre;
        this.descripción= "";
        this.precio = precio;
    }

    private String descripción;
    private Double precio;


    public String getNombre() {
        return nombre;
    }


    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public Double getPrecio() {
        return precio;
    }


    @Override
    public String toString() {
        return nombre + "   " + precio;
    }
}
