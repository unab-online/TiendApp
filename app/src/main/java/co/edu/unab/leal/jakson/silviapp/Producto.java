package co.edu.unab.leal.jakson.silviapp;

import java.io.Serializable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "Productos")
public class Producto implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nombre;
    private String descripcion;
    private double precio;

    public Producto(String nombre, double precio, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio(){
       return precio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return nombre +" "+descripcion+ " = "+ precio + "usd";
    }
}
