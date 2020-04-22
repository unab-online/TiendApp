package co.edu.unab.tiendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AgregarActivity extends AppCompatActivity {

    EditText edtNombre;
    EditText edtPrecio;
    EditText edtDesc;
    Button btnAgregar;
    ProductoDAO productoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        asociarElementos();

        BaseDatos bd = BaseDatos.obtenerInstancia(this);
        productoDAO = bd.productoDAO();

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Producto nuevoProducto = new Producto(
                        edtNombre.getText().toString(),
                        Double.parseDouble(edtPrecio.getText().toString())
                        );
                nuevoProducto.setDescripcion(edtDesc.getText().toString());

                productoDAO.agregar(nuevoProducto);

                finish();

            }
        });
    }

    void asociarElementos(){
        edtNombre = findViewById(R.id.edt_nombre);
        edtPrecio = findViewById(R.id.edt_precio);
        edtDesc = findViewById(R.id.edt_desc);
        btnAgregar = findViewById(R.id.btn_agregar);
    }
}
