package co.edu.unab.leal.jakson.silviapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditarActivity extends AppCompatActivity {

    private TextView txvTituloEditar;
    private EditText edtNombre, edtPrecio, edtDescripcion;
    private Button btnEditar, btnEliminar;
    private ProductoDAO productoDAO;
    private FirebaseFirestore freefire = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        asociarElementos();
        BaseDatos bd = BaseDatos.obtenerInstancia(this);
        //productoDAO = bd.productoDAO();



        final Producto producto = (Producto) getIntent().getSerializableExtra("producto");//Capturar producto enviado desde el listado

        txvTituloEditar.setText(getString(R.string.txt_titulo_editar, producto.getNombre()));
        edtNombre.setText(producto.getNombre());
        edtPrecio.setText(String.valueOf(producto.getPrecio()));
        edtDescripcion.setText(producto.getDescripcion());

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                producto.setNombre(edtNombre.getText().toString());
                producto.setPrecio(Double.parseDouble(edtPrecio.getText().toString()));
                producto.setDescripcion(edtDescripcion.getText().toString());
                //productoDAO.actualizar(producto);//Actualizar datos del producto en la base de datos
                freefire.collection("productos").document(producto.getId()).set(producto).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        finish();
                    }
                });

            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //productoDAO.eliminar(producto);//Borrar producto de la base de datos

                freefire.collection("productos").document(producto.getId()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        finish();
                    }
                });

            }
        });
    }

    private void asociarElementos(){
        txvTituloEditar = findViewById(R.id.txv_tituloEditar);
        edtNombre = findViewById(R.id.edt_nombreEditar);
        edtPrecio = findViewById(R.id.edt_precioEditar);
        edtDescripcion = findViewById(R.id.edt_descripcionEditar);
        btnEditar = findViewById(R.id.btn_editar);
        btnEliminar = findViewById(R.id.btn_eliminar);
    }
}
