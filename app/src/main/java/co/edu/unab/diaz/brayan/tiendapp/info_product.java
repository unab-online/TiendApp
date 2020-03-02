package co.edu.unab.diaz.brayan.tiendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class info_product extends AppCompatActivity {

    private TextView txvNombre, txvPrecio, txvDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_product);

        this.asociarElementos();

        Bundle info = getIntent().getExtras();
        Producto miProducto = (Producto) info.getSerializable("producto");

        txvNombre.setText("Nombre: " + miProducto.getNombre());
        txvPrecio.setText("Precio: " + miProducto.getPrecio());
        txvDescripcion.setText("Descripción: " + miProducto.getDescripcion());
    }

    private void asociarElementos(){
        txvNombre = findViewById(R.id.txv_nombre);
        txvPrecio = findViewById(R.id.txv_precio);
        txvDescripcion = findViewById(R.id.txv_descripcion);
    }


}
