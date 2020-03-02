package co.edu.unab.tiendaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetalleActivity extends AppCompatActivity {

    private TextView txtNombre;
    private TextView txtDesc;
    private TextView txtPrecio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        Bundle misDatos = getIntent().getExtras();
        Productos miProducto = (Productos) misDatos.getSerializable("producto");



        this.asociarElemento();

        txtNombre.setText(miProducto.getNombre());
        txtPrecio.setText("$" + miProducto.getPrecio());
        txtDesc.setText(miProducto.getDescripcion());


        setTitle("Detalle" + miProducto.getNombre());
    }

    private void asociarElemento() {

        txtNombre = findViewById(R.id.txtTitulo);
        txtDesc = findViewById(R.id.txtDes);
        txtPrecio = findViewById(R.id.txtPrec);

    }
}
