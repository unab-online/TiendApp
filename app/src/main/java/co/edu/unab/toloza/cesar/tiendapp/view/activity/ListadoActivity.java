package co.edu.unab.toloza.cesar.tiendapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.edu.unab.toloza.cesar.tiendapp.model.db.network.CallBackFirestore;
import co.edu.unab.toloza.cesar.tiendapp.model.entity.Producto;
import co.edu.unab.toloza.cesar.tiendapp.view.adapter.ProductoAdapter;
import co.edu.unab.toloza.cesar.tiendapp.model.repository.ProductoRepository;
import co.edu.unab.toloza.cesar.tiendapp.R;

public class ListadoActivity extends AppCompatActivity {

    private RecyclerView rvProductos;
    private List<Producto> productos;
    private ProductoRepository productoRepository;
    private ProductoAdapter miAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        this.AsociarElementos();

        SharedPreferences misPreferencias = getSharedPreferences(getString(R.string.preferencias), MODE_PRIVATE);

        Boolean logueado = misPreferencias.getBoolean("logueado", false);

        if(!logueado){
            Intent in = new Intent(ListadoActivity.this, LoginActivity.class);
            startActivity(in);
            finish();
        }

        productoRepository = new ProductoRepository(this);

        productos = new ArrayList<>();

        miAdapter = new ProductoAdapter(productos, new ProductoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Producto producto, int position) {
                Toast.makeText(getApplication(), "Hice click " + producto, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListadoActivity.this, EditarActivity.class);
                intent.putExtra("producto", producto);
                startActivity(intent);
            }
        });

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplication());
        rvProductos.setLayoutManager(manager);
        rvProductos.setAdapter(miAdapter);
        rvProductos.setHasFixedSize(true);

        this.getData();

        productoRepository.escucharTodosFirestore(new CallBackFirestore<List<Producto>>() {
            @Override
            public void correcto(List<Producto> respuesta) {
                miAdapter.setProductos(respuesta);
            }
        });

        String user = misPreferencias.getString("user", "");
        Toast.makeText(getApplicationContext(), "Bienvenido " + user, Toast.LENGTH_LONG ).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    public void  CerrarSesion(View view){
        SharedPreferences misPreferencias = getSharedPreferences(getString(R.string.preferencias), MODE_PRIVATE);
        SharedPreferences.Editor miEditor = misPreferencias.edit();
        //miEditor.putBoolean("logueado", false);
        //miEditor.putString("user", "");
        miEditor.clear();
        //miEditor.remove("logueado");
        //miEditor.remove("user");
        miEditor.apply();
        Intent in = new Intent(ListadoActivity.this, LoginActivity.class);
        startActivity(in);
        finish();
    }

    public void  AgregarNuevo(View view){
        Intent in = new Intent(ListadoActivity.this, AgregarActivity.class);
        startActivity(in);
    }

    private void getData(){
        /*productos = productoDAO.obtenerTodos();
        if(productos.size() == 0){
            productoDAO.agregar(new Producto("PC Asus", 2000.0));
            productoDAO.agregar(new Producto("Disco Duro", 500.0));
            productoDAO.agregar(new Producto("Memoria USB", 100.0));
            productoDAO.agregar(new Producto("Mouse", 50.0));
            productoDAO.agregar(new Producto("Teclado", 80.0));

            productos = productoDAO.obtenerTodos();
        }*/
        productoRepository.obtenerTodosFirestore(new CallBackFirestore<List<Producto>>() {
            @Override
            public void correcto(List<Producto> respuesta) {
                miAdapter.setProductos(respuesta);
            }
        });
    }
    private void AsociarElementos(){
        rvProductos = findViewById(R.id.rv_productos);
    }
}
