package co.edu.unab.toloza.cesar.tiendapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Map;

import co.edu.unab.toloza.cesar.tiendapp.model.db.network.ProductoAPI;
import co.edu.unab.toloza.cesar.tiendapp.model.db.network.TiendAppService;
import co.edu.unab.toloza.cesar.tiendapp.model.entity.Producto;
import co.edu.unab.toloza.cesar.tiendapp.model.repository.ProductoRepository;
import co.edu.unab.toloza.cesar.tiendapp.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EditarActivity extends AppCompatActivity {

    private TextView titulo;
    private EditText nombre, precio, descripcion;
    private Button btnEditar, btnEliminar;
    ProductoRepository productoRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        productoRepository = new ProductoRepository(EditarActivity.this);

        asociarElementos();

        final Producto producto = (Producto) getIntent().getSerializableExtra("producto");

        titulo.setText(getString(R.string.titulo_editar, producto.getNombre()));
        nombre.setText(producto.getNombre());
        precio.setText(String.valueOf(producto.getPrecio()));
        descripcion.setText(producto.getDescripcion());

        btnEditar.setOnClickListener(v -> {
            producto.setNombre(nombre.getText().toString());
            producto.setPrecio(Double.valueOf(precio.getText().toString()));
            producto.setDescripcion(descripcion.getText().toString());
           //productoRepository.editarFirestore(producto, respuesta -> finish());
            productoRepository.editarProductoAPI(producto, respuesta -> finish());
        });

        btnEliminar.setOnClickListener(v -> {
            //productoRepository.eliminarFirestore(producto, respuesta -> finish());
            productoRepository.eliminarProductoAPI(producto, respuesta -> finish());

        });
    }

    private void asociarElementos(){
        titulo = findViewById(R.id.text_editar_titulo);
        nombre = findViewById(R.id.editText_editar_nombre);
        precio = findViewById(R.id.editText_editar_precio);
        descripcion = findViewById(R.id.editText_editar_desc);
        btnEditar = findViewById(R.id.button_editar);
        btnEliminar = findViewById(R.id.button_borrar);
    }

}
