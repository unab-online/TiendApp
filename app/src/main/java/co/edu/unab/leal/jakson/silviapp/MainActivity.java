package co.edu.unab.leal.jakson.silviapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listViewProductos;
    private ArrayList <Producto> productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Producto objProd = new Producto("primero", 2.0);

        productos = new ArrayList <Producto> ();
        productos.add(new Producto ("jabon johnson", 6.55));
        productos.add(new Producto("cd's de musica", 11.99));
        productos.add(new Producto("esclavo color canela N°6", 1.1));
        productos.add(new Producto("brazzers premium por un mes", 21));
        productos.add(new Producto("200 usd en rappicreditos", 15));
        productos.add(new Producto("ducha de baño",8.72));

        listViewProductos = findViewById(R.id.listView_productos);

        ArrayAdapter miA = new ArrayAdapter<Producto>(getBaseContext(), android.R.layout.simple_list_item_1, productos);

        listViewProductos.setAdapter(miA);
        setTitle(R.string.titulo);
    }
}
