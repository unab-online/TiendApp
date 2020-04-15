package co.edu.unab.diaz.brayan.tiendapp;

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
    void  actualizar(Producto miProducto);

    @Delete
    void borrar(Producto miProducto);

    @Query("DELETE FROM productos")
    void borrarTodo();

    @Query("SELECT * FROM productos WHERE id=:idProducto")
    Producto obtenerPorId(int idProducto);

}
