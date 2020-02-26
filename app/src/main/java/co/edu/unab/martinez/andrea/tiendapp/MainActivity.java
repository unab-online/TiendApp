package co.edu.unab.martinez.andrea.tiendapp;

import androidx.annotation.ArrayRes;
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

        lvProductos = findViewById(R.id.lv_productos);
        productos = new ArrayList<>();

        productos.add(new Producto("Galleta",1500));
        productos.add(new Producto("Té helado",2000));
        productos.add(new Producto("Brownie",1000));
        productos.add(new Producto("Paleta",1300));
        productos.add(new Producto("Achira",1000));

        ArrayAdapter adaptador = new ArrayAdapter<Producto>(getBaseContext(),android.R.layout.simple_list_item_1,productos);
        lvProductos.setAdapter(adaptador);

        setTitle(R.string.txt_tituloList);

    }
}
