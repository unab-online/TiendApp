package co.edu.unab.toloza.cesar.tiendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AgregarActivity extends AppCompatActivity {

    private EditText nombreProducto, precioProducto, descripcionProducto;
    private Button btnAgregar;
    private ProductoDAO productoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        BaseDatos db = BaseDatos.obtenerInstancia(this);
        productoDAO = db.productoDAO();

        asociarElementos();

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = nombreProducto.getText().toString();
                String descripcion = descripcionProducto.getText().toString();
                double precio = Double.valueOf(precioProducto.getText().toString());
                productoDAO.agregar(new Producto(nombre, descripcion, precio));
                finish();
            }
        });
    }

    private void asociarElementos(){
        nombreProducto = findViewById(R.id.editText_nombre);
        precioProducto = findViewById(R.id.editText_precio);
        descripcionProducto = findViewById(R.id.editText_desc);
        btnAgregar = findViewById(R.id.button_agregar_nuevo);
    }
}
