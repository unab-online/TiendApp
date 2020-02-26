package co.edu.unab.melon.cristian.tiendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvProductos;
    //private String prodcutos[] = new String[5];
    private ArrayList<Producto> productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Producto lista = new Producto("Pc Asus",2000d);
        Producto lista2 = new Producto("Disco Duro",100.0);
        Producto lista3 = new Producto("Memoria USB",20.0);
        Producto lista4 = new Producto("Mouse",10.0);
        Producto lista5 = new Producto("Teclado",12.0);

        productos = new ArrayList<>();
        productos.add(lista);
        productos.add(lista2);
        productos.add(lista3);
        productos.add(lista4);
        productos.add(lista5);

        lvProductos = findViewById(R.id.lv_productos);

        ArrayAdapter miAdaptador = new ArrayAdapter<Producto>(getBaseContext(),android.R.layout.simple_list_item_1, productos);

        lvProductos.setAdapter(miAdaptador);

        setTitle(R.string.txt_listado);

    }
}
