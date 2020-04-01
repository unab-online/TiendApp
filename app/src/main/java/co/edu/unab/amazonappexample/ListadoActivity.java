package co.edu.unab.amazonappexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import co.edu.unab.amazonappexample.Adapters.ProductoAdapter;

public class ListadoActivity extends AppCompatActivity {
    private RecyclerView rv_productos;
    private ArrayList<Producto> productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        SharedPreferences LocalStorage= getSharedPreferences(getString(R.string.nameStorage),MODE_PRIVATE);
        Boolean status= LocalStorage.getBoolean(getString(R.string.strStatus),false);
        String user= LocalStorage.getString(getString(R.string.user),"");

        if (status){
            Toast.makeText(this, "Welcom,"+user, Toast.LENGTH_SHORT).show();
        }else {
            Intent in = new Intent(ListadoActivity.this,LoginActivity.class);
            startActivity(in);
        }

        this.AsociarElementos();
        this.getFakeData();
        RecyclerView.LayoutManager manajer= new LinearLayoutManager(getApplication());
        //RecyclerView.LayoutManager xd= new GridLayoutManager(getApplication(),2);
        ProductoAdapter adapter= new ProductoAdapter(productos);
        rv_productos.setLayoutManager(manajer);
        rv_productos.setAdapter(adapter);

    }

    private void getFakeData(){
        productos= new ArrayList<>();
        Producto p1= new Producto("Pc Asus",1800000);
        Producto p2= new Producto("Disco duro",250000);
        Producto p3= new Producto("Memoria USB",18000);
        Producto p4= new Producto("Mouse",78000);
        productos.add(p1);
        productos.add(p2);
        productos.add(p3);
        productos.add(p4);

        for (Producto productos1:productos
             ) {
            productos1.setDescripcion("xd");

        }

    }
    private void AsociarElementos(){
        rv_productos= findViewById(R.id.rv_productos);

    }


}
