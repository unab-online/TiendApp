package co.edu.unab.tiendaapp;

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
    //private String productos [] =new String[5];
    private ArrayList <Productos> productos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productos = new ArrayList<>();

        productos.add(new Productos( "Llaves Locke & Key", "Llaves reales que te otorgan habilidades especiales",  29000));
        productos.add(new Productos( "Plutonio", "Pequeñas cantidades de plutonio para sus experimentos caseros ",64500));
        productos.add(new Productos( "Ak 47", "El vendedor no se hace responsable por el mal uso del producto",27999));
        productos.add(new Productos( "Pierna de Cerdo", "Deliciosa pierna de cerdo de 25 Kg ", 18000));
        productos.add(new Productos( "Obsidiana", "Cuarzo oscuro para aumentar la energia positiva del aura corporal y espiritual", 17000));
        productos.add(new Productos( "Souls", "Espiritus chocarreros esclavos para uso personal",0000));
        productos.add(new Productos( "Cerdo sin una pierna", "Delicioso cerdo que antes tenia 4 piernas",195000));


        lvProductos = findViewById(R.id.lvProductos); // identificamos el listview

        ArrayAdapter miAdaptador = new ArrayAdapter<Productos >(getApplicationContext(), R.layout.item_producto, productos);
        lvProductos.setAdapter(miAdaptador);

        setTitle("TiendaApp");

        //Cuando hago tap en un elemento en especifico

        lvProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               // Productos miProducto = productos.get(position);
                Productos miProducto = (Productos) parent.getItemAtPosition(position);

                Intent intencion = new Intent(MainActivity.this, DetalleActivity.class);
                intencion.putExtra("producto", miProducto);
                startActivity(intencion);


                Toast.makeText(getApplicationContext(), "Seleccionaste " + miProducto.getNombre(), Toast.LENGTH_LONG).show();
            }
        });


    }
}
