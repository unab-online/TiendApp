package co.edu.unab.diaz.javier.tiendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class ListadoActivity extends AppCompatActivity {

    private RecyclerView rvProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        this.getFakeData();
        this.AsociarElementos();

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplication());
        ProductoAdapter miAdaptador = new ProductoAdapter(productos);

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
            productos.get(i).setDescription("Descripcion" + (i+1));
        }
    }

    private void AsociarElementos(){
        rvProductos = findViewById(R.id.rv_productos);
    }
}
