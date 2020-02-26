package co.edu.unab.patino.omar.tiendapp;

public class Producto {

    private String nombre;
    private String descripcion;
    private double precio;

    public Producto(String nombre, double precio){
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = ""; //comillas para que se pueda cambiar la descripcion
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

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return nombre + " = " + precio + "usd";
    }
}
