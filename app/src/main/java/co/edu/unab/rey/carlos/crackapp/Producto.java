package co.edu.unab.rey.carlos.crackapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity (tableName = "productos")
public class Producto implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo (name = "nombre")
    private String nombre;
    private String descripcion;
    private Double precio;

    public Producto(String nombre, double precio, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return nombre+"  "+ precio;
    }

    @Ignore
    public void setId(int id) {
        this.id = id;
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

    public Double getPrecio() {
        return precio;
    }


}
