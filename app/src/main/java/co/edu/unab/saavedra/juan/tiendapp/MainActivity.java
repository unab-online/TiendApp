package co.edu.unab.saavedra.juan.tiendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private ListView lvProductos;
    private ArrayList<Producto> productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productos = new ArrayList<>();
        Producto p1 = new Producto("Pc Asus", 12.5);
        p1.setDescripcion("Computador marca Asus");
        productos.add(p1);
        Producto p2 = new Producto("Airpods pro",250);
        p2.setDescripcion("Audifonos Apple");
        productos.add(p2);
        Producto p3 = new Producto("Iphone 11",700);
        p3.setDescripcion("Celular Apple");
        productos.add(p3);
        Producto p4 = new Producto("Monitor Lg",300);
        p4.setDescripcion("Monitor de 30' marca LG");
        productos.add(p4);
        Producto p5 = new Producto("Cable Iphone",30);
        p5.setDescripcion("Cable de corriente para Iphone");
        productos.add(p5);

        lvProductos = findViewById(R.id.lv_productos);

        ArrayAdapter miAdaptador;
        miAdaptador = new ArrayAdapter<Producto>(getBaseContext(), R.layout.item_producto, productos);

        lvProductos.setAdapter(miAdaptador);

        setTitle(R.string.txt_listado);

        lvProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //OTRA FORMA
                //parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Hice Tap "+productos.get(position).getNombre(), Toast.LENGTH_SHORT).show();

                Intent miIntencion = new Intent(getApplication(), item_descripcion.class);
                Producto item = productos.get(position);
                miIntencion.putExtra("Item", item);
                startActivity(miIntencion);
            }
        });
    }
}
