package co.edu.unab.tiendapp.forero.jesus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Descripcion_Producto extends AppCompatActivity {
private TextView titulo;
private TextView precio;
private TextView descripcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion__producto);
        titulo=findViewById(R.id.titulo);
        precio=findViewById(R.id.precio);
        descripcion=findViewById(R.id.descripcion);
        Bundle bundle = getIntent().getExtras();
        Producto miProducto = (Producto) bundle.getSerializable("Producto");
      titulo.setText(miProducto.getNombre());
      precio.setText(miProducto.getPrecio().toString());
      descripcion.setText(miProducto.getDescripcion());


    }
}
