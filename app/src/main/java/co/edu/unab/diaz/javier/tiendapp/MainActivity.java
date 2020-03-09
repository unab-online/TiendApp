package co.edu.unab.diaz.javier.tiendapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvProductos;
    private ArrayList<Producto> productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productos = new ArrayList<>();
        productos.add(new Producto("PC Asus", 2000));
        productos.add(new Producto("Disco Duro", 500));
        productos.add(new Producto("Memoria USB", 100));
        productos.add(new Producto("Mouse", 50));
        productos.add(new Producto("Teclado", 80));

        lvProductos = findViewById(R.id.lvProductos);

        ArrayAdapter miAdaptador = new ArrayAdapter<Producto>(getBaseContext(), R.layout.item_producto, productos);

        lvProductos.setAdapter(miAdaptador);

        setTitle(R.string.txtListado);

        lvProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Producto producto =(Producto) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Hice tap " + producto.getNombre(), Toast.LENGTH_SHORT).show();
                Intent intentDetalle = new Intent(MainActivity.this, DetalleActivity.class);
                intentDetalle.putExtra("producto", producto);
                startActivity(intentDetalle);
            }
        });
    }
}
