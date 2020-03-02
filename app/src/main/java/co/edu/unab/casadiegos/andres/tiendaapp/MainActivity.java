package co.edu.unab.casadiegos.andres.tiendaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.security.ProtectionDomain;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ListView lvProductos;
    //private String productos[] = new String[5];
    private ArrayList<Producto> productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productos = new ArrayList<>();
        productos.add(new Producto("PC Asus", 2000));
        productos.add(new Producto("Disco Duro",5000));
        productos.add(new Producto("Memoria USB",8000));
        productos.add(new Producto("Mouse",4000));
        productos.add(new Producto("PC Asus",2000));
        productos.add(new Producto("Teclado",5500));

        lvProductos = findViewById(R.id.lv_productos);

        ArrayAdapter miAdaptador = new ArrayAdapter<Producto>(getApplicationContext(),R.layout.item_producto,productos);

        lvProductos.setAdapter(miAdaptador);

        setTitle(R.string.txt_listado);


        lvProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


 //               Producto miProducto = productos.get(position);
                Producto miProducto = (Producto) parent.getItemAtPosition(position);
 //               Toast.makeText(getApplicationContext(), "Hice Tap " + miProducto.getNombre(), Toast.LENGTH_LONG).show();

                Intent miIntencion = new Intent(getApplication(), informacion_producto.class);

                miIntencion.putExtra("objeto",miProducto);
                startActivity(miIntencion);
            }
        });
    }
}
