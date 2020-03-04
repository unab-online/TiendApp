package co.edu.unab.casadiegos.andres.tiendaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.security.ProtectionDomain;
import java.util.ArrayList;

public class ListadoActivity extends AppCompatActivity {

    RecyclerView rvProductos;
    ArrayList<Producto> productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        this.getFakeData();
        this.AsociarElementos();

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplication());

        ProductoAdapter miAdaptador = new ProductoAdapter(productos);

        rvProductos.setLayoutManager(manager);
        rvProductos.setAdapter(miAdaptador);
    }

    void getFakeData(){

        if(productos == null)
        {
            productos = new ArrayList<>();
        }

        productos.add(new Producto("PC Asus", 2000));
        productos.add(new Producto("Disco Duro",5000));
        productos.add(new Producto("Memoria USB",8000));
        productos.add(new Producto("Mouse",4000));
        productos.add(new Producto("PC Asus",2000));
        productos.add(new Producto("Teclado",5500));
        productos.add(new Producto("PC Asus", 2000));
        productos.add(new Producto("Disco Duro",5000));
        productos.add(new Producto("Memoria USB",8000));
        productos.add(new Producto("Mouse",4000));
        productos.add(new Producto("PC Asus",2000));
        productos.add(new Producto("Teclado",5500));
        productos.add(new Producto("PC Asus", 2000));
        productos.add(new Producto("Disco Duro",5000));
        productos.add(new Producto("Memoria USB",8000));
        productos.add(new Producto("Mouse",4000));
        productos.add(new Producto("PC Asus",2000));
        productos.add(new Producto("Teclado",5500));
        productos.add(new Producto("PC Asus", 2000));
        productos.add(new Producto("Disco Duro",5000));
        productos.add(new Producto("Memoria USB",8000));
        productos.add(new Producto("Mouse",4000));
        productos.add(new Producto("PC Asus",2000));
        productos.add(new Producto("Teclado",5500));
        productos.add(new Producto("PC Asus", 2000));
        productos.add(new Producto("Disco Duro",5000));
        productos.add(new Producto("Memoria USB",8000));
        productos.add(new Producto("Mouse",4000));
        productos.add(new Producto("PC Asus",2000));
        productos.add(new Producto("Teclado",5500));
        productos.add(new Producto("PC Asus", 2000));
        productos.add(new Producto("Disco Duro",5000));
        productos.add(new Producto("Memoria USB",8000));
        productos.add(new Producto("Mouse",4000));
        productos.add(new Producto("PC Asus",2000));
        productos.add(new Producto("Teclado",5500));
        productos.add(new Producto("PC Asus", 2000));
        productos.add(new Producto("Disco Duro",5000));
        productos.add(new Producto("Memoria USB",8000));
        productos.add(new Producto("Mouse",4000));
        productos.add(new Producto("PC Asus",2000));
        productos.add(new Producto("Teclado",5500));
        productos.add(new Producto("PC Asus", 2000));
        productos.add(new Producto("Disco Duro",5000));
        productos.add(new Producto("Memoria USB",8000));
        productos.add(new Producto("Mouse",4000));
        productos.add(new Producto("PC Asus",2000));
        productos.add(new Producto("Teclado",5500));
        productos.add(new Producto("PC Asus", 2000));
        productos.add(new Producto("Disco Duro",5000));
        productos.add(new Producto("Memoria USB",8000));
        productos.add(new Producto("Mouse",4000));
        productos.add(new Producto("PC Asus",2000));
        productos.add(new Producto("Teclado",5500));


        for (int i=0;i<productos.size();i++)
        {
            productos.get(i).setDescripcion("Desc "+(i+1));
        }

    }

    void AsociarElementos()
    {
            rvProductos = findViewById(R.id.rv_productos);
    }
}
