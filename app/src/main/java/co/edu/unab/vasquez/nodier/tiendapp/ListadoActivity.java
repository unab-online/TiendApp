package co.edu.unab.vasquez.nodier.tiendapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListadoActivity extends AppCompatActivity {

    private Button btnCerrarSesion;
    private Button btnAgregar;
    private RecyclerView rvProductos; // elemento para asociar el recicler view
    private List<Producto> productos; // elemento para guardar el elemento
    private ProductoDAO productoDAO;
    private ProductoAdapter miAdaptador;
    private ProductoRepository productoRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);


        this.asociarElementos();

        //no volver a solicitar login --- 3
        SharedPreferences misPreferencias = getSharedPreferences(getString(R.string.misDatos), MODE_PRIVATE);
        Boolean logueado = misPreferencias.getBoolean("logueado", false);

        if(!logueado){
            Intent in = new Intent(ListadoActivity.this, LoginActivity.class);
            startActivity(in);
            finish(); //Si le da atrás en la app... no va a poder mostrar esta actividad.
        }

        productoRepository = new ProductoRepository(this);

        productos = new ArrayList<>();

        miAdaptador = new ProductoAdapter(productos, new ProductoAdapter.NombreDeInterface() {
            @Override
            public void metodoParaelItemClick(Producto miProducto, int posicion) {
                Toast.makeText(getApplicationContext(),"Eliminé "+miProducto,Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ListadoActivity.this,EditarActivity.class);
                i.putExtra("producto",miProducto);
                startActivity(i);
            }
        });

        RecyclerView.LayoutManager manager = new GridLayoutManager(getApplicationContext(),2); //Mostrar como grilla
        rvProductos.setLayoutManager(manager);
        rvProductos.setAdapter(miAdaptador);
        rvProductos.setHasFixedSize(true);
        getDataFirestore();

        productoRepository.obtenerTodosFirestore(new CallBackFirestore<List<Producto>>() {
            @Override
            public void correcto(List<Producto> respuesta) {

            }
        });



        //***************************FIRESTORE***************************//
        //FirebaseFirestore firestoreDB = FirebaseFirestore.getInstance();
        //No puedo hacer esto se puede hacer al momento de mostrar los elementos de esa tabla (coleccion)-
        /**List<Producto> productos = firestoreDB.collection("productos").get();*/
        //Toca hacer esto
        //Segunda opción.. con un escuchador

         /*firestoreDB.collection("productos").addSnapshotListener(new EventListener<QuerySnapshot>() {
             @Override
             public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                 productos = new ArrayList<>();
                 if (queryDocumentSnapshots != null) {
                     for (QueryDocumentSnapshot documento : queryDocumentSnapshots){
                         Producto miProducto = documento.toObject(Producto.class);
                         miProducto.setId(documento.getId());
                         productos.add(miProducto);
                     }
                 }
                 //Puedo hacer esto
                 if (miAdaptador != null){
                     miAdaptador.setProductos(productos);
                     miAdaptador.notifyDataSetChanged();
                 }
             }
         });

        //Primera opción
        firestoreDB.collection("productos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            //El onCompleteListener nos devuelve una tarea con la información que se está utilizando
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    RecyclerView.LayoutManager manager = new GridLayoutManager(getApplicationContext(),2); //Mostrar como grilla
                    miAdaptador = new ProductoAdapter(productos, new ProductoAdapter.NombreDeInterface(){
                        @Override
                        public void metodoParaelItemClick(Producto miProducto, int posicion) {
                            Toast.makeText(getApplicationContext(),"Eliminé "+miProducto,Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(ListadoActivity.this,EditarActivity.class);
                            i.putExtra("producto",miProducto);
                            startActivity(i);
                            //productoDAO.eliminar(miProducto);
                            //refrescarPantalla();
                            //onResume();
                        }
                    });
                    rvProductos.setLayoutManager(manager);
                    rvProductos.setAdapter(miAdaptador);
            }
        });



        String usuario = misPreferencias.getString("usuario","");
        Toast.makeText(this,"Bienvenido: "+ usuario,Toast.LENGTH_LONG).show();
*/
        //RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplication()); //Para mostrar con Linear
       /* RecyclerView.LayoutManager manager = new GridLayoutManager(getApplicationContext(),2); //Mostrar como grilla
        miAdaptador = new ProductoAdapter(productos, new ProductoAdapter.NombreDeInterface(){
            @Override
            public void metodoParaelItemClick(Producto miProducto, int posicion) {
                Toast.makeText(getApplicationContext(),"Eliminé "+miProducto,Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ListadoActivity.this,EditarActivity.class);
                i.putExtra("producto",miProducto);
                startActivity(i);
                //productoDAO.eliminar(miProducto);
                //refrescarPantalla();
                onResume();
            }
        });
        rvProductos.setLayoutManager(manager);
        rvProductos.setAdapter(miAdaptador);*/

        irAgregarProducto();

    }
/*
    private void getDataROOM(){
        *//*if(productos==null){
            productos = new ArrayList<>();
        }
        productos.add(new Producto("Pc Asus",50000.0));
        productos.add (new Producto("Disco Duro",200000.0));
        productos.add (new Producto("Memoria USB", 20000.0));
        productos.add (new Producto("Mouse", 15000.0));
        productos.add (new Producto("teclado", 25000.0));

        for (int i = 0;i< productos.size(); i++){
            productos.get(i).setDescripcion("Descripción "+(i+1));
        }*//*

        productos = productoDAO.obtenerTodos();
        if(productos.size()==0){ //si no hay nada

            productoDAO.agregar(new Producto("Pc Asus",50000.0));
            productoDAO.agregar(new Producto("Disco Duro",200000.0));
            productoDAO.agregar(new Producto("Memoria USB", 20000.0));
            productoDAO.agregar(new Producto("Mouse", 15000.0));
            productoDAO.agregar(new Producto("teclado", 25000.0));

            productos = productoDAO.obtenerTodos();
        }
    }*/

    private void getDataFirestore(){
        productoRepository.obtenerTodosFirestore(new CallBackFirestore<List<Producto>>() {
            @Override
            public void correcto(List<Producto> respuesta) {
                miAdaptador.setProductos(respuesta);
                miAdaptador.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDataFirestore();
        miAdaptador.setProductos(productos);
        miAdaptador.notifyDataSetChanged(); //cambia visualmente de lo que uno va a ver

    }

    private void asociarElementos(){
        rvProductos = findViewById(R.id.rv_productos);
        btnCerrarSesion = findViewById(R.id.btn_cerrarSesion);
        btnAgregar = findViewById(R.id.btn_agregar);
    }
    //Hay tres formas de
    public void cerrarSesion(View view){
        SharedPreferences misPreferencias = getSharedPreferences(getString(R.string.misDatos), MODE_PRIVATE);
        SharedPreferences.Editor miEditor = misPreferencias.edit();
        //forma 1
//        miEditor.putBoolean("logueado", false);
//        miEditor.putString("usuario","");
        //forma 2
        miEditor.clear();
        //forma 3
//        miEditor.remove("logueado");
//        miEditor.remove("usuario");
        miEditor.apply();
        Intent i = new Intent(ListadoActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }

   /* public void refrescarPantalla(){
        getData();
        miAdaptador.setProductos(productos);
        miAdaptador.notifyDataSetChanged(); //cambia visualmente de lo que uno va a ver
    }
*/
    public void irAgregarProducto(){

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListadoActivity.this, agregarActivity.class);
                startActivity(i);
            }
        });

    }

}
