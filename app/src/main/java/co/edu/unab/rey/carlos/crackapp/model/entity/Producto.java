package co.edu.unab.rey.carlos.crackapp.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity (tableName = "productos")
public class Producto implements Serializable {

    @PrimaryKey
    @NonNull
    public String id;
    @ColumnInfo (name = "nombre")
    @SerializedName("name")
    private String nombre;
    private String descripcion;
    private Double precio;

    @Ignore
    public Producto(String nombre, double precio, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.id = "";
    }

    public Producto() {
    }

    @Override
    public String toString() {
        return nombre+"  "+ precio;
    }

    @Ignore
    public void setId(String id) {
        this.id = id;
    }

    @NonNull
    public String getId() {
        return id;
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


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }


}
