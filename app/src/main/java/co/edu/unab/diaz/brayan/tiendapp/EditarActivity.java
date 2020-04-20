package co.edu.unab.diaz.brayan.tiendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditarActivity extends AppCompatActivity {

    private EditText edtNombre, edtPrecio, edtDescripcion;
    private Button btnEditar, btnEliminar;
    private ProductoDAO productoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        asociarElementos();
        BaseDatos bd = BaseDatos.obtenerInstancia(this);
        productoDAO = bd.productoDAO();

        final Bundle datos = getIntent().getExtras();
        final Producto producto = (Producto) datos.getSerializable("producto");

        edtNombre.setText(producto.getNombre());
        edtPrecio.setText(String.valueOf(producto.getPrecio()));
        edtDescripcion.setText(producto.getDescripcion());

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productoDAO.borrar(producto);//Borrar producto de la base de datos al hacer click
                finish();
            }
        });
    }

    private void asociarElementos(){
        edtNombre = findViewById(R.id.edt_nombreEditar);
        edtPrecio = findViewById(R.id.edt_precioEditar);
        edtDescripcion = findViewById(R.id.edt_descripcionEditar);
        btnEditar = findViewById(R.id.btn_editar);
        btnEliminar = findViewById(R.id.btn_eliminar);
    }
}
