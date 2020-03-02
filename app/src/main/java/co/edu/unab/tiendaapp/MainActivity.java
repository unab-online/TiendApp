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

        productos = new ArrayList<Productos>();
        productos.add(new Productos( "Llavero Usb","8 Gigas", 30000));
        productos.add(new Productos( "Bolso ", " Elegante  en colores champaña y Dorado",50000));
        productos.add(new Productos( "portatil", "i5 de 8 de ram  250 de disco solido",1300000));
        productos.add(new Productos( "mouse", "motivos animados inalambrico",35000));
        productos.add(new Productos( "apuntador ", "larga duracion recargable",17000));
        productos.add(new Productos( "protector de teclado ", "en silicona transparente",15000));
        productos.add(new Productos( "limpiador de pantallas lcd", "de 250 centimetros cubicos  con atomizador y pañitos limpiadore",8000));

        lvProductos = findViewById(R.id.lvProductos);

        ArrayAdapter miAdaptador = new ArrayAdapter<Productos >(getApplicationContext(), R.layout.item_producto, productos);
        lvProductos.setAdapter(miAdaptador);


        setTitle("TiendaApp");

        lvProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Productos miproducto = productos.get(position);
                Intent intencion =new Intent(MainActivity.this,DetalleActivity.class);
                intencion.putExtra("producto", miproducto);
                startActivity(intencion);


                Toast.makeText(getApplicationContext(),"hice click " + miproducto.getNombre() , Toast.LENGTH_LONG).show();

            }
        });
    }
}
