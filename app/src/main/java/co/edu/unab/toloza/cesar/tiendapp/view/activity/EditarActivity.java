package co.edu.unab.toloza.cesar.tiendapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import co.edu.unab.toloza.cesar.tiendapp.model.db.network.CallBackFirestore;
import co.edu.unab.toloza.cesar.tiendapp.model.entity.Producto;
import co.edu.unab.toloza.cesar.tiendapp.model.repository.ProductoRepository;
import co.edu.unab.toloza.cesar.tiendapp.R;

public class EditarActivity extends AppCompatActivity {

    private TextView titulo;
    private EditText nombre, precio, descripcion;
    private Button btnEditar, btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        asociarElementos();

        final Producto producto = (Producto) getIntent().getSerializableExtra("producto");

        titulo.setText(getString(R.string.titulo_editar, producto.getNombre()));
        nombre.setText(producto.getNombre());
        precio.setText(String.valueOf(producto.getPrecio()));
        descripcion.setText(producto.getDescripcion());

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                producto.setNombre(nombre.getText().toString());
                producto.setPrecio(Double.valueOf(precio.getText().toString()));
                producto.setDescripcion(descripcion.getText().toString());
                //productoDAO.actualizar(producto);
                ProductoRepository productoRepository = new ProductoRepository(EditarActivity.this);
                productoRepository.editarFirestore(producto, new CallBackFirestore<Producto>() {
                    @Override
                    public void correcto(Producto respuesta) {
                        finish();
                    }
                });

            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //productoDAO.eliminar(producto);
                ProductoRepository productoRepository = new ProductoRepository(EditarActivity.this);
                productoRepository.eliminarFirestore(producto, new CallBackFirestore<Producto>() {
                    @Override
                    public void correcto(Producto respuesta) {
                        finish();
                    }
                });
            }
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
