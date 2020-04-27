package co.edu.unab.saavedra.juan.tiendapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
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


public class ListadoActivity extends AppCompatActivity {

    private RecyclerView rvProductos;
    private List<Producto> productos;
    private ProductoDAO productoDAO;
    private ProductoAdapter miAdaptador;

    Button btnCerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        BaseDatos bd = BaseDatos.obtenerInstancia(this);
        productoDAO = bd.productoDAO();

        FirebaseFirestore firestoreDB = FirebaseFirestore.getInstance();

        firestoreDB.collection("productos").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                productos = new ArrayList<>();
                if(queryDocumentSnapshots!=null) {
                    for (QueryDocumentSnapshot documento : queryDocumentSnapshots) {
                        Producto miProducto = documento.toObject(Producto.class);
                        miProducto.setId(documento.getId());
                        productos.add(miProducto);
                    }
                }
                if(miAdaptador!=null){
                    miAdaptador.setProductos(productos);
                    miAdaptador.notifyDataSetChanged();
                }
            }
        });

        firestoreDB.collection("productos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    productos = new ArrayList<>();
                    for(QueryDocumentSnapshot documento : task.getResult()){
                        Producto miProducto = documento.toObject(Producto.class);
                        miProducto.setId(documento.getId());
                        productos.add(miProducto);
                    }

                    RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplication());

                    miAdaptador = new ProductoAdapter(productos, new ProductoAdapter.OnItemClickListener() {
                        @Override
                        public void OnItemClick(Producto miproducto, int posicion) {
                            Toast.makeText(getApplicationContext(), "Hice click "+miproducto, Toast.LENGTH_LONG).show();
                            Intent i = new Intent(ListadoActivity.this, EditarActivity.class);
                            i.putExtra("producto", miproducto);
                            startActivity(i);
                        }
                    });


                    rvProductos.setLayoutManager(manager);
                    rvProductos.setAdapter(miAdaptador);
                }
            }
        });

        //this.getData();
        this.asociarElementos();

        SharedPreferences misPreferencias = getSharedPreferences(getString(R.string.misDatos), MODE_PRIVATE);

        Boolean logueado = misPreferencias.getBoolean("logueado", false);

        if(!logueado){
            Intent intentListado = new Intent(ListadoActivity.this, LoginActivity.class);
            startActivity(intentListado);
            finish();
        }

        String usuario = misPreferencias.getString("usuario", "");

        Toast.makeText(this, "Bienvenido "+usuario, Toast.LENGTH_LONG).show();

    }

    private void actualizarDatos(){
        getData();
        miAdaptador.setProductos(productos);
        //APLICAR CAMBIOS, MIRA SI HAY ALGUNO Y MODIFICA
        miAdaptador.notifyDataSetChanged();
    }

    private void getData(){

        productos = productoDAO.obtenerTodos();
        if(productos.size()==0){
            Producto p1 = new Producto("Pc Asus", 12.5);
            p1.setDescripcion("Computador marca Asus");
            productos.add(p1);
            Producto p2 = new Producto("Airpods pro",250);
            p2.setDescripcion("Audifonos Apple");
            productos.add(p2);
            Producto p3 = new Producto("Iphone 11",700);
            p3.setDescripcion("Celular Apple");
            productos.add(p3);
            Producto p4 = new Producto("Monitor Lg",300);
            p4.setDescripcion("Monitor de 30' marca LG");
            productos.add(p4);
            Producto p5 = new Producto("Cable Iphone",30);
            p5.setDescripcion("Cable de corriente para Iphone");
            productos.add(p5);

            productoDAO.agregar(p1);
            productoDAO.agregar(p2);
            productoDAO.agregar(p3);
            productoDAO.agregar(p4);
            productoDAO.agregar(p5);

            productos = productoDAO.obtenerTodos();
        }

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
