package co.edu.unab.celis.sergio.tiendapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class ListadoActivity extends AppCompatActivity {

    private RecyclerView rvProductos;
    private ArrayList<Producto> productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        this.getFakeData();
        this.asociarElementos();

        SharedPreferences misPreferencias = getSharedPreferences(getString(R.string.misDatos), MODE_PRIVATE);

        Boolean logueado = misPreferencias.getBoolean("logueado", false);

        if(!logueado){
            Intent intent = new Intent(ListadoActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }

        String usuario = misPreferencias.getString("usuario","Vacío");

        Toast.makeText(this,"Bienvenido "+ usuario, Toast.LENGTH_LONG).show();

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplication());

        ProductoAdapter miAdaptador = new ProductoAdapter(productos, new ProductoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Producto miProducto, int posicion) {
                Producto miroducto = (Producto)(productos.get(posicion));

                Intent mostrarInformacion = new Intent(getApplication(), InfoActivity.class);
                mostrarInformacion.putExtra("info", miroducto);
                startActivity(mostrarInformacion);

                Toast.makeText(getApplicationContext(),"Hice click "+miroducto, Toast.LENGTH_SHORT).show();
            }
        });

        rvProductos.setLayoutManager(manager);
        rvProductos.setAdapter(miAdaptador);

    }

    public void cerrarSesion (View vista){
        SharedPreferences misPreferencias = getSharedPreferences("tiendapp", MODE_PRIVATE);
        SharedPreferences.Editor miEditor = misPreferencias.edit();
        miEditor.putBoolean("logueado", false);
        miEditor.putString("usuario","");
        //miEditor.clear();
        //miEditor.remove("logueado");
        miEditor.apply();

        Intent i = new Intent(ListadoActivity.this,LoginActivity.class);
        startActivity(i);
        finish();
    }

    private void getFakeData(){

        if(productos == null){
            productos = new ArrayList<>();
        }

        Producto pcAsus = new Producto("Pc Asus",15.3);
        Producto discoDuro = new Producto("Disco Duro",17.2);
        Producto memoriaUSB = new Producto("Memoria USB",7.8);
        Producto mouse = new Producto("Mouse",10);

        productos = new ArrayList<>();
        productos.add(pcAsus);
        productos.add(discoDuro);
        productos.add(memoriaUSB);
        productos.add(mouse);
        productos.add(new Producto("Teclado",80));

        for (int  i =0; i<productos.size(); i++){
            productos.get(i).setDescripción("Desc " + (i+1));
        }
    }

    private void asociarElementos(){
        rvProductos = findViewById(R.id.rvProductos);
    }
}
