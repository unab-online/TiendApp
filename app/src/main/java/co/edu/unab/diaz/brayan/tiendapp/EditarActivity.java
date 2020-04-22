package co.edu.unab.diaz.brayan.tiendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditarActivity extends AppCompatActivity {

    private TextView txvTituloEditar;
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

        final Producto producto = (Producto) getIntent().getSerializableExtra("producto");//Capturar producto enviado desde el listado

        txvTituloEditar.setText(getString(R.string.txt_titulo_editar, producto.getNombre()));
        edtNombre.setText(producto.getNombre());
        edtPrecio.setText(String.valueOf(producto.getPrecio()));
        edtDescripcion.setText(producto.getDescripcion());

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                producto.setNombre(edtNombre.getText().toString());
                producto.setPrecio(Double.parseDouble(edtPrecio.getText().toString()));
                producto.setDescripcion(edtDescripcion.getText().toString());
                productoDAO.actualizar(producto);//Actualizar datos del producto en la base de datos
                finish();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productoDAO.borrar(producto);//Borrar producto de la base de datos
                finish();
            }
        });
    }

    private void asociarElementos(){
        txvTituloEditar = findViewById(R.id.txv_tituloEditar);
        edtNombre = findViewById(R.id.edt_nombreEditar);
        edtPrecio = findViewById(R.id.edt_precioEditar);
        edtDescripcion = findViewById(R.id.edt_descripcionEditar);
        btnEditar = findViewById(R.id.btn_editar);
        btnEliminar = findViewById(R.id.btn_eliminar);
    }
}
