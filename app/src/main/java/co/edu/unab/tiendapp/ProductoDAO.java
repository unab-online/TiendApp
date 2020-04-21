package co.edu.unab.tiendapp;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ProductoDAO {
    //Interfaz para definir los metodos que usaré en la base de datos
    @Insert
     void agregar(Producto miProducto);

     @Query("SELECT * FROM productos")
    List<Producto> obtenerTodos(); //devolverá una lista de tipo Producto

    @Update
    void actualizar(Producto miProducto);

    @Delete
    void eliminar(Producto miProducto);

    @Query("DELETE from productos")
    void borrarTodo();

    @Query("SELECT * FROM productos WHERE id=:idProducto")
   Producto obtenerPorId(int idProducto);
}
