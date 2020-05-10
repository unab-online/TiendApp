package co.edu.unab.rey.carlos.crackapp.model.bd.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import co.edu.unab.rey.carlos.crackapp.model.entity.Producto;

@Dao
public interface ProductoDAO {

    @Insert
    void agregar(Producto miProducto);

    @Query("SELECT * FROM productos")
    List<Producto> obtenerTodos();

    @Update
    void actualizar(Producto miProducto);

    @Delete
    void borrar(Producto miProducto);

    @Query("DELETE FROM productos")
    void borrarTodo();

    @Query("SELECT * FROM productos WHERE id=:idProducto")
    Producto obtenerId(int idProducto);
}
