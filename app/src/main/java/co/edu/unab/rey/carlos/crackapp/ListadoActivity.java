package co.edu.unab.rey.carlos.crackapp;

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
    private Button btnAgregar;
    private List<Producto> productos;
    private ProductoAdapter miAdaptador;
    private ProductoDAO productoDAO;

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
        String usuario = misPreferencias.getString("usuario", "nonname");
        Toast.makeText(this, "Bienvenido Usuario "+usuario, Toast.LENGTH_LONG).show();
        RecyclerView.LayoutManager manager = new GridLayoutManager(getApplication(),1);

        miAdaptador = new ProductoAdapter(productos, new ProductoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Producto miProducto, int posicion) {
                Toast.makeText(getApplicationContext(), "tap en: " + miProducto.getNombre(), Toast.LENGTH_LONG).show();
                productoDAO.borrar(miProducto);
                onResume();
            }
        });

        /*miAdaptador = new ProductoAdapter((ArrayList<Producto>) productos, new ProductoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Producto miProducto, int posicion) {
                Toast.makeText(getApplicationContext(), "Hice Click", Toast.LENGTH_LONG).show();
                productoDAO.borrar(miProducto);
                getData();
                miAdaptador.setProductos(productos);
                miAdaptador.notifyDataSetChanged();
            }
        });*/
        rvProductos.setLayoutManager(manager);
        rvProductos.setAdapter(miAdaptador);
//------------------------------------------------------------
       /* btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),FormularioAgregar.class);
                startActivity(in);

            }
        });

        */
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
        miAdaptador.setProductos(productos);
        miAdaptador.notifyDataSetChanged();
    }

    public void cerrarSecion(View vista){
        SharedPreferences misPreferencias = getSharedPreferences(getString(R.string.misDatos), MODE_PRIVATE);
        SharedPreferences.Editor miEditor = misPreferencias.edit();
        miEditor.clear();
        miEditor.apply();
        Intent in = new Intent(ListadoActivity.this, LoginActivity.class);
        startActivity(in);
        finish();
    }



    private void getData(){

        productos = productoDAO.obtenerTodos();
        if (productos.size()==0) {

            productoDAO.agregar(new Producto("caldo", 3000.0, "de la region, bien santanderiano, con huevo de gallina puesto hoy"));
            productoDAO.agregar(new Producto("arepa", 2000.0, "arepa de maiz pelao, de la ultima cosecha"));
            productoDAO.agregar(new Producto("chocolate", 1500.0, "hecho con el cacao de san vicente "));
            productoDAO.agregar(new Producto("pan", 1000.0, "de la esquina, no alcanzó para mas"));
            productoDAO.agregar(new Producto("chorizo", 1500.0, "saludable 100%"));

            productos = productoDAO.obtenerTodos();
        }
    }

    private void AsociarElementos(){
        rvProductos = findViewById(R.id.rv_productos);
    }
}
