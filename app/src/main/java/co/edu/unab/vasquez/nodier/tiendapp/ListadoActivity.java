package co.edu.unab.vasquez.nodier.tiendapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ListadoActivity extends AppCompatActivity {

    private RecyclerView rvProductos;
    private ArrayList<Producto> productos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        this.getFakeData();
        this.AsociarElementos();

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());

        //creación del adaptador
        ProductorAdapter miAdactador = new ProductorAdapter(productos); //cargar productos

        //siempre que

        //se una para recycler
        rvProductos.setLayoutManager(manager);
        rvProductos.setAdapter(miAdactador);
    }

    private void getFakeData(){

        if(productos==null)
        {
            productos = new ArrayList<>();
        }
        productos.add (new Producto("Disco Suro",200000.0));
        productos.add (new Producto("Memoria USB", 20000.0));
        productos.add (new Producto("Mouse", 15000.0));
        productos.add (new Producto("teclado", 25000.0));

        for (int i = 0;i< productos.size(); i++){
            productos.get(i).setDescripcion("Descripción "+(i+1));
        }
    }

    private void AsociarElementos(){

        rvProductos = findViewById(R.id.rv_productos);
    }
}
