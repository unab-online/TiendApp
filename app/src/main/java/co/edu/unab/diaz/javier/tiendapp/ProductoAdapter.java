package co.edu.unab.diaz.javier.tiendapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductoAdapter extends RecyclerView.Adapter{

    ArrayList<Producto> productos;

    public ProductoAdapter(ArrayList<Producto> productos, OnItemClickListener miEscuchador) {
        this.productos = productos;
        this.miEscuchador = miEscuchador;
    }

    OnItemClickListener miEscuchador;

    public ProductoAdapter(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    class ProductoViewHolder extends RecyclerView.ViewHolder{
        TextView txvNombre, txvPrecio;
        public ProductoViewHolder (@NonNull View itemView){
            super(itemView);
            txvNombre = itemView.findViewById(R.id.txv_nombre);
            txvPrecio = itemView.findViewById(R.id.txv_precio);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View miVista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);
        return new ProductoViewHolder(miVista);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position){

        ProductoViewHolder miHolder = (ProductoViewHolder) holder;

        final Producto miProducto = productos.get(position);
        miHolder.txvNombre.setText(miProducto.getNombre());
        miHolder.txvPrecio.setText("$"+miProducto.getPrecio());

        miHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miEscuchador.onItemClick(miProducto, position);
            }
        });

        miHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("Prueba-CLick", "hice-click-sostenido"+miProducto);
                return false;
            }
        });
    }

    @Override
    public int getItemCount(){
        return productos.size();
    }

    interface OnItemClickListener{
        void onItemClick(Producto miproducto, int posicion);
    }
}
