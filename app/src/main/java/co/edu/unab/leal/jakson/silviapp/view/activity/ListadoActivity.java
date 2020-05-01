package co.edu.unab.leal.jakson.silviapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import co.edu.unab.leal.jakson.silviapp.model.entity.Producto;
import co.edu.unab.leal.jakson.silviapp.model.repository.ProductoRepository;
import co.edu.unab.leal.jakson.silviapp.R;
import co.edu.unab.leal.jakson.silviapp.model.db.network.CallBackFirestore;

import co.edu.unab.leal.jakson.silviapp.view.adapter.ProductoAdapter;

public class ListadoActivity extends AppCompatActivity {

    private RecyclerView recyclerViewProductos;
    private List<Producto> productos;
    private Toolbar toolbar;
    private FloatingActionButton btnFloating;
    private ProductoAdapter adptadorObj;
    private ProductoRepository prodRepositorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        this.asociarElementos();

        SharedPreferences misPreferencias = getSharedPreferences(getString(R.string.misDatos), MODE_PRIVATE);
        Boolean logueado = misPreferencias.getBoolean("logueado", false);
        if(!logueado){
            Intent in = new Intent(ListadoActivity.this, LoginActivity.class);
            startActivity(in);
            finish();
        }

        prodRepositorio = new ProductoRepository(this);
        productos = new ArrayList<>();

        adptadorObj = new ProductoAdapter(productos, new ProductoAdapter.onItemClickListener() {
            @Override
            public void onItemClick(Producto producto, int posicion) {
                Toast.makeText(getApplicationContext(), "tap en: " + producto.getNombre(), Toast.LENGTH_LONG).show();
                Intent editarIntent = new Intent(ListadoActivity.this, EditarActivity.class);
                editarIntent.putExtra("producto", producto);
                startActivity(editarIntent);
            }
        });

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplication());
        recyclerViewProductos.setLayoutManager(manager);
        recyclerViewProductos.setAdapter(adptadorObj);
        recyclerViewProductos.setHasFixedSize(true);
        getDataFirestore();

        prodRepositorio.obtenerTodosFirestore(new CallBackFirestore<List<Producto>>() {
            @Override
            public void correcto(List<Producto> respuesta) {

            }
        });

        String usuario = misPreferencias.getString("usuario", "vacio");
        Toast.makeText(this, "Bienvenido Usuario "+usuario, Toast.LENGTH_LONG).show();

        btnFloating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "tap ", Toast.LENGTH_SHORT).show();
                Snackbar.make(view, "tap", Snackbar.LENGTH_SHORT).show();
            }
        });

    }//

    private void getDataFirestore(){
        prodRepositorio.obtenerTodosFirestore(new CallBackFirestore<List<Producto>>() {
            @Override
            public void correcto(List<Producto> respuesta) {
                adptadorObj.setProductos(respuesta);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        getDataFirestore();
        adptadorObj.setProductos(productos);
        adptadorObj.notifyDataSetChanged();
    }


    private void asociarElementos() {

        recyclerViewProductos = findViewById(R.id.recyclerView_productos);
        btnFloating = findViewById(R.id.btn_floating);

    }

    public void cerrarSesion(){

        SharedPreferences misPreferencias = getSharedPreferences(getString(R.string.misDatos), MODE_PRIVATE);
        SharedPreferences.Editor miEditor = misPreferencias.edit();
        miEditor.clear();
        miEditor.apply();
        Intent i = new Intent(ListadoActivity.this, LoginActivity.class);
        startActivity(i);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            cerrarSesion();
            return true;
        }
        if (id == R.id.add) {
            Intent i = new Intent(ListadoActivity.this, AgregarProductoActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
