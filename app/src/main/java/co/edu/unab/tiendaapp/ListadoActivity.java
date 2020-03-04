package co.edu.unab.tiendaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class ListadoActivity extends AppCompatActivity {

    private RecyclerView rv_producto;
    ArrayList<Productos>productos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        this.getFakeData();
        this.AsociarElementos();

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());

        ProductoAdapter miAdaptador = new ProductoAdapter(productos);

        rv_producto.setLayoutManager(manager);
        rv_producto.setAdapter(miAdaptador);


    }

    private void getFakeData(){
        if(productos == null){
            productos = new ArrayList<>();
        }

        productos.add(new Productos( "Llaves Locke & Key", "Llaves reales que te otorgan habilidades especiales",  29000));
        productos.add(new Productos( "Plutonio", "Pequeñas cantidades de plutonio para sus experimentos caseros ",64500));
        productos.add(new Productos( "Ak 47", "El vendedor no se hace responsable por el mal uso del producto",27999));
        productos.add(new Productos( "Pierna de Cerdo", "Deliciosa pierna de cerdo de 25 Kg ", 18000));
        productos.add(new Productos( "Obsidiana", "Cuarzo oscuro para aumentar la energia positiva del aura corporal y espiritual", 17000));
        productos.add(new Productos( "Souls", "Espiritus chocarreros esclavos para uso personal",0000));
        productos.add(new Productos( "Cerdo sin una pierna", "Delicioso cerdo que antes tenia 4 piernas",195000));

    }


    private void AsociarElementos(){

        rv_producto = findViewById(R.id.rv_productos);

    }
}
