package co.edu.unab.toloza.cesar.tiendapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Map;

import co.edu.unab.toloza.cesar.tiendapp.model.db.network.CallBackFirestore;
import co.edu.unab.toloza.cesar.tiendapp.model.db.network.ProductoAPI;
import co.edu.unab.toloza.cesar.tiendapp.model.db.network.TiendAppService;
import co.edu.unab.toloza.cesar.tiendapp.model.entity.Producto;
import co.edu.unab.toloza.cesar.tiendapp.model.repository.ProductoRepository;
import co.edu.unab.toloza.cesar.tiendapp.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AgregarActivity extends AppCompatActivity {

    private EditText nombreProducto, precioProducto, descripcionProducto;
    private Button btnAgregar;
    private ProductoRepository productoRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        asociarElementos();
        productoRepository = new ProductoRepository(getApplicationContext());

        btnAgregar.setOnClickListener(v -> {
            String nombre = nombreProducto.getText().toString();
            String descripcion = descripcionProducto.getText().toString();
            double precio = Double.valueOf(precioProducto.getText().toString());
            //productoDAO.agregar(new Producto(nombre, descripcion, precio));

            Producto nuevoProducto = new Producto(nombre, descripcion, precio);
            //productoRepository.agregarFirestore(nuevoProducto, respuesta -> finish());
            Retrofit retrofit = TiendAppService.obtenerInstancia();
            ProductoAPI productoAPI = retrofit.create(ProductoAPI.class);
            productoAPI.agregar(nuevoProducto).enqueue(new Callback<Map>() {
                @Override
                public void onResponse(Call<Map> call, Response<Map> response) {
                    if(response.body() != null){
                        Log.d("API", "agregar: " + response.body().toString());
                        String id = (String) response.body().get("name");
                        nuevoProducto.setId(id);
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<Map> call, Throwable t) {
                    Log.d("API", "error agregar: " + t.getMessage());
                }
            });
        });
    }

    private void asociarElementos(){
        nombreProducto = findViewById(R.id.editText_editar_nombre);
        precioProducto = findViewById(R.id.editText_editar_precio);
        descripcionProducto = findViewById(R.id.editText_editar_desc);
        btnAgregar = findViewById(R.id.button_agregar_nuevo);
    }
}
