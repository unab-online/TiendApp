package co.edu.unab.saavedra.juan.tiendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class item_descripcion extends AppCompatActivity {

    private TextView txtNombreItem;
    private TextView txtPrecio;
    private TextView txtDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle misDatos = getIntent().getExtras();
        Producto item = (Producto) misDatos.getSerializable("Item");

        setContentView(R.layout.activity_item_descripcion);

        txtNombreItem = findViewById(R.id.txt_nombreItem);
        txtDescripcion = findViewById(R.id.txt_descripcion);
        txtPrecio = findViewById(R.id.txt_precio);

        txtNombreItem.setText(item.getNombre());
        txtPrecio.setText(Double.toString(item.getPrecio()));
        txtDescripcion.setText(item.getDescripcion());

    }
}
