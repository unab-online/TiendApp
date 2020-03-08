package co.edu.unab.tiendapp.forero.jesus;

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
    private ArrayList<Producto> productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        productos = new ArrayList<>();
        productos.add(new Producto("PC asus",2600.0));
        productos.add(new Producto("GTX 1080",1500.0));
        productos.add(new Producto("Disco Duro",150.0));
        productos.add(new Producto("Mouse",80.0));
        productos.add(new Producto("Teclado",80.0));
        productos.add(new Producto("PC asus",2600.0));
        productos.add(new Producto("GTX 1080",1500.0));
        productos.add(new Producto("Disco Duro",150.0));
        productos.add(new Producto("Mouse",80.0));
        productos.add(new Producto("Teclado",80.0));
        productos.add(new Producto("PC asus",2600.0));
        productos.add(new Producto("GTX 1080",1500.0));
        productos.add(new Producto("Disco Duro",150.0));
        productos.add(new Producto("Mouse",80.0));
        productos.add(new Producto("Teclado",80.0));

        lvProductos = findViewById(R.id.lv_productos);


        ArrayAdapter miAdaptador = new ArrayAdapter<Producto>(getBaseContext(),R.layout.item_producto, productos);
        lvProductos.setAdapter(miAdaptador);
        setTitle("Listado de Productos");
        lvProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Producto miProducto = productos.get(position);
                //Toast.makeText(getApplicationContext(),"Hice Tap "+miProducto.getNombre(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplication(),Descripcion_Producto.class);
                intent.putExtra("Producto",miProducto);
                startActivity(intent);
            }
        });

    }
}
