package co.edu.unab.leal.jakson.silviapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
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

        FirebaseFirestore freefire = FirebaseFirestore.getInstance();
        freefire.collection("productos").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                productos = new ArrayList<>();
                if (queryDocumentSnapshots != null) {
                    for (QueryDocumentSnapshot doc: queryDocumentSnapshots) {
                        Producto objProducto = doc.toObject(Producto.class);
                        objProducto.setId(doc.getId());
                        productos.add(objProducto);
                    }
                }
                if (proAdpObj!=null){
                    proAdpObj.setProductos(productos);
                    proAdpObj.notifyDataSetChanged();
                }
            }
        });
        freefire.collection("productos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    productos = new ArrayList<>();
                    for (QueryDocumentSnapshot doc: task.getResult()) {
                        Producto objProducto = doc.toObject(Producto.class);
                        objProducto.setId(doc.getId());
                        productos.add(objProducto);
                    }
                    RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplication());
                    proAdpObj = new ProductoAdapater(productos, new ProductoAdapater.onItemClicListener() {
                        @Override
                        public void onItemClick(Producto producto, int posicion) {
                            Toast.makeText(getApplicationContext(), "tap en: " + producto.getNombre(), Toast.LENGTH_LONG).show();
                            Intent editarIntent = new Intent(ListadoActivity.this, EditarActivity.class);
                            editarIntent.putExtra("producto", producto);
                            startActivity(editarIntent);

                            //productoDAO.eliminar(producto);
                            //onResume();
                        }
                    });

                    recyclerViewProductos.setLayoutManager(manager);
                    recyclerViewProductos.setAdapter(proAdpObj);

                }
            }
        }); //traer toda la tabla

        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        //this.cargarDatos();
        this.asociarElementos();

        SharedPreferences misPreferencias = getSharedPreferences(getString(R.string.misDatos), MODE_PRIVATE);

        Boolean logueado = misPreferencias.getBoolean("logueado", false);
        if(!logueado){
            Intent in = new Intent(ListadoActivity.this, LoginActivity.class);
            startActivity(in);
            finish();
        }

        String usuario = misPreferencias.getString("usuario", "vacio");
        Toast.makeText(this, "Bienvenido Usuario "+usuario, Toast.LENGTH_LONG).show();



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
        //cargarDatos();
        //proAdpObj.setProductos(productos);
        //proAdpObj.notifyDataSetChanged();
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
