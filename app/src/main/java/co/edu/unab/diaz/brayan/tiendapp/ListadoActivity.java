package co.edu.unab.diaz.brayan.tiendapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListadoActivity extends AppCompatActivity {

    private RecyclerView rvProductos;
    private List<Producto> productos;
    private ProductoDAO productoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        BaseDatos bd = BaseDatos.obtenerInstancia(this);
        productoDAO = bd.productoDAO();

        this.getFakeData();
        this.AsociarElementos();

        //Persistencia Local
        SharedPreferences misPreferencias = getSharedPreferences(getString(R.string.misDatos), 0);
        Boolean logueado = misPreferencias.getBoolean("logueado", false);
        String usuario = misPreferencias.getString("usuario", "vacio");

        Toast.makeText(this, "Bienvenido Usuario "+usuario, Toast.LENGTH_LONG).show();

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());

        ProductoAdapter miAdaptador =  new ProductoAdapter(productos, new ProductoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Producto producto, int position) {

                Intent intentInfo = new Intent(getApplication(), info_product.class);
                intentInfo.putExtra("producto", producto);
                startActivity(intentInfo);
                Toast.makeText(getApplicationContext(),"Hice click" + producto, Toast.LENGTH_LONG).show();
            }
        });


        rvProductos.setLayoutManager(manager);
        rvProductos.setAdapter(miAdaptador);

        //rvProductos.setOn
    }

    public void cerrarSesion(View vista){
        SharedPreferences misPreferencias = getSharedPreferences("tiendapp", MODE_PRIVATE);
        SharedPreferences.Editor miEditor = misPreferencias.edit();
        //miEditor.putBoolean("logueado", false); //modificar valores de variables especificas
        //miEditor.putString("usuario", "");

        miEditor.clear();//eliminar todoas las variables
        //miEditor.remove("logueado");//eliminar variable especifica
        //miEditor.remove("usuario");
        miEditor.apply();

        Intent i = new Intent(ListadoActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }
    private void getFakeData(){

        productos = productoDAO.obtenerTodos();
        if(productos.size()==0){
            productoDAO.agregar(new Producto("Pc Asus", 2000));
            productoDAO.agregar(new Producto("Disco duro", 2000));
            productoDAO.agregar(new Producto("Memoria USB", 5));
            productoDAO.agregar(new Producto("Mouse", 10));
            productoDAO.agregar(new Producto("Teclado", 500));

            productos = productoDAO.obtenerTodos();

        }


    }

    private void AsociarElementos(){
        rvProductos = findViewById(R.id.rv_productos);
    }
}
