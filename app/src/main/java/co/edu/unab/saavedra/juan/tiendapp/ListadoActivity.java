package co.edu.unab.saavedra.juan.tiendapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class ListadoActivity extends AppCompatActivity {

    private RecyclerView rvProductos;
    private List<Producto> productos;
    private ProductoDAO productoDAO;

    Button btnCerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        BaseDatos bd = BaseDatos.obtenerInstancia(this);
        productoDAO = bd.productoDAO();

        this.getFakeData();
        this.asociarElementos();

        SharedPreferences misPreferencias = getSharedPreferences(getString(R.string.misDatos), MODE_PRIVATE);

        Boolean logueado = misPreferencias.getBoolean("logueado", false);

        if(!logueado){
            Intent intentListado = new Intent(ListadoActivity.this, LoginActivity.class);
            startActivity(intentListado);
            finish();
        }

        String usuario = misPreferencias.getString("usuario", "");

        Toast.makeText(this, "Bienvenido "+usuario, Toast.LENGTH_LONG).show();

        RecyclerView.LayoutManager manager = new GridLayoutManager(getApplication(),2);

        ProductoAdapter miAdaptador = new ProductoAdapter(productos, new ProductoAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(Producto miproducto, int posicion) {
                Toast.makeText(getApplicationContext(), "Hice click "+miproducto, Toast.LENGTH_LONG).show();
            }
        });


        rvProductos.setLayoutManager(manager);
        rvProductos.setAdapter(miAdaptador);


    }

    private void getFakeData(){

        productos = productoDAO.obtenerTodos();
        if(productos.size()==0){
            Producto p1 = new Producto("Pc Asus", 12.5);
            p1.setDescripcion("Computador marca Asus");
            productos.add(p1);
            Producto p2 = new Producto("Airpods pro",250);
            p2.setDescripcion("Audifonos Apple");
            productos.add(p2);
            Producto p3 = new Producto("Iphone 11",700);
            p3.setDescripcion("Celular Apple");
            productos.add(p3);
            Producto p4 = new Producto("Monitor Lg",300);
            p4.setDescripcion("Monitor de 30' marca LG");
            productos.add(p4);
            Producto p5 = new Producto("Cable Iphone",30);
            p5.setDescripcion("Cable de corriente para Iphone");
            productos.add(p5);

            productoDAO.agregar(p1);
            productoDAO.agregar(p2);
            productoDAO.agregar(p3);
            productoDAO.agregar(p4);
            productoDAO.agregar(p5);

            productos = productoDAO.obtenerTodos();
        }

    }

    private void asociarElementos(){

        rvProductos = findViewById(R.id.rv_productos);
    }

    public void cerrarSesion(View vista){

        SharedPreferences misPreferencias = getSharedPreferences("tiendapp", MODE_PRIVATE);
        SharedPreferences.Editor miEditor = misPreferencias.edit();
        miEditor.putBoolean("logueado", false);
        miEditor.putString("usuario", "");
        //miEditor.clear(); //BORRA TODAS LAS PREFERENCIAS
        miEditor.apply();

        Intent i = new Intent(ListadoActivity.this, LoginActivity.class);
        startActivity(i);
        finish();

    }
}
