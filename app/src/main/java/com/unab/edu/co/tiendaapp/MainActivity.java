package com.unab.edu.co.tiendaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listaProductos;
    //private String productos [] =new String[5];
    private ArrayList<Productos> productos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productos = new ArrayList<Productos>();
        productos.add(new Productos( "Asus VivoBook Max","Compudator gamer lleno de calidad, imagen a un muy buen precio.",2900000));
        productos.add(new Productos( "Mouse Gamer", "Mouse que te hara entrar en el mundo de los videojuegos ",64500));
        productos.add(new Productos( "Arduino UNO", "Da tus primeros pasos en el mundo de la robotica", 27999));
        productos.add(new Productos( "USB 32GB","Lleva toda tu informacion a donde vayas",18000));
        productos.add(new Productos( "Disco Duro 50 GB", "Amplia el almacenamiento y lleva mas información.",170000));
        productos.add(new Productos( "Teclado Gamer", " Sumergete en el mundo de los videojuegos",200000));
        productos.add(new Productos( "Diadema Beat","Escuche tu musica favorita con la mejor calidad." ,195000));

        listaProductos = findViewById(R.id.listaProductos);

        ArrayAdapter miAdaptador = new ArrayAdapter<Productos >(getApplicationContext(), R.layout.item_producto, productos);
        listaProductos.setAdapter(miAdaptador);

        setTitle("TiendaApp");

        listaProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                Productos miProducto = productos.get(position);

                Productos miProducto = (Productos) parent.getItemAtPosition(position);

                Intent intencion = new Intent(MainActivity.this,ActivityDetalle.class);
                intencion.putExtra("producto",miProducto);
                startActivity(intencion);

                Toast.makeText(getApplicationContext(),"Hice tap " + miProducto.getNombre() ,Toast.LENGTH_LONG).show();

            }
        });
    }
}
