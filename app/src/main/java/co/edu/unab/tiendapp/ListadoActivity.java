package co.edu.unab.tiendapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListadoActivity extends AppCompatActivity {
private RecyclerView rvProductos;
private List<Producto> productos;
private ProductoDAO productoDAO;
private  ProductoAdapter miAdaptador;
    private Button btnCerrarSesion;
    private Button btnAgregarProducto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        BaseDatos bd= BaseDatos.obtenerInstancia(getApplication());
        productoDAO=bd.productoDAO();
        SharedPreferences misPreferencias= getSharedPreferences(getString(R.string.misDatos),MODE_PRIVATE );

        // segundo parametro, que pasa si no he creado ese dato (por defecto devuelve falso)
        Boolean logueado=misPreferencias.getBoolean("logueado", false );
        if(!logueado){
            Intent in= new Intent(ListadoActivity.this, Login.class);
            startActivity(in);
            finish();
        }
        String usuario=misPreferencias.getString("usuario", "");


        Toast.makeText(getApplicationContext(), "usuario"+usuario, Toast.LENGTH_LONG).show();

        //el this no es necesario
        this.getFakeData();
        this.AsociarElementos();

        //Debemos crear un nuevo archivo, siempre que trabajamos con un Recyclerview

        //Agragamos el manejador, si creamos un LinearLayout o un Grid
        RecyclerView.LayoutManager manager = new GridLayoutManager(getApplication(),2);
       // ProductoAdapter miAdaptador= new ProductoAdapter(productos);

         miAdaptador = new ProductoAdapter(productos, new ProductoAdapter.onItemClickListener() {
            @Override
            public void onItemClick(Producto miProducto, int posicion) {
                Toast.makeText(getApplicationContext(), "Hice click "+miProducto,Toast.LENGTH_SHORT).show();
                productoDAO.eliminar(miProducto);
                //La siguiente linea consulta de nuevo los datos en la bd
                getFakeData();
                //En esta le digo a mi adaptador cuál lista quiero agregar
                miAdaptador.setProductos(productos);
                //se encarga de cambiar visualmente lo que veré, es decir, pinta de nuevo 
                miAdaptador.notifyDataSetChanged();
            }
        });

        rvProductos.setLayoutManager(manager);
        rvProductos.setAdapter(miAdaptador);

    }

    private void getFakeData(){
        productos=productoDAO.obtenerTodos();
        if(productos.size()==0){

            Producto p1= new Producto("Pc Asus",1800000);
            Producto p2= new Producto("Disco duro",250000);
            Producto p3= new Producto("Memoria USB",18000);
            Producto p4= new Producto("Mouse",78000);
            productoDAO.agregar(p1);
            productoDAO.agregar(p2);
            productoDAO.agregar(p3);
            productoDAO.agregar(p4);
            productos=productoDAO.obtenerTodos();
        }


    }

    private void AsociarElementos(){

        rvProductos=findViewById(R.id.rv_productos);
        btnCerrarSesion= findViewById(R.id.btn_logout);
        btnAgregarProducto=findViewById(R.id.btn_agregar_producto);
    }

    public void cerrarSesion(View view){
        SharedPreferences misPreferencias= getSharedPreferences(getString(R.string.misDatos),MODE_PRIVATE );
        SharedPreferences.Editor miEditor= misPreferencias.edit();

        //clear para borrar todos
        //miEditor.clear();

        //Para borrar una variables especifica uso
        miEditor.remove("logueado");
        miEditor.remove("usuario");
        miEditor.apply();
        Intent in= new Intent(ListadoActivity.this, Login.class);
        startActivity(in);
        finish();
    }


    @Override
    protected void onResume() {
        super.onResume();
        getFakeData();
        miAdaptador.setProductos(productos);
        miAdaptador.notifyDataSetChanged();
    }

    public void agregarProducto(View view){
        Intent i= new Intent(ListadoActivity.this, AgregarProducto.class);
        startActivity(i);
    }

}
