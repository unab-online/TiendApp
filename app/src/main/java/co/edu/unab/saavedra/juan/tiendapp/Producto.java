package co.edu.unab.saavedra.juan.tiendapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import javax.annotation.Nonnull;

@Entity(tableName = "productos")
public class Producto implements Serializable {

    @PrimaryKey
    @NonNull
    private String id;

    @ColumnInfo(name = "nombre")
    private String nombre;
    private String descripcion;
    private double precio;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = "";
        this.id="";
    }

    public Producto(){

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        if(this.descripcion.equals("")){
            return  nombre + "\n" +
                    precio+"$";
        }else{
            return  nombre + "\n" +
                    descripcion + "\n" +
                    precio+"$";
        }

    }
}
