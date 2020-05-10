package co.edu.unab.vasquez.nodier.tiendapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class agregarActivity extends AppCompatActivity {

    private TextView edtNombre, edtDescripcion, edtPrecio;
    private Button btnAgregar;
    private ProductoDAO productoDAO;
    private ProductoRepository productoRepository;
    //private Producto miProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        asociarElementos();
        BaseDatos bd = BaseDatos.obtenerInstancia(this);


       productoDAO = bd.productoDAO();

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ROOM
                final Producto nuevoProducto = new Producto(
                        edtNombre.getText().toString(),
                        edtDescripcion.getText().toString(),
                        Double.parseDouble(edtPrecio.getText().toString())
                );
                nuevoProducto.setDescripcion(edtDescripcion.getText().toString());

                //productoDAO.agregar(nuevoProducto);
                //FIREBASE
                /*FirebaseFirestore firestoreDB = FirebaseFirestore.getInstance();
                firestoreDB.collection("productos").add(nuevoProducto).
                        addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        finish();
                    }
                });*/

                /*productoRepository = new ProductoRepository(agregarActivity.this);
                productoRepository.agregarFirestore(nuevoProducto, new CallBackFirestore<Producto>() {
                    @Override
                    public void correcto(Producto respuesta) {
                        finish();
                        Toast.makeText(agregarActivity.this, "Agregado con Exito", Toast.LENGTH_SHORT).show();
                    }
                });*/

                Retrofit retrofit = TiendAppService.obtenerInstancia();
                final ProductoAPI productoAPI = retrofit.create(ProductoAPI.class);

                productoAPI.agregar(nuevoProducto).enqueue(new Callback<Map>() {
                    @Override
                    public void onResponse(Call<Map> call, Response<Map> response) {
                        assert response.body() != null;
                        Log.d("API","Agregado "+response.body().toString());
                        String id = (String) response.body().get("name");
                        nuevoProducto.setId(id);
                        productoAPI.editar(id,nuevoProducto).enqueue(new Callback<Map>() {
                            @Override
                            public void onResponse(Call<Map> call, Response<Map> response) {
                                finish();
                            }

                            @Override
                            public void onFailure(Call<Map> call, Throwable t) {

                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<Map> call, Throwable t) {
                        Log.d("API","Agregado "+t.getMessage());
                    }
                });



                
            }



        });

    }

    public void asociarElementos(){
        edtNombre = findViewById(R.id.edt_Nombre);
        edtDescripcion = findViewById(R.id.edt_Descripcion);
        edtPrecio = findViewById(R.id.edt_Precio);
        btnAgregar = findViewById(R.id.btn_Editar);
    }
}
