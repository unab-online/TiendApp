package co.edu.unab.celis.sergio.tiendapp;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Productos")
public class Producto implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "nombre")
    private String nombre;
    private String descripción;
    private double precio;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.descripción = "";
        this.precio = precio;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return  nombre + " (" + precio + ')';
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public double getPrecio() {
        return precio;
    }
}
