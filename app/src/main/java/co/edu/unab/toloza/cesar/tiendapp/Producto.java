package co.edu.unab.toloza.cesar.tiendapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "productos")
public class Producto implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "nombre")
    private String nombre;
    private String descripcion;
    private Double precio;

    @Ignore
    public Producto(String nombre, Double precio) {
        this.nombre = nombre;
        this.descripcion = "";
        this.precio = precio;
    }

    public Producto(String nombre, String descripcion, Double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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
