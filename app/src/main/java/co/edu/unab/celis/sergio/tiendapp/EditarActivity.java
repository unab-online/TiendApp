package co.edu.unab.celis.sergio.tiendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditarActivity extends AppCompatActivity {

    private EditText edtNombre;
    private EditText edtDescripcion;
    private EditText edtPrecio;
    private Button btnEditar;
    private Button btnEliminar;
    private ProductoDAO productoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        this.asociarElementos();

        BaseDatos bd = BaseDatos.obtenerInstancia(this);
        productoDAO = bd.productoDAO();

        Bundle datos = getIntent().getExtras();
        final Producto producto = (Producto) datos.getSerializable("info");

        edtNombre.setText(producto.getNombre());
        edtDescripcion.setText(producto.getDescripción());
        edtPrecio.setText(String.valueOf(producto.getPrecio()));

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productoDAO.eliminar(producto);
                finish();
            }
        });
    }

    public void asociarElementos(){
        edtNombre = findViewById(R.id.edtSetNombre);
        edtDescripcion = findViewById(R.id.edtSetDesc);
        edtPrecio = findViewById(R.id.edtSetPrecio);
        btnEditar = findViewById(R.id.btnEditar);
        btnEliminar = findViewById(R.id.btnEliminar);
    }
}
