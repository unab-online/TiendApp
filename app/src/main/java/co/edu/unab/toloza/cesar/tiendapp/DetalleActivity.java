package co.edu.unab.toloza.cesar.tiendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

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
        textNombre = findViewById(R.id.textNombre);
        textPrecio = findViewById(R.id.textPrecio);
        textDescripcion = findViewById(R.id.textDescripcion);
    }
}
