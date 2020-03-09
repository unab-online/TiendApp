package co.edu.unab.diaz.javier.tiendapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListadoActivity extends AppCompatActivity {

    RecyclerView rvProductos;
    ArrayList<Producto> productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        this.getFakeData();
        this.AsociarElementos();

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplication());
        ProductoAdapter miAdaptador = new ProductoAdapter(productos, new ProductoAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(Producto miproducto, int posicion) {
                Toast.makeText(getApplicationContext(), "Hice-click"+miproducto, Toast.LENGTH_LONG).show();
            }
        });

        rvProductos.setLayoutManager(manager);
        rvProductos.setAdapter(miAdaptador);


    }

    public void getFakeData(){
        if(productos == null){
            productos = new ArrayList<>();
        }
        productos.add(new Producto("PC Asus", 2000));
        productos.add(new Producto("Disco Duro", 500));
        productos.add(new Producto("Memoria USB", 100));
        productos.add(new Producto("Mouse", 50));
        productos.add(new Producto("Teclado", 80));
        for (int i = 0; i < productos.size(); i++){
            productos.get(i).setDescripcion("Descripcion" + (i+1));
        }
    }

    private void AsociarElementos(){
        rvProductos = findViewById(R.id.rv_productos);
    }
}
