package co.edu.unab.rey.carlos.crackapp;

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
    private ArrayList<Producto> productos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        asociarProductos();

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

    public void asociarProductos(){
        productos = new ArrayList <Producto> ();
        productos.add(new Producto ("jabon johnson & johnson", 6.55,"solo usar despues de estar mojado"));
        productos.add(new Producto("cd's de musica", 11.99, "la pinto y coloreo"));
        productos.add(new Producto("esclavo color chocolate N°6", 1.1, "desde somalia"));
        productos.add(new Producto("brazzers premium por un mes", 21, "2X1 solo para solteros"));
        productos.add(new Producto("200 usd en rappicreditos", 15,"bono de xhamster"));
        productos.add(new Producto("ducha de baño",8.72, "enchapada en oro golfi"));
    }
}
