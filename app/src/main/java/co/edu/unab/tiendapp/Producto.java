package co.edu.unab.tiendapp;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "productos")


public class Producto implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "prod_nombre")
    private String nombre; //colomInfo es para cambiar el nombre de la columna
    private String descripcion;
    private double precio;


    public Producto(String nombre , double precio) {
        this.nombre = nombre;
        this.descripcion = "Sin descripción";
        this.precio = precio;
    }



    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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

    @Override
    public String toString() {
        return  nombre +
                 precio ;

    }



}
