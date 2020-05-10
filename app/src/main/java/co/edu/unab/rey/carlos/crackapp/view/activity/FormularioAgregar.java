package co.edu.unab.rey.carlos.crackapp.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

import co.edu.unab.rey.carlos.crackapp.model.bd.network.ProductoAPI;
import co.edu.unab.rey.carlos.crackapp.model.bd.network.TiendAppService;
import co.edu.unab.rey.carlos.crackapp.model.entity.Producto;
import co.edu.unab.rey.carlos.crackapp.model.bd.local.ProductoDAO;
import co.edu.unab.rey.carlos.crackapp.R;
import co.edu.unab.rey.carlos.crackapp.model.bd.local.BaseDatos;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FormularioAgregar extends AppCompatActivity {

    private EditText edtNombre;
    private EditText edtPrecio;
    private EditText edtDescripcion;

    private Button btnAgregar;

    ProductoDAO productoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_agregar);

        asociarProductos();

        BaseDatos db = BaseDatos.obtenerInstancia(this);
        //productoDAO = db.productoDAO();

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Producto nuevoProducto = new Producto(edtNombre.getText().toString(),
                        Double.parseDouble(edtPrecio.getText().toString()),
                        edtDescripcion.getText().toString());
                //productoDAO.agregar(nuevoProducto);
                //FIRESTORE
                /*

                FirebaseFirestore firestoreDb = FirebaseFirestore.getInstance();
                firestoreDb.collection("productos").add(nuevoProducto).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        finish();
                    }
                });

                 */

                Retrofit retrofit = TiendAppService.obtenerInstancias();
                ProductoAPI productoAPI = retrofit.create(ProductoAPI.class);

                productoAPI.agregar(nuevoProducto).enqueue(new Callback<Map>() {
                    @Override
                    public void onResponse(Call<Map> call, Response<Map> response) {
                        if (response.body()!= null){
                            Log.d("API","agregar" +response.body().toString());
                            String id = (String) response.body().get("name");
                            nuevoProducto.setId(id);
                            finish();
                            /*
                            productoAPI.editar(id, nuevoProducto).enqueue(new Callback<Map>() {
                                @Override
                                public void onResponse(Call<Map> call, Response<Map> response) {
                                    finish();
                                }

                                @Override
                                public void onFailure(Call<Map> call, Throwable t) {

                                }
                            });
                            finish();

                             */

                        }

                    }

                    @Override
                    public void onFailure(Call<Map> call, Throwable t) {
                        Log.d("API", "Error agregar "+t.getMessage());
                    }
                });

                finish();

            }
        });

    }

    public void asociarProductos(){
        edtNombre = findViewById(R.id.edt_nombre_nuevo_producto);
        edtPrecio = findViewById(R.id.edt_precio_nuevo_producto);
        edtDescripcion = findViewById(R.id.edt_descripcion_nuevo_producto);
        btnAgregar = findViewById(R.id.btn_agregar);
    }
}
