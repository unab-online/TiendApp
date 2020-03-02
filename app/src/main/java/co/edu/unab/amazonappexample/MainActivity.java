package co.edu.unab.amazonappexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
    private ListView lvProductos;
    private ArrayList<Producto>productos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        productos= new ArrayList<>();
        Producto p1= new Producto("Pc Asus",1800000);
        Producto p2= new Producto("Disco duro",250000);
        Producto p3= new Producto("Memoria USB",18000);
        Producto p4= new Producto("Mouse",78000);

        productos.add(p1);
        productos.add(p2);
        productos.add(p3);
        productos.add(p4);

        productos.add(p1);
        productos.add(p2);
        productos.add(p3);
        productos.add(p4);


        productos.add(p1);
        productos.add(p2);
        productos.add(p3);
        productos.add(p4);


        lvProductos= findViewById(R.id.lv_productos);

        ArrayAdapter miAdaptador= new ArrayAdapter<Producto>(getApplicationContext(),R.layout.item_producto, productos);

        lvProductos.setAdapter(miAdaptador);
        setTitle(R.string.txt_titulo);

        lvProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Producto p= productos.get(position);
                Intent intent= new Intent(MainActivity.this,DetailActivity.class);
                intent.putExtra("datos",p);
                startActivity(intent);
            }
        });

    }
}
