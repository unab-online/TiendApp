package co.edu.unab.toloza.cesar.tiendapp.model.db.network;

import java.util.Map;

import co.edu.unab.toloza.cesar.tiendapp.model.entity.Producto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductoAPI {

    @GET("productos.json")
    Call<Map<Object, Object>> obtenerTodos();

    @PUT("productos/{id}.json")
    Call<Map> editar(@Path("id") String id, @Body Producto producto);

    @DELETE("productos/{id}.json")
    Call<Map> eliminar(@Path("id") String id);

    @POST("productos.json")
    Call<Map> agregar(@Body Producto producto);
}