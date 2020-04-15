package co.edu.unab.diaz.brayan.tiendapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "productos")
public class Producto implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nombre,descripcion;
    private double precio;

    Producto(String nombre,double precio){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
