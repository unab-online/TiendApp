package co.edu.unab.rey.carlos.crackapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class ListadoActivity extends AppCompatActivity {

    private RecyclerView rvProductos;
    private List<Producto> productos;
    private ProductoAdapter miAdaptador;
    private ProductoRepository productoRepository;
    private Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        this.AsociarElementos();

        SharedPreferences misPreferencias = getSharedPreferences(getString(R.string.misDatos), MODE_PRIVATE);
        Boolean logueado = misPreferencias.getBoolean("logueado", false);

        if(!logueado){
            Intent in = new Intent(ListadoActivity.this, LoginActivity.class);
            startActivity(in);
            finish();
        }

        productoRepository = new ProductoRepository(ListadoActivity.this);

        productos = new ArrayList<>();

        inicializarRecyclerview();

        getData();

        obtenerTodosFirestore();

        escucharTodosFirestore();

        String usuario = misPreferencias.getString("usuario", "nonname");
        Toast.makeText(this, "Bienvenido "+usuario, Toast.LENGTH_LONG).show();

        agregarProducto();



    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    public void irAgregarProductos(View vista){
        Intent in = new Intent (ListadoActivity.this, Editar.class);
        startActivity(in);
    }

    public void cerrarSesion(View vista){
        SharedPreferences misPreferencias = getSharedPreferences(getString(R.string.misDatos), MODE_PRIVATE);
        SharedPreferences.Editor miEditor = misPreferencias.edit();
        miEditor.clear();
        miEditor.apply();
        Intent in = new Intent(ListadoActivity.this, LoginActivity.class);
        startActivity(in);
        finish();
    }



    private void getData(){
        productoRepository.obtenerTodosFireStore(new CallBackFirestore<List<Producto>>() {
            @Override
            public void correcto(List<Producto> respuesta) {
                productos = respuesta;
                miAdaptador.setProductos(productos);
            }
        });
    }

    private void AsociarElementos(){
        rvProductos = findViewById(R.id.rv_productos);
        btnAgregar = findViewById(R.id.btn_agregar);
    }

    public void obtenerTodosFirestore(){
        productoRepository.obtenerTodosFireStore(new CallBackFirestore<List<Producto>>() {
            @Override
            public void correcto(List<Producto> respuesta) {

            }
        });
    }

    public void escucharTodosFirestore(){
        productoRepository.escucharTodos(new CallBackFirestore<List<Producto>>() {
            @Override
            public void correcto(List<Producto> respuesta) {

            }
        });
    }

    public void inicializarRecyclerview(){
        miAdaptador = new ProductoAdapter(productos, new ProductoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Producto miProducto, int posicion) {
                Toast.makeText(getApplicationContext(), "Hice click "+miProducto, Toast.LENGTH_LONG).show();
                Intent i = new Intent(ListadoActivity.this, Editar.class);
                i.putExtra("producto", miProducto);
                startActivity(i);
                getData();
                //miAdaptador.setProductos(productos);
                //miAdaptador.notifyDataSetChanged();
            }
        });

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplication());
        rvProductos.setLayoutManager(manager);
        rvProductos.setAdapter(miAdaptador);
        rvProductos.setHasFixedSize(true);
    }

    public void agregarProducto(){
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListadoActivity.this, FormularioAgregar.class);
                startActivity(i);
            }
        });
    }
}
