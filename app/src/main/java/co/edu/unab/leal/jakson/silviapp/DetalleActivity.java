package co.edu.unab.leal.jakson.silviapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetalleActivity extends AppCompatActivity {

    private TextView textViewPrecio;
    private TextView textViewNombre;
    private TextView textViewDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        Bundle datosB = getIntent().getExtras();
        Producto prodObj = (Producto) datosB.getSerializable("objeto");

        this.asociarProductos();
        textViewNombre.setText(prodObj.getNombre());
        textViewPrecio.setText("$"+prodObj.getPrecio()+"USD");
        textViewDescripcion.setText(prodObj.getDescripcion());

}

    private void asociarProductos(){

        textViewDescripcion = findViewById(R.id.textView_descripcion);
        textViewNombre = findViewById(R.id.textView_nombre);
        textViewPrecio = findViewById(R.id.textView_precio);

    }

}
