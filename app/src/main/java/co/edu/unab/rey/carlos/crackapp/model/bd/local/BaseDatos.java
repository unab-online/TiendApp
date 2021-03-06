package co.edu.unab.rey.carlos.crackapp.model.bd.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import co.edu.unab.rey.carlos.crackapp.model.entity.Producto;

@Database(entities = {Producto.class},version = 2, exportSchema = false)
public abstract class BaseDatos extends RoomDatabase {

    public abstract ProductoDAO productoDAO();
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
