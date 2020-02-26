package co.edu.unab.celis.sergio.tiendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvProductos;
    //private String productos[];
    private ArrayList<Producto> productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Producto pcAsus = new Producto("PcAsus",15.3);
        Producto discoDuro = new Producto("Disco Duro",17.2);
        Producto memoriaUSB = new Producto("Memoria USB",7.8);
        Producto mouse = new Producto("Mouse",10);
        Producto teclado = new Producto("Teclado",20.1);


        productos = new ArrayList<>();
        productos.add(pcAsus);
        productos.add(discoDuro);
        productos.add(memoriaUSB);
        productos.add(mouse);
        productos.add(teclado);

        lvProductos = findViewById(R.id.lvProductos);

        ArrayAdapter adaptador = new ArrayAdapter<Producto>(getBaseContext(),android.R.layout.simple_list_item_1, productos);

        lvProductos.setAdapter(adaptador);

        setTitle(R.string.txtListado);
    }
}
