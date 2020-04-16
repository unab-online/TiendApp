package co.edu.unab.celis.sergio.tiendapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListadoActivity extends AppCompatActivity {

    private RecyclerView rvProductos;
    private List<Producto> productos;
    private ProductoDAO productoDAO;

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
            Intent intent = new Intent(ListadoActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }

        String usuario = misPreferencias.getString("usuario","Vacío");

        Toast.makeText(this,"Bienvenido "+ usuario, Toast.LENGTH_LONG).show();

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplication());

        ProductoAdapter miAdaptador = new ProductoAdapter(productos, new ProductoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Producto miProducto, int posicion) {
                Producto miroducto = (Producto)(productos.get(posicion));

                Intent mostrarInformacion = new Intent(getApplication(), InfoActivity.class);
                mostrarInformacion.putExtra("info", miroducto);
                startActivity(mostrarInformacion);

                Toast.makeText(getApplicationContext(),"Hice click "+miroducto, Toast.LENGTH_SHORT).show();
                productoDAO.eliminar(miProducto);
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

    private void getFakeData(){

        productos = productoDAO.obtenerTodos();
        if(productos.size()==0){
            productoDAO.agregar(new Producto("PC Asus", 2000));
            productoDAO.agregar(new Producto("Disco Duro", 500));
            productoDAO.agregar(new Producto("Memoria USB", 100));
            productoDAO.agregar(new Producto("Mouse", 50));
            productoDAO.agregar(new Producto("Teclado", 80));

            productos = productoDAO.obtenerTodos();
        }
    }

    private void asociarElementos(){
        rvProductos = findViewById(R.id.rvProductos);
    }
}
