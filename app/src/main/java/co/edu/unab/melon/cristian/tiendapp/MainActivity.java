package co.edu.unab.melon.cristian.tiendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvProductos;
    //private String prodcutos[] = new String[5];
    private ArrayList<Producto> productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Producto lista = new Producto("Pc Asus",2000d);
        Producto lista2 = new Producto("Disco Duro",100.0);
        Producto lista3 = new Producto("Memoria USB",20.0);
        Producto lista4 = new Producto("Mouse",10.0);
        Producto lista5 = new Producto("Teclado",12.0);

        productos = new ArrayList<>();
        productos.add(lista);
        productos.add(lista2);
        productos.add(lista3);
        productos.add(lista4);
        productos.add(lista5);

        lvProductos = findViewById(R.id.lv_productos);

        ArrayAdapter miAdaptador = new ArrayAdapter<Producto>(getBaseContext(),R.layout.item_producto, productos);

        lvProductos.setAdapter(miAdaptador);

        setTitle(R.string.txt_listado);

        lvProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // atrapar texto de lista

                Producto miproducto = productos.get(position);
                Toast.makeText(getApplicationContext(), "tap "+ miproducto.getNombre(),Toast.LENGTH_SHORT).show();

               Intent intent = new Intent(getApplicationContext(), Tap.class);
               intent.putExtra("producto", miproducto);
               startActivity(intent);

            }
        });






    }
}
