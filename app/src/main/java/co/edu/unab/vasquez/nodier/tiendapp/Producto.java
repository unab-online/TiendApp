package co.edu.unab.vasquez.nodier.tiendapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

//cambiar nombre de la tabla
@Entity (tableName = "Productos")
public class Producto implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    //si quiero cambiar el nombre así:
    //@ColumnInfo(name = "prod_nombre")
    private String nombre;
    private String descripcion;
    private Double precio;

    public Producto(String nombre, Double precio) {
        this.nombre = nombre;
        this.descripcion = "";
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

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return nombre +" ("+precio+")";
    } // Se modifica el método toString para mostrar de la manera que queremos nuestra lista
}
