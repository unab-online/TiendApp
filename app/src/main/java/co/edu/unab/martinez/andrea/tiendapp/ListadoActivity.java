package co.edu.unab.martinez.andrea.tiendapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class ListadoActivity extends AppCompatActivity {

    private RecyclerView rvProductos;
    private ArrayList<Producto>productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        productos = new ArrayList<>();

        this.getFakeData();
        this.AsociarElementos();

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplication()); // Manejador de mi RecyclerView si es lineal o en forma de grid

        ProductoAdapter miAdaptador = new ProductoAdapter(productos); // Creación del adaptador.

        rvProductos.setLayoutManager(manager);
        rvProductos.setAdapter(miAdaptador);


    }

    private void getFakeData(){

        if(productos==null){
            productos = new ArrayList<>();
        }
        productos.add(new Producto("Galleta",1500, "Galleta de chocolate con crema de maní"));
        productos.add(new Producto("Té helado",2000, "Té de limón - Nestea"));
        productos.add(new Producto("Brownie",1000, "Brownie relleno de arequipe"));
        productos.add(new Producto("Paleta",1300, "Paleta ALOHA sabor sandía"));
        productos.add(new Producto("Achira",1000, "Paquete de Achiras - Huila"));

    }

    private void AsociarElementos(){

        rvProductos = findViewById(R.id.rv_productos);
    }

}
