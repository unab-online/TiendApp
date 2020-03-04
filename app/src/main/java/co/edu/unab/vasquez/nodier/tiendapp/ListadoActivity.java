package co.edu.unab.vasquez.nodier.tiendapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class ListadoActivity extends AppCompatActivity {

    private RecyclerView rvProductos; // elemento para asociar el recicler view
    private ArrayList <Producto> productos; // elemento para guardar el elemento

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        this.getFakeData();
        this.asociarElementos();

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplication());

        ProductoAdapter miAdaptador = new ProductoAdapter(productos);

        rvProductos.setLayoutManager(manager);
        rvProductos.setAdapter(miAdaptador);

    }

    private void getFakeData(){

        if(productos==null){
            productos = new ArrayList<>();
        }
        productos.add(new Producto("Pc Asus",50000.0));
        productos.add (new Producto("Disco Duro",200000.0));
        productos.add (new Producto("Memoria USB", 20000.0));
        productos.add (new Producto("Mouse", 15000.0));
        productos.add (new Producto("teclado", 25000.0));

        for (int i = 0;i< productos.size(); i++){
            productos.get(i).setDescripcion("Descripción "+(i+1));
        }

    }

    private void asociarElementos(){
        rvProductos = findViewById(R.id.rv_productos);
    }

}
