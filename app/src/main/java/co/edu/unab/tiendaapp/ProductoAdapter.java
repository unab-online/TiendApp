package co.edu.unab.tiendaapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ProductoAdapter extends RecyclerView.Adapter {

ArrayList<Productos> productos;


public ProductoAdapter(ArrayList<Productos> productos){
    this.productos = productos;
}

class ProductoViewHolder extends RecyclerView.ViewHolder{
    TextView txt_nombre;
    public ProductoViewHolder ( @NonNull View itemView){
        super(itemView);
        txt_nombre = itemView.findViewById(R.id.txv_nombre);

    }
}

    @NonNull
    @Override //Define lista
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View mivista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto,parent,false);     //guardarla en un objeto


        return  new ProductoViewHolder(mivista);



    }

    @Override//Define los elementos
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


    ProductoViewHolder miHolder = (ProductoViewHolder) holder; //puedo llamar un metodo
        Productos miProducto=productos.get(position);
        miHolder.txt_nombre.setText(miProducto.getNombre());

    }

    @Override//Define el numero de elementos de la lista
    public int getItemCount() {
        return productos.size();
    }
}
