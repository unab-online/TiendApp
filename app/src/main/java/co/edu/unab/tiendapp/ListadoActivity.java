package co.edu.unab.tiendapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

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

        SharedPreferences misPreferencias = getSharedPreferences(getString(R.string.misDatos), MODE_PRIVATE);

        Boolean logueado = misPreferencias.getBoolean("logueado", false);
        String usuario = misPreferencias.getString("usuario", "");

        Toast.makeText(this, "Bienvenido Usuario "+usuario, Toast.LENGTH_LONG).show();

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplication());

        ProductoAdapter miAdaptador = new ProductoAdapter(productos, new ProductoAdapter.NombreDeInterface() {
            @Override
            public void metodoParaelItemClick(Producto miproducto, int posicion) {
                Toast.makeText(getApplicationContext(), "Hice click "+miproducto, Toast.LENGTH_LONG).show();
            }
        });

        rvProductos.setLayoutManager(manager);
        rvProductos.setAdapter(miAdaptador);



    }

    private void getFakeData(){

        if(productos==null){
            productos = new ArrayList<>();
        }

        productos.add(new Producto("PC Asus", 2000));
        productos.add(new Producto("Disco Duro", 500));
        productos.add(new Producto("Memoria USB", 100));
        productos.add(new Producto("Mouse", 50));
        productos.add(new Producto("Teclado", 80));

        for (int i=0;i<productos.size(); i++){
            productos.get(i).setDescripcion("Desc "+(i+1));
        }

    }

    private void AsociarElementos(){

        rvProductos = findViewById(R.id.rv_productos);
    }
}
