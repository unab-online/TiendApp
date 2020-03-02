package co.edu.unab.tiendaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DetalleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        Bundle misDatos= getIntent().getExtras();


        Productos miproducto=(Productos) misDatos.getSerializable("producto");


    }
}
