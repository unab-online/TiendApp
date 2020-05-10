package co.edu.unab.rey.carlos.crackapp.model.entity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Map;

import co.edu.unab.rey.carlos.crackapp.R;
import co.edu.unab.rey.carlos.crackapp.model.bd.local.BaseDatos;
import co.edu.unab.rey.carlos.crackapp.model.bd.local.ProductoDAO;
import co.edu.unab.rey.carlos.crackapp.model.bd.network.CallBackFirestore;
import co.edu.unab.rey.carlos.crackapp.model.bd.network.ProductoAPI;
import co.edu.unab.rey.carlos.crackapp.model.bd.network.TiendAppService;
import co.edu.unab.rey.carlos.crackapp.model.repository.ProductoRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Editar extends AppCompatActivity {

    private EditText edtNombre;
    private EditText edtPrecio;
    private EditText edtDescripcion;
    private TextView txvTituloEditar;

    private Button btnAgregar;
    private Button btnEliminar;

    ProductoDAO productoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);


        asociarProductos();
        final Producto miProducto = (Producto) getIntent().getSerializableExtra("producto") ;

        BaseDatos db = BaseDatos.obtenerInstancia(this);
        productoDAO = db.productoDAO();

        txvTituloEditar.setText(getString(R.string.txt_titulo_editar, miProducto.getNombre()));
        edtNombre.setText(miProducto.getNombre());
        edtPrecio.setText(String.valueOf(miProducto.getPrecio()));
        edtDescripcion.setText(miProducto.getDescripcion());

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miProducto.setNombre(edtNombre.getText().toString());
                miProducto.setPrecio(Double.parseDouble(edtPrecio.getText().toString()));
                miProducto.setDescripcion(edtDescripcion.getText().toString());
                //productoDAO.actualizar(miProducto);
                //FIRESTORE
                /*
                ProductoRepository productoRepository = new ProductoRepository(Editar.this);
                productoRepository.editarFirestore(miProducto, new CallBackFirestore<Producto>() {
                    @Override
                    public void correcto(Producto respuesta) {
                        finish();
                    }
                });
                //FIRESTORE
                 */
                Retrofit retrofit = TiendAppService.obtenerInstancias();
                ProductoAPI productoAPI = retrofit.create(ProductoAPI.class);

                productoAPI.editar(miProducto.getId(),miProducto).enqueue(new Callback<Map>() {
                    @Override
                    public void onResponse(Call<Map> call, Response<Map> response) {
                        if (response.body()!= null){
                            Log.d("API", response.body().toString());
                            finish();
                        }

                    }

                    @Override
                    public void onFailure(Call<Map> call, Throwable t) {
                        Log.d("API", "Error "+t.getMessage());
                    }
                });


            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                //FIREBASE
            /*
                ProductoRepository productoRepository = new ProductoRepository(Editar.this);
                productoRepository.eliminarFirestore(miProducto, new CallBackFirestore<Producto>() {
                    @Override
                    public void correcto(Producto respuesta) {
                        finish();
                    }
                });

             */
                //FIREBASE

                Retrofit retrofit = TiendAppService.obtenerInstancias();
                ProductoAPI productoAPI = retrofit.create(ProductoAPI.class);
                productoAPI.eliminar(miProducto.getId()).enqueue(new Callback<Map>() {
                    @Override
                    public void onResponse(Call<Map> call, Response<Map> response) {
                        if (response.body() != null){
                            Log.d("API","eliminar: "+response.body().toString());
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<Map> call, Throwable t) {
                        Log.d("API","error "+t.getMessage());
                    }
                });
            }
        });
    }



    public void asociarProductos(){
        edtNombre = findViewById(R.id.edt_nombre_nuevo_producto);
        edtPrecio = findViewById(R.id.edt_precio_nuevo_producto);
        edtDescripcion = findViewById(R.id.edt_descripcion_nuevo_producto);
        txvTituloEditar = findViewById(R.id.txv_titulo_editar);
        btnAgregar = findViewById(R.id.btn_agregar);
        btnEliminar = findViewById(R.id.btn_eliminar);

    }

}
