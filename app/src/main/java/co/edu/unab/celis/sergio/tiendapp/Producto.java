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

    public Producto(String nombre, String descripción, double precio) {
        this.nombre = nombre;
        this.descripción = descripción;
        this.precio = precio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return  nombre  + " (" + precio + ')';
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripción() {
        return descripción;
    }

    public double getPrecio() {
        return precio;
    }
}
