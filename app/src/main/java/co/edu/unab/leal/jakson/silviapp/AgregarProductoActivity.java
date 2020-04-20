package co.edu.unab.leal.jakson.silviapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AgregarProductoActivity extends AppCompatActivity {

    private Button btnAgregar, btnVolver;
    private EditText editTextPrecio, editTextNombre, editTextDesc;
    private ProductoDAO productoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);

        this.inicializar();

        BaseDatos db = BaseDatos.obtenerInstancia(this);
        productoDAO = db.productoDAO();

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double auxPrecio;
                String auxStrP;

                auxStrP = editTextPrecio.getText().toString();
                auxPrecio = Double.valueOf(auxStrP);

                Producto nuevoProducto = new Producto(editTextNombre.getText().toString(), auxPrecio, editTextDesc.getText().toString());
                productoDAO.agregar(nuevoProducto);

                onResume();

                editTextNombre.setText(null);
                editTextDesc.setText(null);
                editTextPrecio.setText(null);

            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
    private void inicializar(){
        btnAgregar = findViewById(R.id.btn_agregar);
        btnVolver = findViewById(R.id.btn_volver);
        editTextPrecio = findViewById(R.id.editText_precio);
        editTextNombre = findViewById(R.id.editText_nombre);
        editTextDesc = findViewById(R.id.editText_descripcion);
    }
}
