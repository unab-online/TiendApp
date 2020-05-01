package co.edu.unab.leal.jakson.silviapp.model.entity;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity (tableName = "Productos")
public class Producto implements Serializable {

    @PrimaryKey
    @NonNull
    private String id;

    private String nombre;
    private String descripcion;
    private double precio;

    public Producto(){

    }
    @Ignore
    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = "";
        this.id = "";
    }

    public Producto(String nombre, double precio, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
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
