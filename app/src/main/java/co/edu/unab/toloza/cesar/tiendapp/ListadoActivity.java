package co.edu.unab.toloza.cesar.tiendapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

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

        SharedPreferences misPreferencias = getSharedPreferences(getString(R.string.preferencias), MODE_PRIVATE);

        Boolean logueado = misPreferencias.getBoolean("logueado", false);
        String user = misPreferencias.getString("user", "");

        if(logueado){
            Toast.makeText(getApplicationContext(), "Bienvenido " + user, Toast.LENGTH_LONG ).show();
        }

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplication());
        ProductoAdapter miProductoAdapter = new ProductoAdapter(productos, new ProductoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Producto producto, int position) {
                Toast.makeText(getApplication(), "Hice click " + producto, Toast.LENGTH_SHORT).show();
            }
        });
        rvProductos.setLayoutManager(manager);
        rvProductos.setAdapter(miProductoAdapter);


    }

    private void getFakeData(){

        if(productos == null){
            productos = new ArrayList<>();
        }
        productos.add(new Producto("PC Asus", 2000.0));
        productos.add(new Producto("Disco Duro", 500.0));
        productos.add(new Producto("Memoria USB", 100.0));
        productos.add(new Producto("Mouse", 50.0));
        productos.add(new Producto("Teclado", 80.0));
        for (int i = 0; i < productos.size(); i++){
            productos.get(i).setDescripcion("Descripcion " + (i+1));
        }
    }
    private void AsociarElementos(){
        rvProductos = findViewById(R.id.rv_productos);
    }
}
