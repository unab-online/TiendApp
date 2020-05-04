package co.edu.unab.rey.carlos.crackapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class Editar extends AppCompatActivity {

    private EditText edtNombre;
    private EditText edtPrecio;
    private EditText edtDescripcion;
    private TextView txvTituloEditar;

    private Button btnAgregar;
    private Button btnEliminar;

    ProductoDAO productoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);


        asociarProductos();
        final Producto miProducto = (Producto) getIntent().getSerializableExtra("producto") ;

        BaseDatos db = BaseDatos.obtenerInstancia(this);
        productoDAO = db.productoDAO();

        txvTituloEditar.setText(getString(R.string.txt_titulo_editar, miProducto.getNombre()));
        edtNombre.setText(miProducto.getNombre());
        edtPrecio.setText(String.valueOf(miProducto.getPrecio()));
        edtDescripcion.setText(miProducto.getDescripcion());

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miProducto.setNombre(edtNombre.getText().toString());
                miProducto.setPrecio(Double.parseDouble(edtPrecio.getText().toString()));
                miProducto.setDescripcion(edtDescripcion.getText().toString());
                //productoDAO.actualizar(miProducto);
                ProductoRepository productoRepository = new ProductoRepository(Editar.this);
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
                //productoDAO.borrar(miProducto);
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



    public void asociarProductos(){
        edtNombre = findViewById(R.id.edt_nombre_nuevo_producto);
        edtPrecio = findViewById(R.id.edt_precio_nuevo_producto);
        edtDescripcion = findViewById(R.id.edt_descripcion_nuevo_producto);
        txvTituloEditar = findViewById(R.id.txv_titulo_editar);
        btnAgregar = findViewById(R.id.btn_agregar);
        btnEliminar = findViewById(R.id.btn_eliminar);

    }

}
