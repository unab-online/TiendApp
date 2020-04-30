package co.edu.unab.vasquez.nodier.tiendapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditarActivity extends AppCompatActivity {

    private EditText edtNombre, edtDescripcion, edtPrecio;
    private TextView txvEditar;
    private Button btnEditar, btnEliminar;
    private  ProductoRepository productoRepository;

    ProductoDAO productoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        BaseDatos bd = BaseDatos.obtenerInstancia(this);
        productoDAO = bd.productoDAO();

        asociarElementos();
        final Producto miProducto = (Producto) getIntent().getSerializableExtra("producto");
        txvEditar.setText(getString(R.string.txt_titulo_editar,miProducto.getNombre(),miProducto.getPrecio()));
        edtNombre.setText(miProducto.getNombre());
        edtDescripcion.setText(miProducto.getDescripcion());
        edtPrecio.setText(String.valueOf(miProducto.getPrecio()));

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miProducto.setNombre(edtNombre.getText().toString());
                miProducto.setDescripcion(edtDescripcion.getText().toString());
                miProducto.setPrecio(Double.parseDouble(edtPrecio.getText().toString()));
//                productoDAO.actualizar(miProducto);


                productoRepository = new ProductoRepository(EditarActivity.this);
                productoRepository.editarFirestore(miProducto, new CallBackFirestore<Producto>() {
                    @Override
                    public void correcto(Producto respuesta) {
                        finish();
                    }
                });
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productoRepository = new ProductoRepository(EditarActivity.this);
                productoRepository.eliminarFirestore(miProducto, new CallBackFirestore<Producto>() {
                    @Override
                    public void correcto(Producto respuesta) {
                        finish();
                    }
                });
            }
        });
    }

    public void asociarElementos(){
        txvEditar = findViewById(R.id.txv_Editar);
        edtNombre = findViewById(R.id.edt_Nombre);
        edtDescripcion = findViewById(R.id.edt_Descripcion);
        edtPrecio = findViewById(R.id.edt_Precio);
        btnEditar = findViewById(R.id.btn_Editar);
        btnEliminar = findViewById(R.id.btn_eliminar);
    }
}
