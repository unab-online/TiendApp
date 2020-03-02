package co.edu.unab.vasquez.nodier.tiendapp;

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
    //private String productos[] = new String[5]; // Así declaramos el arreglo, debríamos darle tamaño
    //private ArrayList <String> productos;// Declaramos un arrayList que reciba Strings; SIEMPRE LO DEBEMOS INICIALIZAR
    private ArrayList <Producto> productos;// Declaramos un arrayList que reciba Strings; SIEMPRE LO DEBEMOS INICIALIZAR
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productos = new ArrayList<>(); // Inicializamos el ArrayList
        //Agregamos valores
        /*productos.add("Pc Asus");
        productos.add("Disco Duro");
        productos.add("Memoria USB");
        productos.add("Mouse");
        productos.add("Teclado");*/

        productos.add(new Producto("Pc Asus",50000.0));
        productos.add (new Producto("Disco Suro",200000.0));
        productos.add (new Producto("Memoria USB", 20000.0));
        productos.add (new Producto("Mouse", 15000.0));
        productos.add (new Producto("teclado", 25000.0));

        for (int i = 0;i< productos.size(); i++){
            productos.get(i).setDescripcion("Descripción "+(i+1));
        }


        lvProductos = findViewById(R.id.lv_productos); // Lo identificamos
        //El listView siempre necesita (recibe un adaptador)
        ArrayAdapter miAdaptador = new ArrayAdapter<Producto>(this, R.layout.item_producto,productos); //Normalmente es el mismo tipo de dato del arraylist, recibe 3 parámetros
        lvProductos.setAdapter(miAdaptador);
        setTitle(R.string.txt_listado);

        lvProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /*parent = Adaptador
            lista = textView
            Position = posicion del item
            id = indentificador*/
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Producto miProducto = (Producto) parent.getItemAtPosition(position);//Mostrar en nombre del item seleccionado, MANERA 1
//                Producto miProducto = productos.get(position);//Mostrar en nombre del item seleccionado, MANERA 2
                Toast.makeText(getApplicationContext(),"Hice tap en: "+ miProducto.getNombre(),Toast.LENGTH_SHORT).show();

                //mostrar información en otra actividad
                Intent intencion = new Intent(MainActivity.this,DetalleActivity.class);
                intencion.putExtra("producto",miProducto);
                startActivity(intencion);

            }
        });

    }



}
