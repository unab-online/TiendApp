package co.edu.unab.rey.carlos.crackapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Detalle extends AppCompatActivity {

    private TextView textViewNombre;
    private TextView textViewPrecio;
    private TextView textViewDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        Bundle misDatos = getIntent().getExtras();
        Producto miProducto = (Producto) misDatos.getSerializable("productos");

        this.asociarElementos();
        textViewNombre.setText(miProducto.getNombre());
        textViewPrecio.setText(""+miProducto.getPrecio());
        textViewDescripcion.setText(miProducto.getDescripcion());

        setTitle("Detalle "+miProducto.getNombre());
    }
private void asociarElementos(){
        textViewNombre = findViewById(R.id.textViewNombre);
        textViewPrecio = findViewById(R.id.textViewPrecio);
        textViewDescripcion = findViewById(R.id.textViewDescripcion);
    }

}
