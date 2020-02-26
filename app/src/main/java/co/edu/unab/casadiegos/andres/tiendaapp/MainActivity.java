package co.edu.unab.casadiegos.andres.tiendaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ListView lvProductos;
    //private String productos[] = new String[5];
    private ArrayList<Producto> productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productos = new ArrayList<>();
        productos.add(new Producto("PC Asus", 2000));
        productos.add(new Producto("Disco Duro",5000));
        productos.add(new Producto("Memoria USB",8000));
        productos.add(new Producto("Mouse",4000));
        productos.add(new Producto("PC Asus",2000));
        productos.add(new Producto("Teclado",5500));

        lvProductos = findViewById(R.id.lv_productos);

        ArrayAdapter miAdaptador = new ArrayAdapter<Producto>(getApplicationContext(),android.R.layout.simple_list_item_1,productos);

        lvProductos.setAdapter(miAdaptador);

        setTitle(R.string.txt_listado);
    }
}
