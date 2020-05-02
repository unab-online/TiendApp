package co.edu.unab.toloza.cesar.tiendapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import co.edu.unab.toloza.cesar.tiendapp.model.db.network.CallBackFirestore;
import co.edu.unab.toloza.cesar.tiendapp.model.entity.Producto;
import co.edu.unab.toloza.cesar.tiendapp.model.repository.ProductoRepository;
import co.edu.unab.toloza.cesar.tiendapp.R;

public class AgregarActivity extends AppCompatActivity {

    private EditText nombreProducto, precioProducto, descripcionProducto;
    private Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        asociarElementos();

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = nombreProducto.getText().toString();
                String descripcion = descripcionProducto.getText().toString();
                double precio = Double.valueOf(precioProducto.getText().toString());
                //productoDAO.agregar(new Producto(nombre, descripcion, precio));
                Producto nuevoProducto = new Producto(nombre, descripcion, precio);
                ProductoRepository productoRepository = new ProductoRepository(getApplicationContext());
                productoRepository.agregarFirestore(nuevoProducto, new CallBackFirestore<Producto>() {
                    @Override
                    public void correcto(Producto respuesta) {
                        finish();
                    }
                });
            }
        });
    }

    private void asociarElementos(){
        nombreProducto = findViewById(R.id.editText_editar_nombre);
        precioProducto = findViewById(R.id.editText_editar_precio);
        descripcionProducto = findViewById(R.id.editText_editar_desc);
        btnAgregar = findViewById(R.id.button_agregar_nuevo);
    }
}
