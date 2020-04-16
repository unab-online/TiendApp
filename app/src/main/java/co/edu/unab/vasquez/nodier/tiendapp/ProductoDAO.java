package co.edu.unab.vasquez.nodier.tiendapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
//Aquí definimos todas las consultas que queremos hacer
@Dao
public interface ProductoDAO {
    @Insert
    void agregar(Producto miProducto);

    @Query("SELECT * FROM Productos")
    List<Producto> obtenerTodos();

    @Update
    void actualizar(Producto miProducto);

    @Delete
    void eliminar(Producto miProducto);

    @Query("DELETE FROM productos")
    void borrarTabla();

    @Query("SELECT * FROM Productos WHERE id=:idProducto")
    Producto obtenerPorId(int idProducto);
}
