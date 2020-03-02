package co.edu.unab.martinez.andrea.tiendapp;

import androidx.annotation.ArrayRes;
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

        lvProductos = findViewById(R.id.lv_productos);
        productos = new ArrayList<>();

        productos.add(new Producto("Galleta",1500, "Galleta de chocolate con crema de maní"));
        productos.add(new Producto("Té helado",2000, "Té de limón - Nestea"));
        productos.add(new Producto("Brownie",1000, "Brownie relleno de arequipe"));
        productos.add(new Producto("Paleta",1300, "Paleta ALOHA sabor sandía"));
        productos.add(new Producto("Achira",1000, "Paquete de Achiras - Huila"));

        ArrayAdapter adaptador = new ArrayAdapter<Producto>(getBaseContext(),R.layout.item_producto,productos); // El que maneja que información atrapa y pone en una vista.

        lvProductos.setAdapter(adaptador);

        setTitle(R.string.txt_tituloList);

        lvProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Producto miProducto = (Producto) parent.getItemAtPosition(position);

                //Toast.makeText(getApplicationContext(),"Hice tap "+ productos.get(position).getNombre(), Toast.LENGTH_SHORT).show();  //2da forma miProducto.getNombre()

                Intent miIntencion = new Intent(getApplication(), SecondActivity.class);
                miIntencion.putExtra("producto",miProducto);
                startActivity(miIntencion);
            }
        });

    }
}
