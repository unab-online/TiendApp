package co.edu.unab.tiendapp;

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

        productos =  new ArrayList<>();
        productos.add(new Producto("PC Asus", 2000));
        productos.add(new Producto("Disco Diro", 500));
        productos.add(new Producto("Memoria USB", 100));
        productos.add(new Producto("Mouse", 50));
        productos.add(new Producto("Teclado", 80));


        lvProductos = findViewById(R.id.lv_productos);

        ArrayAdapter miAdaptador = new ArrayAdapter<Producto>(getBaseContext(),android.R.layout.simple_list_item_1, productos);

        lvProductos.setAdapter(miAdaptador);

        setTitle(R.string.txt_listado);


    }
}
