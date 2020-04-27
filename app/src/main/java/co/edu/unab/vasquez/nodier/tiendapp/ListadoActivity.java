package co.edu.unab.vasquez.nodier.tiendapp;

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

import java.util.ArrayList;
import java.util.List;

public class ListadoActivity extends AppCompatActivity {

    private Button btnCerrarSesion;
    private Button btnAgregar;
    private RecyclerView rvProductos; // elemento para asociar el recicler view
    private List<Producto> productos; // elemento para guardar el elemento
    private ProductoDAO productoDAO;
    private ProductoAdapter miAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        BaseDatos bd = BaseDatos.obtenerInstancia(this);
        productoDAO = bd.productoDAO();

        this.getData();
        this.asociarElementos();

        //no volver a solicitar login --- 3
        SharedPreferences misPreferencias = getSharedPreferences(getString(R.string.misDatos), MODE_PRIVATE);
        Boolean logueado = misPreferencias.getBoolean("logueado", false);

        if(!logueado){
            Intent in = new Intent(ListadoActivity.this, LoginActivity.class);
            startActivity(in);
            finish(); //Si le da atrás en la app... no va a poder mostrar esta actividad.
        }

        String usuario = misPreferencias.getString("usuario","");
        Toast.makeText(this,"Bienvenido: "+ usuario,Toast.LENGTH_LONG).show();

        //RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplication()); //Para mostrar con Linear
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
                onResume();
            }
        });
        rvProductos.setLayoutManager(manager);
        rvProductos.setAdapter(miAdaptador);

        irAgregarProducto();

    }

    private void getData(){
        /*if(productos==null){
            productos = new ArrayList<>();
        }
        productos.add(new Producto("Pc Asus",50000.0));
        productos.add (new Producto("Disco Duro",200000.0));
        productos.add (new Producto("Memoria USB", 20000.0));
        productos.add (new Producto("Mouse", 15000.0));
        productos.add (new Producto("teclado", 25000.0));

        for (int i = 0;i< productos.size(); i++){
            productos.get(i).setDescripcion("Descripción "+(i+1));
        }*/

        productos = productoDAO.obtenerTodos();
        if(productos.size()==0){ //si no hay nada

            productoDAO.agregar(new Producto("Pc Asus",50000.0));
            productoDAO.agregar(new Producto("Disco Duro",200000.0));
            productoDAO.agregar(new Producto("Memoria USB", 20000.0));
            productoDAO.agregar(new Producto("Mouse", 15000.0));
            productoDAO.agregar(new Producto("teclado", 25000.0));

            productos = productoDAO.obtenerTodos();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
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
