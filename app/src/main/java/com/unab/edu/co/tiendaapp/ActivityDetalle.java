package com.unab.edu.co.tiendaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ActivityDetalle extends AppCompatActivity {

    private TextView txtNombre;
    private TextView txtDescri;
    private TextView txtPrecio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);


        Bundle misDatos = getIntent().getExtras();
        Productos miProducto = (Productos) misDatos.getSerializable("producto");

        this.AsociarElementos();
        txtNombre.setText(miProducto.getNombre());
        txtDescri.setText(miProducto.getDescripcion());
        txtPrecio.setText("$" + miProducto.getPrecio());

        setTitle("Detalle " + miProducto.getNombre());
    }

    private void AsociarElementos(){

        txtNombre = findViewById(R.id.textViewNombre);
        txtDescri = findViewById(R.id.textViewDescri);
        txtPrecio = findViewById(R.id.textViewPrecio);

    }
}
