package co.edu.unab.diaz.brayan.tiendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvProductos;
    //private String productos[] = new String[0];
    private ArrayList<Producto> productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productos = new ArrayList<>();
        productos.add(new Producto("Pc Asus", 2000));
        productos.add(new Producto("Disco duro", 2000));
        productos.add(new Producto("Memoria USB", 5));
        productos.add(new Producto("Mouse", 10));
        productos.add(new Producto("Teclado", 500));

        lvProductos = findViewById(R.id.lv_productos);

        ArrayAdapter miAdaptador = new ArrayAdapter<Producto>(getBaseContext(), android.R.layout.simple_list_item_1, productos);

        lvProductos.setAdapter(miAdaptador);

        setTitle(R.string.txt_listado);
    }
}
