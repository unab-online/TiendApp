package co.edu.unab.rey.carlos.crackapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class ListadoActivity extends AppCompatActivity {

    private RecyclerView rvProductos;
    ArrayList<Producto> productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        this.getFakeData();
        this.AsociarElementos();

        RecyclerView.LayoutManager manager = new GridLayoutManager(getApplication(),1);

        ProductoAdapter miAdaptador = new ProductoAdapter(productos, new ProductoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Producto miProducto, int posicion) {
                Toast.makeText(getApplicationContext(),"Apachurré "+miProducto, Toast.LENGTH_LONG).show();
            }
        });


        rvProductos.setLayoutManager(manager);
        rvProductos.setAdapter(miAdaptador);

    }

    private void getFakeData(){
        productos = new ArrayList<Producto>();
        productos.add(new Producto("caldo",3000.0, "de la region, bien santanderiano, con huevo de gallina puesto hoy"));
        productos.add(new Producto("arepa",2000.0, "arepa de maiz pelao, de la ultima cosecha"));
        productos.add(new Producto("chocolate",1500.0, "hecho con el cacao de san vicente "));
        productos.add(new Producto("pan", 1000.0, "de la esquina, no alcanzó para mas"));
        productos.add(new Producto("chorizo",1500.0, "saludable 100%"));
    }

    private void AsociarElementos(){
        rvProductos = findViewById(R.id.rv_productos);
    }
}
