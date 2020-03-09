package co.edu.unab.gonzalez.carlos.tiendapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class ListadoActivity extends AppCompatActivity {
    private RecyclerView rvProductos;
    private ArrayList<Producto> productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado2);

        this.getFakeData();
        this.AsociarElementos();

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplication());
        //RecyclerView.LayoutManager manager = new GridLayoutManager(getApplication(),2);

        ProductoAdapter miAdaptador = new ProductoAdapter(productos, new ProductoAdapter.interfaceescucghadorclick() {
            @Override
            public void metodoclickear(Producto miproducto, int posicion) {
                Toast.makeText(getApplicationContext(), "Hice clic en "+miproducto, Toast.LENGTH_SHORT).show();
            }
        });

        rvProductos.setLayoutManager(manager);
        rvProductos.setAdapter(miAdaptador);

        Toast.makeText(getApplicationContext(),"hice tap ", Toast.LENGTH_LONG).show();
    }
    private void getFakeData(){
        if (productos==null){
            productos = new ArrayList<>();
        }
        Producto PC = new Producto("PC asus", "computador gamer", 1800000);
        Producto PC1 = new Producto("PC asus", "computador gamer", 1800000);
        Producto PC2 = new Producto("memoria USB ", "memoria usb 4GB", 50000);
        Producto PC3 = new Producto("PC asus", "computador gamer", 1800000);
        Producto PC4 = new Producto("PC asus", "computador gamer", 1800000);
        Producto PC5 = new Producto("PC asus", "computador gamer", 1800000);
        Producto PC6 = new Producto("PC asus", "computador gamer", 1800000);
        Producto PC7 = new Producto("PC asus", "computador gamer", 1800000);


        productos  = new ArrayList<>();
        productos.add(new Producto("disco duro", "DDH 500G", 200000));
        productos.add(PC);
        productos.add(PC1);
        productos.add(PC2);
        productos.add(PC3);
        productos.add(PC4);
        productos.add(PC5);
        productos.add(PC6);
        productos.add(PC7);
        productos.add(PC);
        productos.add(PC);
        productos.add(PC);
        productos.add(PC);
        productos.add(PC1);
        productos.add(PC2);
        productos.add(PC3);
        productos.add(PC4);
        productos.add(PC5);
        productos.add(PC6);
        productos.add(PC7);
        productos.add(PC);
        productos.add(PC);
        productos.add(PC);
        productos.add(PC);
        productos.add(PC1);
        productos.add(PC2);
        productos.add(PC3);
        productos.add(PC4);
        productos.add(PC5);
        productos.add(PC6);
        productos.add(PC7);
        productos.add(PC);
        productos.add(PC);
        productos.add(PC);
    }
    private void AsociarElementos(){
        rvProductos = findViewById(R.id.lv_productos2);
    }
}
