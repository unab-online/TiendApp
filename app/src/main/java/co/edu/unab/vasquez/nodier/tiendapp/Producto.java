package co.edu.unab.vasquez.nodier.tiendapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

//cambiar nombre de la tabla
@Entity (tableName = "Productos")
public class Producto implements Serializable {

    @PrimaryKey
    @NonNull
    private String id;
    //si quiero cambiar el nombre así:
    //@ColumnInfo(name = "prod_nombre")
    private String nombre;
    private String descripcion;
    private Double precio;

    public Producto() {
    }

    @Ignore
    public Producto(String nombre, Double precio) {
        this.nombre = nombre;
        this.descripcion = "";
        this.precio = precio;
        this.id ="";

    }

    public Producto(String nombre, String descripcion, Double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return nombre +" ("+precio+")";
    } // Se modifica el método toString para mostrar de la manera que queremos nuestra lista
}
