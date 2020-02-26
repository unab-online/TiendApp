package co.edu.unab.vasquez.nodier.tiendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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


        lvProductos = findViewById(R.id.lv_productos); // Lo identificamos

        ArrayAdapter miAdaptador = new ArrayAdapter<Producto>(this, android.R.layout.simple_list_item_1,productos); //Normalmente es el mismo tipo de dato del arraylist, recibe 3 parámetros

        lvProductos.setAdapter(miAdaptador);

        setTitle(R.string.txt_listado);
    }



}
