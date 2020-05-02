package co.edu.unab.toloza.cesar.tiendapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import co.edu.unab.toloza.cesar.tiendapp.model.entity.Producto;
import co.edu.unab.toloza.cesar.tiendapp.R;

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
        productos.add(new Producto("PC Asus", 2000.0));
        productos.add(new Producto("Disco Duro", 500.0));
        productos.add(new Producto("Memoria USB", 100.0));
        productos.add(new Producto("Mouse", 50.0));
        productos.add(new Producto("Teclado", 80.0));
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
        ArrayAdapter adapter = new ArrayAdapter(getBaseContext(), R.layout.item_producto, productos);
        lvProductos.setAdapter(adapter);
        setTitle(R.string.txt_listado);

        lvProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Producto producto = productos.get(position);
                Producto producto =(Producto) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Hice tap " + producto.getNombre(), Toast.LENGTH_SHORT).show();
                Intent intentDetalle = new Intent(MainActivity.this, DetalleActivity.class);
                intentDetalle.putExtra("producto", producto);
                startActivity(intentDetalle);
            }
        });
    }
}
