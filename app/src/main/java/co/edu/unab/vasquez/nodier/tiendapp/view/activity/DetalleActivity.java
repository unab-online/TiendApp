package co.edu.unab.vasquez.nodier.tiendapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import co.edu.unab.vasquez.nodier.tiendapp.R;
import co.edu.unab.vasquez.nodier.tiendapp.model.entity.Producto;

public class DetalleActivity extends AppCompatActivity {

    private TextView txvNombre, txvPrecio, txvDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        // se puede hacer antes del set content view
        //recibimos los datos
        Bundle misDatos = getIntent().getExtras();
        Producto miProducto = (Producto) misDatos.getSerializable("producto");

        this.asociarElementos();
        txvNombre.setText(miProducto.getNombre());
        txvPrecio.setText("$"+miProducto.getPrecio());
        txvDescripcion.setText(miProducto.getDescripcion());

        setTitle("Detalle "+miProducto.getNombre());

    }

    private void asociarElementos(){
        txvNombre = findViewById(R.id.txvNombre);
        txvPrecio =findViewById(R.id.txvPrecio);
        txvDescripcion = findViewById(R.id.txvDesc);
    }

}
