package co.edu.unab.rey.carlos.crackapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
        productoDAO = db.productoDAO();

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Producto nuevoProducto = new Producto(edtNombre.getText().toString(),
                        Double.parseDouble(edtPrecio.getText().toString()),
                        edtDescripcion.getText().toString());
                productoDAO.agregar(nuevoProducto);

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
