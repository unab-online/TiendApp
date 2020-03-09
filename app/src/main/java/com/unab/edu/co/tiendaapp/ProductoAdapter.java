package com.unab.edu.co.tiendaapp;

import android.view.LayoutInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.AbstractCollection;
import java.util.ArrayList;

public class ProductoAdapter extends RecyclerView.Adapter {

    ArrayList<Productos> productos;

    public ProductoAdapter(ArrayList<Productos> productos){
        this.productos = productos;
    }

    class ProductoViewHolder extends RecyclerView.ViewHolder{
        TextView tvxNombre;

        public ProductoViewHolder(@NonNull View itemView){
            super(itemView);
            tvxNombre = itemView.findViewById(R.id.txv_Nombre);
        }
    }

    @NonNull
    @Override



    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Definir cual va a ser el layout que va a tener cada elemento (Vista)
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);

        return new ProductoViewHolder(vista);
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //indicamos de los datos que tengo y exsten en el layout, que va en que parte, osea cada elemento va en un lugar especifico (Asignar elementos de la Vista)

        ProductoViewHolder miholder = (ProductoViewHolder) holder;
        Productos miProducto = productos.get(position);
        miholder.tvxNombre.setText(miProducto.getNombre());
        // miholder.tvxNombre.setText(miProducto.getPrecio());

    }

    @Override
    public int getItemCount() {
        //Para decirle cauntos elelmtos voy a tener en mi RV (Cuantos elementos hay en la lista)
        return productos.size();
    }
}
