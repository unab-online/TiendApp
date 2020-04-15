package co.edu.unab.saavedra.juan.tiendapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductoDAO {

    @Insert
    void agregar(Producto miProducto);

    @Query("SELECT * FROM productos")
    List<Producto> obtenerTodos();

    @Update
    void actualizar(Producto miProducto);

    @Delete
    void eliminar(Producto miProducto);

    @Query("DELETE FROM productos")
    void eliminarTodo();

    @Query("SELECT * FROM productos WHERE id = :idProducto")
    Producto consultarPorId(int idProducto);

}
