package co.edu.unab.saavedra.juan.tiendapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ListadoActivity extends AppCompatActivity {

    private RecyclerView rvProductos;
    private ArrayList<Producto> productos;

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

    }

    private void asociarElementos(){

        rvProductos = findViewById(R.id.rv_productos);
    }
}
