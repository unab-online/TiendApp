package co.edu.unab.tiendapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListadoActivity extends AppCompatActivity {

    private RecyclerView rvProductos;
    private List<Producto> productos;
    private ProductoDAO productoDAO;
    private ProductoAdapter miAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        BaseDatos bd = BaseDatos.obtenerInstancia(this);
        productoDAO = bd.productoDAO();

        this.getData();
        this.AsociarElementos();

        SharedPreferences misPreferencias = getSharedPreferences(getString(R.string.misDatos), MODE_PRIVATE);

        Boolean logueado = misPreferencias.getBoolean("logueado", false);

        if(!logueado){
            Intent in = new Intent(ListadoActivity.this, LoginActivity.class);
            startActivity(in);
            finish();
        }

        String usuario = misPreferencias.getString("usuario", "");

        Toast.makeText(this, "Bienvenido Usuario "+usuario, Toast.LENGTH_LONG).show();

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplication());

        miAdaptador = new ProductoAdapter(productos, new ProductoAdapter.NombreDeInterface() {
            @Override
            public void metodoParaelItemClick(Producto miproducto, int posicion) {
                Toast.makeText(getApplicationContext(), "Hice click "+miproducto, Toast.LENGTH_LONG).show();
                productoDAO.borrar(miproducto);
                getData();
                miAdaptador.setProductos(productos);
                miAdaptador.notifyDataSetChanged();
            }
        });

        rvProductos.setLayoutManager(manager);
        rvProductos.setAdapter(miAdaptador);



    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
        miAdaptador.setProductos(productos);
        miAdaptador.notifyDataSetChanged();
    }

    public void irAgregarProducto(View vista){
        Intent i = new Intent(ListadoActivity.this, AgregarActivity.class);
        startActivity(i);
    }

    public void cerrarSesion(View vista){

        SharedPreferences misPreferencias = getSharedPreferences(getString(R.string.misDatos), MODE_PRIVATE);
        SharedPreferences.Editor miEditor = misPreferencias.edit();
//        miEditor.putBoolean("logueado", false);
//        miEditor.putString("usuario", "");

        miEditor.clear();
//        miEditor.remove("logueado");
//        miEditor.remove("usuario");
        miEditor.apply();
        Intent i = new Intent(ListadoActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    private void getData(){

        productos = productoDAO.obtenerTodos();
        if(productos.size()==0){

            productoDAO.agregar(new Producto("PC Asus", 2000));
            productoDAO.agregar(new Producto("Disco Duro", 500));
            productoDAO.agregar(new Producto("Memoria USB", 100));
            productoDAO.agregar(new Producto("Mouse", 50));
//            productoDAO.agregar(new Producto("Teclado", 80));

            productos = productoDAO.obtenerTodos();
        }





    }

    private void AsociarElementos(){

        rvProductos = findViewById(R.id.rv_productos);
    }
}
