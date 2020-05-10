package co.edu.unab.rey.carlos.crackapp.model.bd.network;

import java.util.Map;

import co.edu.unab.rey.carlos.crackapp.model.entity.Producto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductoAPI {
    @GET("productos.json")
    Call<Map <Object, Object>> obtenerTodos();
//metodo para editar en retorfit
    @PUT("productos/{id}.json")
    Call<Map> editar(@Path("id") String id, @Body Producto unProducto);

    //metodo para borrar retrofit
    @DELETE("prodcuto/{id}.json")
    Call<Map> eliminar(@Path("id")String id);

    @POST("producto.json")
    Call<Map> agregar(@Body Producto unProducto);

}
