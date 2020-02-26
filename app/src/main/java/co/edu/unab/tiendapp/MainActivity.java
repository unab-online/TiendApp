package co.edu.unab.tiendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private ListView lvProductos;
private ArrayList<String>productos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       productos= new ArrayList<>();
       Producto p1= new Producto("Pc Asus",1800000);
        Producto p2= new Producto("Disco duro",250000);
        Producto p3= new Producto("Memoria USB",18000);
        Producto p4= new Producto("Mouse",78000);
        productos.add(p1.toString());
       productos.add(p2.toString());
       productos.add(p3.toString());
       productos.add(p4.toString());

       lvProductos= findViewById(R.id.lv_productos);

        ArrayAdapter miAdaptador= new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, productos);
        lvProductos.setAdapter(miAdaptador);

        setTitle(R.string.txt_titulo);
    }
}
