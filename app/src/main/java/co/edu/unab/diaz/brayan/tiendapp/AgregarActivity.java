package co.edu.unab.diaz.brayan.tiendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AgregarActivity extends AppCompatActivity {


    private EditText edtNombre, edtPrecio, edtDescripcion;
    private Button btnAgregar;
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
            public void onClick(View v) {
                Producto nuevoProducto = new Producto(
                        edtNombre.getText().toString(),
                        Double.parseDouble(edtPrecio.getText().toString())
                );
                nuevoProducto.setDescripcion(edtDescripcion.getText().toString());
                productoDAO.agregar(nuevoProducto);//Agregar nuevo producto a la base de datos
                finish();
            }
        });

    }


    private void asociarElementos(){
        edtNombre = findViewById(R.id.edt_nombreProducto);
        edtPrecio = findViewById(R.id.edt_precioProducto);
        edtDescripcion = findViewById(R.id.edt_descripcionProducto);
        btnAgregar = findViewById(R.id.btn_agregarProducto);
    }
}
