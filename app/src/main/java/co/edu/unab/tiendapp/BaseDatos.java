package co.edu.unab.tiendapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;
// numero de tablas que tengo
@Database(entities = {Producto.class}, version = 1, exportSchema = false)
public abstract class BaseDatos extends RoomDatabase {
    //por cada dao debo hacer lo siguiente
    abstract  ProductoDAO productoDAO();
    private static BaseDatos instancia;
    public static BaseDatos obtenerInstancia(Context contexto){
        if(instancia==null){
            instancia= Room.databaseBuilder(contexto.getApplicationContext(), BaseDatos.class, "tiendapp.db").allowMainThreadQueries().build();
        }
        return instancia;
    };
}
