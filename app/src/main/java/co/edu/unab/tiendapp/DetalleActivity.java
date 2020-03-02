package co.edu.unab.tiendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetalleActivity extends AppCompatActivity {

    private TextView txvNombre;
    private TextView txvPrecio;
    private TextView txvDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        Bundle misDatos = getIntent().getExtras();
        Producto miProducto = (Producto) misDatos.getSerializable("producto");

        this.asociarElementos();

        txvNombre.setText(miProducto.getNombre());
        txvPrecio.setText("$"+miProducto.getPrecio());
        txvDesc.setText(miProducto.getDescripcion());

        setTitle("Detalle "+miProducto.getNombre());

    }

    private void asociarElementos(){

        txvNombre = findViewById(R.id.txv_nombre);
        txvPrecio = findViewById(R.id.txv_precio);
        txvDesc = findViewById(R.id.txv_desc);

    }
}
