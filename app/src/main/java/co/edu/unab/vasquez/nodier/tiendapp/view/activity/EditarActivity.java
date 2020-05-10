package co.edu.unab.vasquez.nodier.tiendapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

import co.edu.unab.vasquez.nodier.tiendapp.R;
import co.edu.unab.vasquez.nodier.tiendapp.model.bd.local.BaseDatos;
import co.edu.unab.vasquez.nodier.tiendapp.model.bd.local.ProductoDAO;
import co.edu.unab.vasquez.nodier.tiendapp.model.bd.network.CallBackFirestore;
import co.edu.unab.vasquez.nodier.tiendapp.model.bd.retrofit.ProductoAPI;
import co.edu.unab.vasquez.nodier.tiendapp.model.bd.retrofit.TiendAppService;
import co.edu.unab.vasquez.nodier.tiendapp.model.entity.Producto;
import co.edu.unab.vasquez.nodier.tiendapp.model.repository.ProductoRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EditarActivity extends AppCompatActivity {

    private EditText edtNombre, edtDescripcion, edtPrecio, edtId;
    private TextView txvEditar;
    private Button btnEditar, btnEliminar;
    private ProductoRepository productoRepository;

    ProductoDAO productoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        BaseDatos bd = BaseDatos.obtenerInstancia(this);
        productoDAO = bd.productoDAO();

        asociarElementos();
        final Producto miProducto = (Producto) getIntent().getSerializableExtra("producto");
        txvEditar.setText(getString(R.string.txt_titulo_editar,miProducto.getNombre(),miProducto.getPrecio()));
        edtNombre.setText(miProducto.getNombre());
        edtDescripcion.setText(miProducto.getDescripcion());
        edtPrecio.setText(String.valueOf(miProducto.getPrecio()));

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //miProducto.setId(miProducto.getId());
                miProducto.setNombre(edtNombre.getText().toString());
                miProducto.setDescripcion(edtDescripcion.getText().toString());
                miProducto.setPrecio(Double.parseDouble(edtPrecio.getText().toString()));
//                productoDAO.actualizar(miProducto);
                //productoRepository = new ProductoRepository(EditarActivity.this);
                /*productoRepository.editarFirestore(miProducto, new CallBackFirestore<Producto>() {
                    @Override
                    public void correcto(Producto respuesta) {
                        finish();
                    }
                });*/
                Retrofit retrofit = TiendAppService.obtenerInstancia();
                ProductoAPI productoAPI = retrofit.create(ProductoAPI.class);

                productoAPI.editar(miProducto.getId(), miProducto).enqueue(new Callback<Map>() {
                    @Override
                    public void onResponse(Call<Map> call, Response<Map> response) {
                        if (response.body() != null){
                            Log.d("API",response.body().toString());
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<Map> call, Throwable t) {
                        Log.d("API","Error: "+t.getMessage());
                    }
                });

            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*productoRepository = new ProductoRepository(EditarActivity.this);
                productoRepository.eliminarFirestore(miProducto, new CallBackFirestore<Producto>() {
                    @Override
                    public void correcto(Producto respuesta) {
                        finish();
                    }
                });*/
                Retrofit retrofit = TiendAppService.obtenerInstancia();
                ProductoAPI productoAPI = retrofit.create(ProductoAPI.class);

                productoAPI.eliminar(miProducto.getId()).enqueue(new Callback<Map>() {
                    @Override
                    public void onResponse(Call<Map> call, Response<Map> response) {

                        if (response.body() != null) {
                            Log.d("API","Eliminar"+response.body().toString());
                        }

                        finish();


                    }

                    @Override
                    public void onFailure(Call<Map> call, Throwable t) {
                        Log.d("API","Eliminar"+t.getMessage());
                    }
                });

            }
        });
    }

    public void asociarElementos(){
        txvEditar = findViewById(R.id.txv_Editar);
        edtNombre = findViewById(R.id.edt_Nombre);
        edtDescripcion = findViewById(R.id.edt_Descripcion);
        edtPrecio = findViewById(R.id.edt_Precio);
        btnEditar = findViewById(R.id.btn_Editar);
        btnEliminar = findViewById(R.id.btn_eliminar);
    }
}
