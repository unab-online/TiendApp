package co.edu.unab.vasquez.nodier.tiendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class agregarActivity extends AppCompatActivity {

    private TextView edtNombre, edtDescripcion, edtPrecio;
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
                productoDAO.agregar(nuevoProducto);
                finish();
                Toast.makeText(agregarActivity.this, "Agregado", Toast.LENGTH_SHORT).show();
                
            }
        });

    }

    public void asociarElementos(){
        edtNombre = findViewById(R.id.edt_Nombre);
        edtDescripcion = findViewById(R.id.edt_Descripcion);
        edtPrecio = findViewById(R.id.edt_Precio);
        btnAgregar = findViewById(R.id.btn_Agregar);
    }
}
