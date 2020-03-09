package co.edu.unab.diaz.javier.tiendapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductoAdapter extends RecyclerView.Adapter{

    ArrayList<Producto> productos;

    public ProductoAdapter(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    class ProductoViewHolder extends RecyclerView.ViewHolder{
        TextView txvNombre;
        public ProductoViewHolder (@NonNull View itemView){
            super(itemView);
            txvNombre = itemView.findViewById(R.id.txv_nombre);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View miVista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);
        return new ProductoViewHolder(miVista);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position){

        ProductoViewHolder miHolder = (ProductoViewHolder) holder;
        Producto miProducto = productos.get(position);
        miHolder.txvNombre.setText(miProducto.getNombre());
    }

    @Override
    public int getItemCount(){
        return productos.size();
    }
}
