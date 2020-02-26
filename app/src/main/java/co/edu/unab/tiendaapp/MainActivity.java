package co.edu.unab.tiendaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvProductos;
    //private String productos [] =new String[5];
    private ArrayList <Productos> productos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productos = new ArrayList<Productos>();
        productos.add(new Productos( "Llaves Locke & Key", 29000));
        productos.add(new Productos( "Plutonio", 64500));
        productos.add(new Productos( "Ak 47", 27999));
        productos.add(new Productos( "Pierna de Cerdo", 18000));
        productos.add(new Productos( "Obsidiana", 17000));
        productos.add(new Productos( "Souls", 0000));
        productos.add(new Productos( "Cerdo sin una pierna", 195000));

        lvProductos = findViewById(R.id.lvProductos);

        ArrayAdapter miAdaptador = new ArrayAdapter<Productos >(getApplicationContext(), android.R.layout.simple_list_item_1, productos);
        lvProductos.setAdapter(miAdaptador);

        setTitle("TiendaApp");
    }
}
