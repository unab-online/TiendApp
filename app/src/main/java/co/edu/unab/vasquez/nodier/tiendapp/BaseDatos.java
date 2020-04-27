package co.edu.unab.vasquez.nodier.tiendapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
//tantas entidades como clases de entidades.
@Database(entities = {Producto.class},version = 2,exportSchema = false)
public abstract class BaseDatos extends RoomDatabase {

    //******************************************************************************//
    // Código para base de datos de room
    abstract ProductoDAO productoDAO();
    private static BaseDatos instancia;

    public static BaseDatos obtenerInstancia(Context contexto){
        if (instancia==null){
            instancia = Room.databaseBuilder(
                    contexto.getApplicationContext(),BaseDatos.class,
                    "tiendapp.db").allowMainThreadQueries().build();
        }
        return instancia;
    }
    //******************************************************************************//

}
