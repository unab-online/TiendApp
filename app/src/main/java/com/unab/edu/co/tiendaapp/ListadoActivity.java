package com.unab.edu.co.tiendaapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListadoActivity extends AppCompatActivity {

    private RecyclerView rv_producto;
    ArrayList<Productos> productos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        this.getFakeData();
        this.AsociarElementos();

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());

        ProductoAdapter miAdaptador = new ProductoAdapter(productos);

        rv_producto.setLayoutManager(manager);
        rv_producto.setAdapter(miAdaptador);


    }

    private void getFakeData(){
        if(productos == null){
            productos = new ArrayList<>();
        }

        productos.add(new Productos( "Asus VivoBook Max","Compudator gamer lleno de calidad, imagen a un muy buen precio.",2900000));
        productos.add(new Productos( "Mouse Gamer", "Mouse que te hara entrar en el mundo de los videojuegos ",64500));
        productos.add(new Productos( "Arduino UNO", "Da tus primeros pasos en el mundo de la robotica", 27999));
        productos.add(new Productos( "USB 32GB","Lleva toda tu informacion a donde vayas",18000));
        productos.add(new Productos( "Disco Duro 50 GB", "Amplia el almacenamiento y lleva mas información.",170000));
        productos.add(new Productos( "Teclado Gamer", " Sumergete en el mundo de los videojuegos",200000));
        productos.add(new Productos( "Diadema Beat","Escuche tu musica favorita con la mejor calidad." ,195000));

    }


    private void AsociarElementos(){

        rv_producto = findViewById(R.id.rv_productos);

    }
}
