package co.edu.unab.celis.sergio.tiendapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListadoActivity extends AppCompatActivity {

    private RecyclerView rvProductos;
    private List<Producto> productos;
    private ProductoDAO productoDAO;
    private ProductoAdapter miAdaptador;
    private Button btnNuevo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        BaseDatos bd = BaseDatos.obtenerInstancia(this);
        productoDAO = bd.productoDAO();

        this.getData();
        this.asociarElementos();

        SharedPreferences misPreferencias = getSharedPreferences(getString(R.string.misDatos), MODE_PRIVATE);

        Boolean logueado = misPreferencias.getBoolean("logueado", false);

        if(!logueado){
            Intent intent = new Intent(ListadoActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }

        String usuario = misPreferencias.getString("usuario","Vacío");

        Toast.makeText(this,"Bienvenido "+ usuario, Toast.LENGTH_LONG).show();

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplication());

        miAdaptador = new ProductoAdapter(productos, new ProductoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Producto miProducto, int posicion) {

                Producto miroducto = (Producto)(productos.get(posicion));

                Intent mostrarInformacion = new Intent(getApplication(), EditarActivity.class);
                mostrarInformacion.putExtra("info", miroducto);
                startActivity(mostrarInformacion);
                Toast.makeText(getApplicationContext(),"Hice click " + miProducto, Toast.LENGTH_SHORT).show();
                /*getData();
                miAdaptador.setProductos(productos);
                miAdaptador.notifyDataSetChanged();*/
            }
        });

        rvProductos.setLayoutManager(manager);
        rvProductos.setAdapter(miAdaptador);

    }

    public void cerrarSesion (View vista){
        SharedPreferences misPreferencias = getSharedPreferences("tiendapp", MODE_PRIVATE);
        SharedPreferences.Editor miEditor = misPreferencias.edit();
        miEditor.putBoolean("logueado", false);
        miEditor.putString("usuario","");
        //miEditor.clear();
        //miEditor.remove("logueado");
        miEditor.apply();

        Intent i = new Intent(ListadoActivity.this,LoginActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onResume(){
        super.onResume();
        getData();
        miAdaptador.setProductos(productos);
        miAdaptador.notifyDataSetChanged();
    }

    public void nuevoProducto (View vista){
        Intent i = new Intent(ListadoActivity.this, AgregarActivity.class);
        startActivity(i);
    }

    private void getData(){

        productos = productoDAO.obtenerTodos();

        if(productos.size()==0){
            productoDAO.agregar(new Producto("PC Asus", "Color negro",200));
            productoDAO.agregar(new Producto("Disco Duro", "2 Teras",50));
            productoDAO.agregar(new Producto("Memoria USB", "Color azul",100));
            productoDAO.agregar(new Producto("Mouse", "Inalámbrico",75));
            productoDAO.agregar(new Producto("Teclado", "Teclado gamer", 80));

            productos = productoDAO.obtenerTodos();
        }
    }

    private void asociarElementos(){
        rvProductos = findViewById(R.id.rvProductos);
        btnNuevo = findViewById(R.id.btnNuevo);
    }
}
