package co.edu.unab.diaz.brayan.tiendapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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

        //Persistencia Local
        SharedPreferences misPreferencias = getSharedPreferences(getString(R.string.misDatos), 0);
        Boolean logueado = misPreferencias.getBoolean("logueado", false);
        String usuario = misPreferencias.getString("usuario", "vacio");

        Toast.makeText(this, "Bienvenido Usuario "+usuario, Toast.LENGTH_LONG).show();

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());

        ProductoAdapter miAdaptador =  new ProductoAdapter(productos, new ProductoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Producto producto, int position) {

                Intent intentInfo = new Intent(getApplication(), info_product.class);
                intentInfo.putExtra("producto", producto);
                startActivity(intentInfo);
                Toast.makeText(getApplicationContext(),"Hice click" + producto, Toast.LENGTH_LONG).show();
            }
        });


        rvProductos.setLayoutManager(manager);
        rvProductos.setAdapter(miAdaptador);

        //rvProductos.setOn
    }

    private void getFakeData(){
        if(productos==null){
            productos = new ArrayList<>();
        }
        productos.add(new Producto("Pc Asus", 2000));
        productos.add(new Producto("Disco duro", 2000));
        productos.add(new Producto("Memoria USB", 5));
        productos.add(new Producto("Mouse", 10));
        productos.add(new Producto("Teclado", 500));
        productos.add(new Producto("Pc Asus", 2000));
        productos.add(new Producto("Disco duro", 2000));
        productos.add(new Producto("Memoria USB", 5));
        productos.add(new Producto("Mouse", 10));
        productos.add(new Producto("Teclado", 500));
        productos.add(new Producto("Pc Asus", 2000));
        productos.add(new Producto("Disco duro", 2000));
        productos.add(new Producto("Memoria USB", 5));
        productos.add(new Producto("Mouse", 10));
        productos.add(new Producto("Teclado", 500));

        for(int i=0; i<productos.size(); i++){

        }
    }

    private void AsociarElementos(){
        rvProductos = findViewById(R.id.rv_productos);
    }
}
