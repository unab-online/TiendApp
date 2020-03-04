package co.edu.unab.tiendaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ListadoActivity extends AppCompatActivity {
    private  RecyclerView rv_producto;
    ArrayList<Productos> productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        this.getFaketData();
        this.AsociarElementos();
        RecyclerView.LayoutManager Manager=new LinearLayoutManager(getApplicationContext());

        ProductoAdapter miAdaptador= new ProductoAdapter(productos);
        rv_producto.setLayoutManager(Manager);
        rv_producto.setAdapter(miAdaptador);

    }

    private void getFaketData(){

        if (productos==null){
            productos=new ArrayList<>();
        }
        productos.add(new Productos( "Llavero Usb","8 Gigas", 30000));
        productos.add(new Productos( "Bolso ", " Elegante  en colores champaña y Dorado",50000));
        productos.add(new Productos( "portatil", "i5 de 8 de ram  250 de disco solido",1300000));
        productos.add(new Productos( "mouse", "motivos animados inalambrico",35000));
        productos.add(new Productos( "apuntador ", "larga duracion recargable",17000));
        productos.add(new Productos( "protector de teclado ", "en silicona transparente",15000));
        productos.add(new Productos( "limpiador de pantallas lcd", "de 250 centimetros cubicos  con atomizador y pañitos limpiadore",8000));

    }

    private void AsociarElementos(){

        rv_producto=findViewById(R.id.rv_productos);

    }
}
