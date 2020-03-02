package co.edu.unab.melon.cristian.tiendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Tap extends AppCompatActivity {

    private TextView Nombre, Descripcion, Precio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap);

        Bundle datos = getIntent().getExtras();

        Producto miproducto = (Producto) datos.getSerializable("producto");


        Nombre = findViewById(R.id.txtname);
        Descripcion = findViewById(R.id.txtdes);
        Precio = findViewById(R.id.txtprecio);

        Nombre.setText(""+miproducto.getNombre()+"");
        Descripcion.setText(""+miproducto.getDescripción()+"");
        Precio.setText(""+miproducto.getPrecio()+"");


    }
}
