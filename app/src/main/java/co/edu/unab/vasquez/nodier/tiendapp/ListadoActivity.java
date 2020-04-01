package co.edu.unab.vasquez.nodier.tiendapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class ListadoActivity extends AppCompatActivity {

    private RecyclerView rvProductos; // elemento para asociar el recicler view
    private ArrayList <Producto> productos; // elemento para guardar el elemento

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        this.getFakeData();
        this.asociarElementos();

        //no volver a solicitar login --- 3
        final SharedPreferences misPreferencias = getSharedPreferences(getString(R.string.misDatos), MODE_PRIVATE);
        Boolean logueado = misPreferencias.getBoolean("logueado", false);
        String usuario = misPreferencias.getString("usuario","");

        Toast.makeText(this,"Bienvenido: "+ usuario,Toast.LENGTH_LONG).show();

        //RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplication()); //Para mostrar con Linear
        RecyclerView.LayoutManager manager = new GridLayoutManager(getApplicationContext(),2); //Mostrar como grilla

        ProductoAdapter miAdaptador = new ProductoAdapter(productos, new ProductoAdapter.NombreDeInterface(){
            @Override
            public void metodoParaelItemClick(Producto miProducto, int posicion) {
                Toast.makeText(getApplicationContext(),"HiceClick "+miProducto,Toast.LENGTH_LONG).show();
            }
        });

        rvProductos.setLayoutManager(manager);
        rvProductos.setAdapter(miAdaptador);


    }

    private void getFakeData(){

        if(productos==null){
            productos = new ArrayList<>();
        }
        productos.add(new Producto("Pc Asus",50000.0));
        productos.add (new Producto("Disco Duro",200000.0));
        productos.add (new Producto("Memoria USB", 20000.0));
        productos.add (new Producto("Mouse", 15000.0));
        productos.add (new Producto("teclado", 25000.0));

        for (int i = 0;i< productos.size(); i++){
            productos.get(i).setDescripcion("Descripción "+(i+1));
        }

    }

    private void asociarElementos(){
        rvProductos = findViewById(R.id.rv_productos);
    }

}
