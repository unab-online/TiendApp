package co.edu.unab.vasquez.nodier.tiendapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

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
                //productoDAO.agregar(nuevoProducto);

                FirebaseFirestore firestoreDB = FirebaseFirestore.getInstance();
                firestoreDB.collection("productos").add(nuevoProducto).
                        addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        finish();
                    }
                });


                Toast.makeText(agregarActivity.this, "Agregado", Toast.LENGTH_SHORT).show();
                
            }
        });

    }

    public void asociarElementos(){
        edtNombre = findViewById(R.id.edt_Nombre);
        edtDescripcion = findViewById(R.id.edt_Descripcion);
        edtPrecio = findViewById(R.id.edt_Precio);
        btnAgregar = findViewById(R.id.btn_Editar);
    }
}
