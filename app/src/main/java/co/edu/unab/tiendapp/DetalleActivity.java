package co.edu.unab.tiendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetalleActivity extends AppCompatActivity {
   private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        Bundle misDatos= getIntent().getExtras();
        Producto miProducto=(Producto) misDatos.getSerializable("producto");
        asociarElementos();
        tv1.setText(miProducto.getNombre().toString());
        tv2.setText(miProducto.getDescripcion().toString());
        tv3.setText(String.valueOf(miProducto.getPrecio()).toString());


    }

    private void asociarElementos(){
        tv1=findViewById(R.id.txv_nombre);
        tv2=findViewById(R.id.txv_desc);
        tv3=findViewById(R.id.txv_precio);
    }
}
