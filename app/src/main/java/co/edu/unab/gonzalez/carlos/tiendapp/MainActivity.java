package co.edu.unab.gonzalez.carlos.tiendapp;

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
    private ListView lvproductos;
    //private String productos[];
    private ArrayList<Producto> productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Producto PC = new Producto("PC asus", "computador gamer", 1800000);
        Producto PC1 = new Producto("PC asus", "computador gamer", 1800000);
        Producto PC2 = new Producto("PC asus", "computador gamer", 1800000);
        Producto PC3 = new Producto("PC asus", "computador gamer", 1800000);
        Producto PC4 = new Producto("PC asus", "computador gamer", 1800000);
        Producto PC5 = new Producto("PC asus", "computador gamer", 1800000);
        Producto PC6 = new Producto("PC asus", "computador gamer", 1800000);
        Producto PC7 = new Producto("PC asus", "computador gamer", 1800000);


        productos  = new ArrayList<>();
        productos.add(new Producto("disco duro", "DDH 500G", 200000));
        productos.add(PC);
        productos.add(PC1);
        productos.add(PC2);
        productos.add(PC3);
        productos.add(PC4);
        productos.add(PC5);
        productos.add(PC6);
        productos.add(PC7);
        productos.add(PC);
        productos.add(PC);
        productos.add(PC);
        productos.add(PC);
        productos.add(PC1);
        productos.add(PC2);
        productos.add(PC3);
        productos.add(PC4);
        productos.add(PC5);
        productos.add(PC6);
        productos.add(PC7);
        productos.add(PC);
        productos.add(PC);
        productos.add(PC);
        productos.add(PC);
        productos.add(PC1);
        productos.add(PC2);
        productos.add(PC3);
        productos.add(PC4);
        productos.add(PC5);
        productos.add(PC6);
        productos.add(PC7);
        productos.add(PC);
        productos.add(PC);
        productos.add(PC);

        lvproductos = findViewById(R.id.lv_productos2);

        final ArrayAdapter miAdaptador = new ArrayAdapter<Producto>(getBaseContext(),R.layout.item_producto, productos);

        lvproductos.setAdapter(miAdaptador);

        setTitle(R.string.titulolistaprod);

        lvproductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Producto miProducto = productos.get(position);
                Producto miProducto = (Producto) miAdaptador.getItem(position);
                Intent miIntencion = new Intent(getApplicationContext(),Descripcion.class);
                miIntencion.putExtra("prod",  miProducto);

                startActivity(miIntencion);

                Toast.makeText(getApplicationContext(),"hice tap "+miProducto.getNombre(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
