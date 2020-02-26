package co.edu.unab.toloza.cesar.tiendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvProductos;
    private ArrayList<Producto> productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productos = new ArrayList<>();
        productos.add(new Producto("PC Asus", 2000.0));
        productos.add(new Producto("Disco Duro", 500.0));
        productos.add(new Producto("Memoria USB", 100.0));
        productos.add(new Producto("Mouse", 50.0));
        productos.add(new Producto("Teclado", 80.0));
        /*productos.add("PC Asus");
        productos.add("Disco Duro");
        productos.add("Memoria USB");
        productos.add("Mouse");
        productos.add("Teclado");*/

        lvProductos = findViewById(R.id.lv_productos);
        ArrayAdapter adapter = new ArrayAdapter(getBaseContext(), android.R.layout.simple_list_item_1, productos);
        lvProductos.setAdapter(adapter);
        setTitle(R.string.txt_listado);
    }
}
