package co.edu.unab.toloza.cesar.tiendapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import co.edu.unab.toloza.cesar.tiendapp.model.entity.Producto;
import co.edu.unab.toloza.cesar.tiendapp.R;

public class DetalleActivity extends AppCompatActivity {

    TextView textNombre, textPrecio, textDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        Bundle misDatos = getIntent().getExtras();
        Producto producto = (Producto) misDatos.getSerializable("producto");

        this.asociarElementos();

        textNombre.setText(producto.getNombre());
        textPrecio.setText("$"+producto.getPrecio());
        textDescripcion.setText(producto.getDescripcion());

    }

    private void asociarElementos(){
        textNombre = findViewById(R.id.tv_nombre);
        textPrecio = findViewById(R.id.tv_precio);
        textDescripcion = findViewById(R.id.textDescripcion);
    }
}
