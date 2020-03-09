package co.edu.unab.melon.cristian.tiendapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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
        this.AsociarElemento();

        //RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplication());
       RecyclerView.LayoutManager manager = new GridLayoutManager(getApplication(),1);

        ProductoAdapter miAdaptador = new ProductoAdapter(productos, new ProductoAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(Producto miProducto, int position) {
                Toast.makeText(getApplicationContext(), "Hice click "+miProducto,Toast.LENGTH_LONG).show();
            }
        });

rvProductos.setLayoutManager(manager);
rvProductos.setAdapter(miAdaptador);



    }


    private void getFakeData(){

        if(productos == null){
            productos = new ArrayList<>();
        }

        productos.add(new Producto("PC Asus", 2000.0));
        productos.add(new Producto("Disco Duro", 100.0));
        productos.add(new Producto("Memoria USB", 20.0));
        productos.add(new Producto("Mouse",10.0));
        productos.add(new Producto("Teclado",12.0));
        productos.add(new Producto("PC Asus", 2000.0));
        productos.add(new Producto("Disco Duro", 100.0));
        productos.add(new Producto("Memoria USB", 20.0));
        productos.add(new Producto("Mouse",10.0));
        productos.add(new Producto("Teclado",12.0));
        productos.add(new Producto("PC Asus", 2000.0));
        productos.add(new Producto("Disco Duro", 100.0));
        productos.add(new Producto("Memoria USB", 20.0));
        productos.add(new Producto("Mouse",10.0));
        productos.add(new Producto("Teclado",12.0));
        productos.add(new Producto("PC Asus", 2000.0));
        productos.add(new Producto("Disco Duro", 100.0));
        productos.add(new Producto("Memoria USB", 20.0));
        productos.add(new Producto("Mouse",10.0));
        productos.add(new Producto("Teclado",12.0));

        for ( int i=0; i<productos.size();i++   ){
            productos.get(i).setDescripción("Descripción "+(i+1));

        }

    }

    private void AsociarElemento(){
rvProductos = findViewById(R.id.lv_productos);

    }

}
