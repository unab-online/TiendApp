package co.edu.unab.tiendapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class ListadoActivity extends AppCompatActivity {
private RecyclerView rvProductos;
private ArrayList<Producto> productos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);


        SharedPreferences misPreferencias= getSharedPreferences(getString(R.string.misDatos),MODE_PRIVATE );

        // segundo parametro, que pasa si no he creado ese dato (por defecto devuelve falso)
        Boolean logueado=misPreferencias.getBoolean("logueado", false );
        String usuario=misPreferencias.getString("usuario", "");

        Toast.makeText(getApplicationContext(), "usuario"+usuario, Toast.LENGTH_LONG).show();

        //el this no es necesario
        this.getFakeData();
        this.AsociarElementos();

        //Debemos crear un nuevo archivo, siempre que trabajamos con un Recyclerview

        //Agragamos el manejador, si creamos un LinearLayout o un Grid
        RecyclerView.LayoutManager manager = new GridLayoutManager(getApplication(),2);
       // ProductoAdapter miAdaptador= new ProductoAdapter(productos);

        ProductoAdapter miAdaptador = new ProductoAdapter(productos, new ProductoAdapter.onItemClickListener() {
            @Override
            public void onItemClick(Producto miProducto, int posicion) {
                Toast.makeText(getApplicationContext(), "Hice click "+miProducto,Toast.LENGTH_SHORT).show();
            }
        });

        rvProductos.setLayoutManager(manager);
        rvProductos.setAdapter(miAdaptador);

    }

    private void getFakeData(){
        if(productos==null){
            productos= new ArrayList<>();
        }
        Producto p1= new Producto("Pc Asus",1800000);
        Producto p2= new Producto("Disco duro",250000);
        Producto p3= new Producto("Memoria USB",18000);
        Producto p4= new Producto("Mouse",78000);
        productos.add(p1);
        productos.add(p2);
        productos.add(p3);
        productos.add(p4);
    }

    private void AsociarElementos(){

        rvProductos=findViewById(R.id.rv_productos);
    }

}
