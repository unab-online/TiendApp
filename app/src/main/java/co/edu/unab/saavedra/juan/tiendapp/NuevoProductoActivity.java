package co.edu.unab.saavedra.juan.tiendapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class NuevoProductoActivity extends AppCompatActivity {

    private EditText edtNombre;
    private EditText edtDescripcion;
    private EditText edtPrecio;
    private Button btnAgregarProducto;
    ProductoDAO productoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_producto);

        asociarElementos();

        BaseDatos bd = BaseDatos.obtenerInstancia(this);
        productoDAO = bd.productoDAO();

        btnAgregarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Producto nuevoProducto = new Producto(edtNombre.getText().toString(), Double.parseDouble(edtPrecio.getText().toString()));
                nuevoProducto.setDescripcion(edtDescripcion.getText().toString());

                //productoDAO.agregar(nuevoProducto);
                FirebaseFirestore firestoreDB = FirebaseFirestore.getInstance();
                firestoreDB.collection("productos").add(nuevoProducto).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        finish();
                    }
                });
            }
        });
    }

    void asociarElementos(){
        edtNombre = findViewById(R.id.edt_nombre);
        edtDescripcion = findViewById(R.id.edt_descripcion);
        edtPrecio = findViewById(R.id.edt_precio);
        btnAgregarProducto = findViewById(R.id.btn_agregarProducto);
    }
}
