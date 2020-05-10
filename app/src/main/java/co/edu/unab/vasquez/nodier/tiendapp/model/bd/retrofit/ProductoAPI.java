package co.edu.unab.vasquez.nodier.tiendapp.model.bd.retrofit;

import java.util.Map;

import co.edu.unab.vasquez.nodier.tiendapp.model.entity.Producto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductoAPI {

    @GET("productos.json")
    //opcion 1
    //Call<Map> obtenerTodos();
    //opción 2
    Call<Map<String, Producto>> obtenerTodos();

    @PUT("productos/{id}.json")
    Call<Map> editar(@Path("id") String id, @Body Producto unProducto);

    @DELETE("productos/{id}.json")
    Call<Map> eliminar(@Path("id") String id);

    @POST("productos.json")
    Call<Map> agregar( @Body Producto unProducto);

}
