package co.edu.unab.saavedra.juan.tiendapp;

import androidx.appcompat.app.AppCompatActivity;
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
    private ProductoRepository productoRepository;

    Button btnCerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        this.asociarElementos();

        SharedPreferences misPreferencias = getSharedPreferences(getString(R.string.misDatos), MODE_PRIVATE);

        Boolean logueado = misPreferencias.getBoolean("logueado", false);

        if(!logueado){
            Intent intentListado = new Intent(ListadoActivity.this, LoginActivity.class);
            startActivity(intentListado);
            finish();
        }

        productoRepository = new ProductoRepository(this);

        productos = new ArrayList<>();

        miAdaptador = new ProductoAdapter(productos, new ProductoAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(Producto miproducto, int posicion) {
                Toast.makeText(getApplicationContext(), "Hice click " + miproducto, Toast.LENGTH_LONG).show();
                Intent i = new Intent(ListadoActivity.this, EditarActivity.class);
                i.putExtra("producto", miproducto);
                startActivity(i);
            }
        });

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplication());
        rvProductos.setLayoutManager(manager);
        rvProductos.setAdapter(miAdaptador);
        rvProductos.setHasFixedSize(true);

        getData();

        productoRepository.obtenerTodosFirestore(new CallBackFirestore<List<Producto>>() {
            @Override
            public void correcto(List<Producto> respuesta) {
                miAdaptador.setProductos(respuesta);
            }
        });

        productoRepository.escucharTodos(new CallBackFirestore<List<Producto>>() {
            @Override
            public void correcto(List<Producto> respuesta) {

            }
        });

        String usuario = misPreferencias.getString("usuario", "");

        Toast.makeText(this, "Bienvenido "+usuario, Toast.LENGTH_LONG).show();

    }

    private void actualizarDatos(){
        getData();
        miAdaptador.setProductos(productos);
        //APLICAR CAMBIOS, MIRA SI HAY ALGUNO Y MODIFICA
        miAdaptador.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        actualizarDatos();
    }

    private void getData(){

        productoRepository.obtenerTodosFirestore(new CallBackFirestore<List<Producto>>() {
            @Override
            public void correcto(List<Producto> respuesta) {
                miAdaptador.setProductos(respuesta);
                miAdaptador.notifyDataSetChanged();
            }
        });

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

    public void nuevoProducto(View vista){
        Intent i = new Intent(ListadoActivity.this, NuevoProductoActivity.class);
        startActivity(i);
    }
}
