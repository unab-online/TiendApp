package co.edu.unab.celis.sergio.tiendapp;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ProductoDAO {

    @Insert
    void agregar (Producto miProducto);

    @Query("SELECT * FROM Productos")
    List<Producto> obtenerTodos();

    @Update
    void actualizar(Producto miProducto);

    @Delete
    void eliminar(Producto miProducto);

    @Query("DELETE FROM Productos")
    void eliminarTodo();

    @Query("SELECT * FROM Productos WHERE id=:idProducto")
    Producto obtenerPorId(int idProducto);
}
