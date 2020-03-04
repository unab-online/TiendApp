package co.edu.unab.leal.jakson.silviapp;

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

    private ListView listViewProductos;
    private ArrayList <Producto> productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productos = new ArrayList <Producto> ();
        productos.add(new Producto ("jabon johnson & johnson", 6.55,"solo usar despues de estar mojado"));
        productos.add(new Producto("cd's de musica", 11.99, "la pinto y coloreo"));
        productos.add(new Producto("esclavo color chocolate N°6", 1.1, "desde somalia"));
        productos.add(new Producto("brazzers premium por un mes", 21, "2X1 solo para solteros"));
        productos.add(new Producto("200 usd en rappicreditos", 15,"bono de xhamster"));
        productos.add(new Producto("ducha de baño",8.72, "enchapada en oro golfi"));

        listViewProductos = findViewById(R.id.listView_productos);

        ArrayAdapter miA = new ArrayAdapter<Producto>(getBaseContext(), R.layout.item_productos, productos);

        listViewProductos.setAdapter(miA);
        setTitle(R.string.titulo);

        listViewProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(), "tap ", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, DetalleActivity.class);
                i.putExtra("objeto", productos.get(position));
                startActivity(i);

            }
        });

    }

}
