package co.edu.unab.rey.carlos.crackapp;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter {

    private List<Producto> productos;
    OnItemClickListener miEscuchador;

    public void setProductos(List<Producto> productos){
        this.productos = productos;
        this.notifyDataSetChanged();
    }

    public ProductoAdapter(List<Producto> productos, OnItemClickListener miEscuchador) {
        this.productos = productos;
        this.miEscuchador = miEscuchador;
    }

    public ProductoAdapter(List<Producto> productos) {
        this.productos = productos;
    }




    class ProductoViewHolder extends RecyclerView.ViewHolder{

        TextView textView_Nombre;
        TextView textView_Precio;
        TextView textView_Descripcion;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_Nombre = itemView.findViewById(R.id.txvNombre);
            textView_Precio = itemView.findViewById(R.id.txvPrecio);
            textView_Descripcion = itemView.findViewById(R.id.txvDescripcion);

        }


    }

    @NonNull//se define la vista
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View miVista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);
        return new ProductoViewHolder(miVista);
    }

    @Override//se define los elementos de la lista
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        ProductoViewHolder miHolder = (ProductoViewHolder) holder;
        final Producto miProducto = productos.get(position);
        miHolder.textView_Nombre.setText(miProducto.getNombre());
        miHolder.textView_Precio.setText("$"+miProducto.getPrecio());
        miHolder.textView_Descripcion.setText("$"+miProducto.getDescripcion());

        miHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miEscuchador.onItemClick(miProducto, position);

            }
        });

        miHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                miEscuchador.onItemClick(miProducto, position);
                return false;
            }
        });

    }



    @Override//numero de elementos de la lista
    public int getItemCount() {
        return productos.size();
    }


    interface OnItemClickListener{
        void onItemClick(Producto miProducto, int posicion);
    }


}
