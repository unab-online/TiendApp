package co.edu.unab.leal.jakson.silviapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ListadoActivity extends AppCompatActivity {

    private RecyclerView recyclerViewProductos;
    private ArrayList<Producto> productos;
    private Toolbar toolbar;
    private FloatingActionButton btnFloating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        this.getFakeData();
        this.asociarProductos();

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplication());
        ProductoAdapater proAdpObj = new ProductoAdapater(productos, new ProductoAdapater.onItemClicListener() {
            @Override
            public void onItemClick(Producto producto, int posicion) {
                Toast.makeText(getApplicationContext(), "tap en: " + producto.getNombre(), Toast.LENGTH_LONG).show();
            }
        });

        recyclerViewProductos.setLayoutManager(manager);
        recyclerViewProductos.setAdapter(proAdpObj);

        btnFloating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "tap ", Toast.LENGTH_SHORT).show();
                Snackbar.make(view, "tap", Snackbar.LENGTH_SHORT).show();
            }
        });

    }

    private void getFakeData() {

        if (productos == null) productos = new ArrayList<>();

        productos.add(new Producto("Jabon johnson & johnson", 6.55, "solo usar despues de estar mojado"));
        productos.add(new Producto("CD's de musica", 11.99, "la pinto y la coloreo"));
        productos.add(new Producto("Esclavo color chocolate N°6", 1.1, "desde somalia"));
        productos.add(new Producto("Brazzers premium por un mes", 21, "2X1 solo para solteros"));
        productos.add(new Producto("200 usd en rappicreditos", 15, "bono de xhamster"));
        productos.add(new Producto("Ducha de baño", 8.72, "enchapada en oro golfi"));

    }

    private void asociarProductos() {

        recyclerViewProductos = findViewById(R.id.recyclerView_productos);
        btnFloating = findViewById(R.id.btn_floating);

    }

}
