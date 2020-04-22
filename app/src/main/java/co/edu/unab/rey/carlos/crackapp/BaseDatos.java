package co.edu.unab.rey.carlos.crackapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Producto.class},version = 1, exportSchema = false)
public abstract class BaseDatos extends RoomDatabase {

    abstract ProductoDAO productoDAO();
    private static BaseDatos instancia;

    public static BaseDatos obtenerInstancia(Context contexto){
        if (instancia == null){
            instancia = Room.databaseBuilder(
                    contexto.getApplicationContext(), BaseDatos.class,
                    "tiendaApp.db").allowMainThreadQueries().build();
        }
        return instancia;
    }
}
