package co.edu.unab.rey.carlos.crackapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import co.edu.unab.rey.carlos.crackapp.model.bd.network.ProductoAPI;
import co.edu.unab.rey.carlos.crackapp.model.bd.network.TiendAppService;
import co.edu.unab.rey.carlos.crackapp.model.entity.Editar;
import co.edu.unab.rey.carlos.crackapp.model.entity.Producto;
import co.edu.unab.rey.carlos.crackapp.view.adapter.ProductoAdapter;
import co.edu.unab.rey.carlos.crackapp.model.repository.ProductoRepository;
import co.edu.unab.rey.carlos.crackapp.R;
import co.edu.unab.rey.carlos.crackapp.model.bd.network.CallBackFirestore;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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

        Retrofit retrofit = TiendAppService.obtenerInstancias();

        //permite concetar al api o servicio
        ProductoAPI productoAPI = retrofit.create(ProductoAPI.class);

        productoAPI.obtenerTodos().enqueue(new Callback<Map<Object, Object>>() {
            @Override
            public void onResponse(Call<Map <Object, Object>> call, Response<Map<Object, Object>> response) {
                if (response.body()!=null){
                    for (Map.Entry datos: response.body().entrySet()){
                        String id = (String) datos.getKey();

                        Map mapa = (Map) datos.getValue();
                        Producto miProducto = new Producto();
                        miProducto.setNombre((String) mapa.get("nombre"));
                        miProducto.setPrecio((Double) mapa.get("precio"));
                        miProducto.setDescripcion((String) mapa.get("descripcion"));
                        miProducto.setId(id)    ;
                        productos.add(miProducto);
                    }
                    miAdaptador.setProductos(productos);
                    Log.d("API", response.body().toString());
                    Toast.makeText(ListadoActivity.this,response.body().toString(), Toast.LENGTH_LONG).show();
                }

            }



            @Override
            public void onFailure(Call<Map<Object, Object>> call, Throwable t) {
                Log.d("API","Eror"+t.getMessage());
            }
        });

        agregarProducto();



    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
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
