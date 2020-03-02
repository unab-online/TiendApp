package co.edu.unab.casadiegos.andres.tiendaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class informacion_producto extends AppCompatActivity {

    private TextView txtNombre, txtPrecio, txtDescripcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_producto);

        Bundle data = getIntent().getExtras();


        Producto miProducto = (Producto) data.getSerializable("objeto");
        this.asociarElementos();

        txtNombre.setText("Nombre: "+  miProducto.getNombre());
        txtPrecio.setText("$"+  miProducto.getPrecio());
        txtDescripcion.setText("Descripción: "+  miProducto.getDescripcion());

    }

    private void asociarElementos()
    {
        txtNombre =findViewById(R.id.txt_Nombre);
        txtPrecio =findViewById(R.id.txt_Precio);
        txtDescripcion = findViewById(R.id.txt_Descripcion);
    }
}
