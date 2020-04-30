package co.edu.unab.saavedra.juan.tiendapp;

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

    private TextView txvTituloEditar;
    private EditText edtNombreEditar;
    private EditText edtDescripcionEditar;
    private EditText edtPrecioEditar;
    private Button btnEditarProducto;
    private Button btnEliminarProducto;
    private ProductoDAO productoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        asociarElementos();

        BaseDatos bd = BaseDatos.obtenerInstancia(this);
        productoDAO = bd.productoDAO();

        final Producto miProducto = (Producto) getIntent().getSerializableExtra("producto");
        txvTituloEditar.setText(getString(R.string.Titulo_Editar, miProducto.getNombre()));
        edtPrecioEditar.setText(Double.toString(miProducto.getPrecio()));
        edtNombreEditar.setText(miProducto.getNombre());
        edtDescripcionEditar.setText(miProducto.getDescripcion());

        btnEditarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miProducto.setNombre(edtNombreEditar.getText().toString());
                miProducto.setPrecio(Double.parseDouble(edtPrecioEditar.getText().toString()));
                miProducto.setDescripcion(edtDescripcionEditar.getText().toString());
                //productoDAO.actualizar(miProducto);
                FirebaseFirestore firestoreDB = FirebaseFirestore.getInstance();
                ProductoRepository productoRepository = new ProductoRepository(EditarActivity.this);
                productoRepository.editarFirestore(miProducto, new CallBackFirestore<Producto>() {
                    @Override
                    public void correcto(Producto respuesta) {
                        finish();
                    }
                });
            }
        });

        btnEliminarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //productoDAO.eliminar(miProducto);
                FirebaseFirestore firestoreDB = FirebaseFirestore.getInstance();
                firestoreDB.collection("productos").document(miProducto.getId()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        finish();
                    }
                });
            }
        });
    }

    void asociarElementos(){

        txvTituloEditar = findViewById(R.id.txv_tituloEditar);
        edtNombreEditar = findViewById(R.id.edt_nombreEditar);
        edtDescripcionEditar = findViewById(R.id.edt_descripcionEditar);
        edtPrecioEditar = findViewById(R.id.edt_precioEditar);
        btnEditarProducto = findViewById(R.id.btn_editarProducto);
        btnEliminarProducto = findViewById(R.id.btn_eliminarProducto);

    }
}
