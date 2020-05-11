package co.edu.unab.toloza.cesar.tiendapp.model.repository;

import android.content.Context;
import android.net.DnsResolver;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import co.edu.unab.toloza.cesar.tiendapp.model.db.local.BaseDatos;
import co.edu.unab.toloza.cesar.tiendapp.model.db.local.ProductoDAO;
import co.edu.unab.toloza.cesar.tiendapp.model.db.network.ProductoAPI;
import co.edu.unab.toloza.cesar.tiendapp.model.db.network.TiendAppService;
import co.edu.unab.toloza.cesar.tiendapp.model.entity.Producto;
import co.edu.unab.toloza.cesar.tiendapp.model.db.network.CallBackFirestore;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductoRepository {

    private BaseDatos roomDB;
    private FirebaseFirestore firestoreDB;
    private Retrofit myRetrofit;
    private ProductoAPI productoAPI;

    public ProductoRepository(Context context) {
        roomDB = BaseDatos.obtenerInstancia(context);
        firestoreDB = FirebaseFirestore.getInstance();
        myRetrofit = TiendAppService.obtenerInstancia();
        productoAPI = myRetrofit.create(ProductoAPI.class);
    }

    public List<Producto> obtenerTodoRoom(){
        ProductoDAO productoDAO = roomDB.productoDAO();
        return productoDAO.obtenerTodos();
    }

    public void obtenerTodosFirestore(final CallBackFirestore<List<Producto>> callBackFirestore){
        firestoreDB.collection("productos").get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                List<Producto> productos = new ArrayList<>();
                if(task.getResult() != null){
                    for (QueryDocumentSnapshot document: task.getResult()) {
                        Producto producto = document.toObject(Producto.class);
                        producto.setId(document.getId());
                        productos.add(producto);
                    }
                }
                callBackFirestore.correcto(productos);
            }
        });
    }

    public void escucharTodosFirestore(final CallBackFirestore<List<Producto>> callBackFirestore){
        firestoreDB.collection("productos").addSnapshotListener((queryDocumentSnapshots, e) -> {
            List<Producto> productos = new ArrayList<>();
            if(queryDocumentSnapshots != null){
                for (QueryDocumentSnapshot document: queryDocumentSnapshots){
                    Producto producto = document.toObject(Producto.class);
                    producto.setId(document.getId());
                    productos.add(producto);
                }
            }
            callBackFirestore.correcto(productos);
        });
    }

    public void editarFirestore(final Producto producto, final CallBackFirestore<Producto> callBackFirestore){
        firestoreDB.collection("productos").document(producto.getId()).set(producto).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                callBackFirestore.correcto(producto);
            }
        });
    }

    public void eliminarFirestore(final Producto producto, final CallBackFirestore<Producto> callBackFirestore){
        firestoreDB.collection("productos").document(producto.getId()).delete().addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                callBackFirestore.correcto(producto);
            }
        });
    }

    public void agregarFirestore(final Producto producto, final CallBackFirestore<Producto> callBackFirestore){
        firestoreDB.collection("productos").add(producto).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                callBackFirestore.correcto(producto);
            }
        });
    }

    public void obtenerTodosProductoAPI(final CallBackFirestore<List<Producto>> callBackFirestore){
        productoAPI.obtenerTodos().enqueue(new Callback<Map<Object, Object>>() {
            @Override
            public void onResponse(Call<Map<Object, Object>> call, Response<Map<Object, Object>> response) {
                List<Producto> productos = new ArrayList<>();
                if(response.body() != null){
                    for (Map.Entry datos: response.body().entrySet()){
                        String id = (String) datos.getKey();
                        Map map = (Map) datos.getValue();
                        Producto producto = new Producto();
                        producto.setNombre((String) map.get("nombre"));
                        producto.setPrecio((Double) map.get("precio"));
                        producto.setDescripcion((String) map.get("descripcion"));
                        producto.setId(id);
                        productos.add(producto);
                    }
                    Log.d("API", response.body().toString());
                }
                callBackFirestore.correcto(productos);
            }

            @Override
            public void onFailure(Call<Map<Object, Object>> call, Throwable t) {
                Log.d("API", "Get error: " + t.getMessage());
            }
        });
    }

    public void editarProductoAPI(final Producto producto, final CallBackFirestore<Map> callBackFirestore){
        productoAPI.editar(producto.getId(), producto).enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Call<Map> call, Response<Map> response) {
                if(response.body() != null){
                    Log.d("API", "editar: " + response.body().toString());
                }
                callBackFirestore.correcto(response.body());
            }

            @Override
            public void onFailure(Call<Map> call, Throwable t) {
                Log.d("API", "error editar: " + t.getMessage());
            }
        });
    }

    public void eliminarProductoAPI(final Producto producto, final CallBackFirestore<Map> callBackFirestore){
        productoAPI.eliminar(producto.getId()).enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Call<Map> call, Response<Map> response) {
                if(response.body() != null){
                    Log.d("API", "eliminar: " + response.body().toString());
                }
                callBackFirestore.correcto(response.body());
            }

            @Override
            public void onFailure(Call<Map> call, Throwable t) {
                Log.d("API", "error eliminar: " + t.getMessage());
            }
        });
    }

    public void agregarProductoAPI(final Producto producto, final CallBackFirestore<Map> callBackFirestore){
        productoAPI.agregar(producto).enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Call<Map> call, Response<Map> response) {
                if(response.body() != null){
                    Log.d("API", "agregar: " + response.body().toString());
                }
                callBackFirestore.correcto(response.body());
            }

            @Override
            public void onFailure(Call<Map> call, Throwable t) {
                Log.d("API", "error agregar: " + t.getMessage());
            }
        });
    }
}
