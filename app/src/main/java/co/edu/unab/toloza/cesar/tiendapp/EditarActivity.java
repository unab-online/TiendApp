package co.edu.unab.toloza.cesar.tiendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditarActivity extends AppCompatActivity {

    private TextView titulo;
    private EditText nombre, precio, descripcion;
    private Button btnEditar, btnEliminar;
    private ProductoDAO productoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        BaseDatos db = BaseDatos.obtenerInstancia(this);
        productoDAO = db.productoDAO();

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
                productoDAO.actualizar(producto);
                finish();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productoDAO.eliminar(producto);
                finish();
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
