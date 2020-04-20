package co.edu.unab.leal.jakson.silviapp;

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
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import java.util.List;

public class ListadoActivity extends AppCompatActivity {

    private RecyclerView recyclerViewProductos;
    private List<Producto> productos;
    private Toolbar toolbar;
    private FloatingActionButton btnFloating;
    private ProductoDAO productoDAO;
    private ProductoAdapater proAdpObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        BaseDatos bd = BaseDatos.obtenerInstancia(this);
        productoDAO = bd.productoDAO();

        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        this.cargarDatos();
        this.asociarElementos();

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
        proAdpObj = new ProductoAdapater(productos, new ProductoAdapater.onItemClicListener() {
            @Override
            public void onItemClick(Producto producto, int posicion) {
                Toast.makeText(getApplicationContext(), "tap en: " + producto.getNombre(), Toast.LENGTH_LONG).show();
                productoDAO.eliminar(producto);
                onResume();
            }
        });

        recyclerViewProductos.setLayoutManager(manager);
        recyclerViewProductos.setAdapter(proAdpObj);

        btnFloating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "tap ", Toast.LENGTH_SHORT).show();
                Snackbar.make(view, "tap", Snackbar.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarDatos();
        proAdpObj.setProductos(productos);
        proAdpObj.notifyDataSetChanged();
    }

    private void cargarDatos() {

        productos = productoDAO.obtenerTodos();
        if (productos.size()==0) {

            productoDAO.agregar(new Producto("Jabon johnson & johnson", 6.55, "solo usar despues de estar mojado"));
            productoDAO.agregar(new Producto("CD's de musica", 11.99, "la pinto y la coloreo"));
            productoDAO.agregar(new Producto("Esclavo color chocolate N°6", 1.1, "desde somalia"));
            productoDAO.agregar(new Producto("Brazzers premium por un mes", 21, "2X1 solo para solteros"));
            productoDAO.agregar(new Producto("200 usd en rappicreditos", 15, "bono de xhamster"));
            productoDAO.agregar(new Producto("Ducha de baño", 8.72, "enchapada en oro golfi"));

            productos = productoDAO.obtenerTodos();

        }
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
