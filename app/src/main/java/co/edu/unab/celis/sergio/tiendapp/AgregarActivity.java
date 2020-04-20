package co.edu.unab.celis.sergio.tiendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AgregarActivity extends AppCompatActivity {

    private EditText edtNombre;
    private EditText edtDescripcion;
    private EditText edtPrecio;
    private Button btnAgregar;
    private ProductoDAO productoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        this.asociarElementos();

        BaseDatos bd = BaseDatos.obtenerInstancia(this);
        productoDAO = bd.productoDAO();

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Producto nuevoProdcuto = new Producto(
                        edtNombre.getText().toString(),
                        Double.parseDouble(edtPrecio.getText().toString()));
                        nuevoProdcuto.setDescripción(edtDescripcion.getText().toString());
                productoDAO.agregar(nuevoProdcuto);

                finish();
            }
        });
    }

    public void asociarElementos(){
        edtNombre = findViewById(R.id.edtNombre);
        edtDescripcion = findViewById(R.id.edtDescripcion);
        edtPrecio = findViewById(R.id.edtPrecio);
        btnAgregar = findViewById(R.id.btnAgregar);
    }
}
