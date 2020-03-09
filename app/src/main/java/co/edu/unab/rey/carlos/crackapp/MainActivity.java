package co.edu.unab.rey.carlos.crackapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import co.edu.unab.rey.carlos.crackapp.Producto;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listViewProductos;
    // private String productos[] = new String[5];
    private ArrayList<Producto> productos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*productos.add("arepa");
        productos.add("chocolate");
        productos.add("pan");
        productos.add("chorizo");*/

        listViewProductos = findViewById(R.id.listViewProducto);

        ArrayAdapter miAdaptador = new ArrayAdapter<Producto>(getApplicationContext(),R.layout.item_producto, productos);
        listViewProductos.setAdapter(miAdaptador);

        setTitle(R.string.Titulo);
        listViewProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Producto miProducto = productos.get(position);

                Producto miProducto = (Producto) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),"apachuraste "+miProducto.getNombre() , Toast.LENGTH_LONG).show();

                Intent miIntension = new Intent(getApplication(),Detalle.class); //esta la intension de ir a la otra actividad, resive la actividad donde está y la clase a donde va

                miIntension.putExtra("productos", productos.get(position));

                startActivity(miIntension); //haga la intension
            }
        });


                //Producto miProducto = new Producto("caldo",3000.0);
    }
}