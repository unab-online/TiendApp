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
    private ProductoDAO productoDAO;
    private ProductoRepository productoRepository;
    //private Producto miProducto;

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

                //ROOM
                Producto nuevoProducto = new Producto(
                        edtNombre.getText().toString(),
                        Double.parseDouble(edtPrecio.getText().toString())
                );
                nuevoProducto.setDescripcion(edtDescripcion.getText().toString());

                //productoDAO.agregar(nuevoProducto);
                //FIREBASE
                /*FirebaseFirestore firestoreDB = FirebaseFirestore.getInstance();
                firestoreDB.collection("productos").add(nuevoProducto).
                        addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        finish();
                    }
                });*/

                productoRepository = new ProductoRepository(agregarActivity.this);
                productoRepository.agregarFirestore(nuevoProducto, new CallBackFirestore<Producto>() {
                    @Override
                    public void correcto(Producto respuesta) {
                        finish();
                        Toast.makeText(agregarActivity.this, "Agregado con Exito", Toast.LENGTH_SHORT).show();
                    }
                });





                
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
