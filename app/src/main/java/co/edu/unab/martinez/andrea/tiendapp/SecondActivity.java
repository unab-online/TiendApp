package co.edu.unab.martinez.andrea.tiendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView txt_pName;
    private TextView txt_pPrice;
    private TextView txt_pDescrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle data = getIntent().getExtras();

        Producto miProducto = (Producto)data.getSerializable("producto");

        this.asociarElementos();

        txt_pName.setText(miProducto.getNombre());
        txt_pPrice.setText(String.valueOf(miProducto.getPrecio()));
        txt_pDescrip.setText(miProducto.getDescripcion());

        setTitle("Detalle "+miProducto.getNombre());
    }

    private void asociarElementos(){

        txt_pName = findViewById(R.id.txt_pName);
        txt_pPrice= findViewById(R.id.txt_pPrice);
        txt_pDescrip = findViewById(R.id.txt_pDescrip);
    }
}
