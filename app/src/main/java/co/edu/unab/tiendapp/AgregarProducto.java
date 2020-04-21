package co.edu.unab.tiendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class AgregarProducto extends AppCompatActivity {
    private Button btnAgregarProducto;
    private EditText edtNombre;
    private EditText edtDescripcion;
    private EditText edtPrecio;
    ProductoDAO productoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);
        cargaElementos();
        BaseDatos bd= BaseDatos.obtenerInstancia(this);
        //con el productoDao puede tabajar con las base de datos
        productoDAO= bd.productoDAO();
        btnAgregarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Producto nuevoProducto= new Producto(edtNombre.getText().toString(),
                         Double.parseDouble(edtPrecio.getText().toString()));
                nuevoProducto.setDescripcion(edtDescripcion.getText().toString());
                productoDAO.agregar(nuevoProducto);
                finish();

            }
        });
    }

    public void cargaElementos(){
        btnAgregarProducto=findViewById(R.id.btn_insertar_producto);
        edtNombre=findViewById(R.id.edt_nombre);
        edtDescripcion=findViewById(R.id.edt_descripcion);
        edtPrecio=findViewById(R.id.edt_precio);
    }

}
