package co.edu.unab.rey.carlos.crackapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import co.edu.unab.rey.carlos.crackapp.Producto;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listViewProductos;
    // private String productos[] = new String[5];
    private ArrayList<Producto> productos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productos = new ArrayList<>();
        productos.add(new Producto("caldo",3000.0));
        productos.add(new Producto("arepa",2000.0));
        productos.add(new Producto("chocolate",1500.0));
        productos.add(new Producto("pan", 1000.0));
        productos.add(new Producto("chorizo",1500.0));
        /*productos.add("arepa");
        productos.add("chocolate");
        productos.add("pan");
        productos.add("chorizo");*/

        listViewProductos = findViewById(R.id.listViewProducto);

        ArrayAdapter miAdaptador = new ArrayAdapter<Producto>(getApplicationContext(),android.R.layout.simple_list_item_1, productos);
        listViewProductos.setAdapter(miAdaptador);

        setTitle(R.string.Titulo);
        Producto miProducto = new Producto("caldo",3000.0);
    }
}
