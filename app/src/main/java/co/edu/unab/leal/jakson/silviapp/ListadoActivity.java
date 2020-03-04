package co.edu.unab.leal.jakson.silviapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ListadoActivity extends AppCompatActivity {

    private RecyclerView recyclerViewProductos;
    private ArrayList<Producto> productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        this.getFakeData();
        this.asociarProductos();

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplication());
        ProductoAdapater proAdpObj = new ProductoAdapater (productos);
        recyclerViewProductos.setLayoutManager(manager);
        recyclerViewProductos.setAdapter(proAdpObj);

    }

    private void getFakeData() {

        if (productos == null) productos = new ArrayList<>();

        productos.add(new Producto("jabon johnson & johnson", 6.55, "solo usar despues de estar mojado"));
        productos.add(new Producto("cd's de musica", 11.99, "la pinto y coloreo"));
        productos.add(new Producto("esclavo color chocolate N°6", 1.1, "desde somalia"));
        productos.add(new Producto("brazzers premium por un mes", 21, "2X1 solo para solteros"));
        productos.add(new Producto("200 usd en rappicreditos", 15, "bono de xhamster"));
        productos.add(new Producto("ducha de baño", 8.72, "enchapada en oro golfi"));

    }

    private void asociarProductos() {

        recyclerViewProductos = findViewById(R.id.recyclerView_productos);

    }

}
