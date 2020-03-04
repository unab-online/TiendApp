package co.edu.unab.diaz.brayan.tiendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
        productos.add(new Producto("Pc Asus", 2000));
        productos.add(new Producto("Disco duro", 2000));
        productos.add(new Producto("Memoria USB", 5));
        productos.add(new Producto("Mouse", 10));
        productos.add(new Producto("Teclado", 500));

        lvProductos = findViewById(R.id.lv_productos);

        ArrayAdapter miAdaptador = new ArrayAdapter<Producto>(getBaseContext(), R.layout.txt_layout, productos);

        lvProductos.setAdapter(miAdaptador);

        setTitle(R.string.txt_listado);

        lvProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Producto miProducto = productos.get(position);
                Producto miProducto = (Producto) parent.getItemAtPosition(position);

                Toast.makeText(getApplicationContext(), "Tap " + miProducto.getNombre(), Toast.LENGTH_LONG).show();

                Intent intendInfoProducto = new Intent(getApplication(), info_product.class);
                intendInfoProducto.putExtra("producto", miProducto);
                startActivity(intendInfoProducto);
            }
        });


    }
}
