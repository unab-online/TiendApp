package co.edu.unab.tiendapp.forero.jesus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ListadoActivity extends AppCompatActivity {
private RecyclerView rvProductos;
private ArrayList<Producto>productos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        this.AsociarElementos();
        this.getFakeData();
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplication());
        ProductoAdapter miAdaptador = new ProductoAdapter((productos));
        rvProductos.setLayoutManager(manager);
        rvProductos.setAdapter(miAdaptador);
    }
    private void getFakeData(){
        if (productos==null){
            productos =new ArrayList<>();
        }
        productos.add(new Producto("PC asus",2600.0));
        productos.add(new Producto("GTX 1080",1500.0));
        productos.add(new Producto("Disco Duro",150.0));
        productos.add(new Producto("Mouse",80.0));
        productos.add(new Producto("Teclado",80.0));
        productos.add(new Producto("PC asus",2600.0));
        productos.add(new Producto("GTX 1080",1500.0));
        productos.add(new Producto("Disco Duro",150.0));
        productos.add(new Producto("Mouse",80.0));
        productos.add(new Producto("Teclado",80.0));
        productos.add(new Producto("PC asus",2600.0));
        productos.add(new Producto("GTX 1080",1500.0));
        productos.add(new Producto("Disco Duro",150.0));
        productos.add(new Producto("Mouse",80.0));
        productos.add(new Producto("Teclado",80.0));

        for (int i=0;i<productos.size();i++){
            productos.get(i).setDescripcion("Desc "+(i+1));
        }
    }
    private void AsociarElementos(){
        rvProductos = findViewById(R.id.rv_productos);

    }
}
