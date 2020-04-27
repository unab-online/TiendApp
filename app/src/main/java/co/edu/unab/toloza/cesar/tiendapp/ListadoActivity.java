package co.edu.unab.toloza.cesar.tiendapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListadoActivity extends AppCompatActivity {

    private RecyclerView rvProductos;
    private List<Producto> productos;
    private ProductoDAO productoDAO;
    private ProductoAdapter miProductoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        BaseDatos db = BaseDatos.obtenerInstancia(this);
        productoDAO = db.productoDAO();

        FirebaseFirestore firestoreDB = FirebaseFirestore.getInstance();

        firestoreDB.collection("productos").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                productos = new ArrayList<>();
                if(queryDocumentSnapshots != null){
                    for (QueryDocumentSnapshot document: queryDocumentSnapshots){
                        Producto producto = document.toObject(Producto.class);
                        producto.setId(document.getId());
                        productos.add(producto);
                    }
                }
                if(miProductoAdapter != null){
                    miProductoAdapter.setProductos(productos);
                    miProductoAdapter.notifyDataSetChanged();
                }

            }
        });

        firestoreDB.collection("productos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    productos = new ArrayList<>();
                    for (QueryDocumentSnapshot document: task.getResult()){
                        Producto producto = document.toObject(Producto.class);
                        producto.setId(document.getId());
                        productos.add(producto);
                    }

                    RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplication());
                    miProductoAdapter = new ProductoAdapter(productos, new ProductoAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(Producto producto, int position) {
                            Toast.makeText(getApplication(), "Hice click " + producto, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ListadoActivity.this, EditarActivity.class);
                            intent.putExtra("producto", producto);
                            startActivity(intent);
                            /*productoDAO.eliminar(producto);
                            getData();
                            miProductoAdapter.setProductos(productos);
                            miProductoAdapter.notifyDataSetChanged();*/
                        }
                    });
                    rvProductos.setLayoutManager(manager);
                    rvProductos.setAdapter(miProductoAdapter);

                }
            }
        });

        //this.getData();
        this.AsociarElementos();

        SharedPreferences misPreferencias = getSharedPreferences(getString(R.string.preferencias), MODE_PRIVATE);

        Boolean logueado = misPreferencias.getBoolean("logueado", false);
        String user = misPreferencias.getString("user", "");

        if(!logueado){
            Intent in = new Intent(ListadoActivity.this, LoginActivity.class);
            startActivity(in);
            finish();
        }

        Toast.makeText(getApplicationContext(), "Bienvenido " + user, Toast.LENGTH_LONG ).show();


    }

    @Override
    protected void onResume() {
        super.onResume();
        //getData();
        //miProductoAdapter.setProductos(productos);
        //miProductoAdapter.notifyDataSetChanged();
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
        productos = productoDAO.obtenerTodos();
        if(productos.size() == 0){
            productoDAO.agregar(new Producto("PC Asus", 2000.0));
            productoDAO.agregar(new Producto("Disco Duro", 500.0));
            productoDAO.agregar(new Producto("Memoria USB", 100.0));
            productoDAO.agregar(new Producto("Mouse", 50.0));
            productoDAO.agregar(new Producto("Teclado", 80.0));

            productos = productoDAO.obtenerTodos();
        }

    }
    private void AsociarElementos(){
        rvProductos = findViewById(R.id.rv_productos);
    }
}
