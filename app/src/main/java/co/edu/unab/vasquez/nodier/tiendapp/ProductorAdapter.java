package co.edu.unab.vasquez.nodier.tiendapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductorAdapter extends RecyclerView.Adapter {

    ArrayList<Producto> productos;// son los productos que voy a mostrar

    //crear un constructor

    public ProductorAdapter(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    class ProductoViewHolder extends  RecyclerView.ViewHolder{

        TextView txvNombre;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            txvNombre = itemView.findViewById(R.id.txv_nombre);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Para cargar Layout
        View miVista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent,false);


        return new ProductoViewHolder(miVista);
        //Asigno mi vista
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
      //decir en que lado va
        ProductoViewHolder miHolder = (ProductoViewHolder) holder; // voy a castear el objeto generico que le llega de la clase
        Producto miProducto = productos.get(position);
        miHolder.txvNombre.setText(miProducto.getNombre());
    }

    @Override
    public int getItemCount() {
        return productos.size();
        //contar elementos
    }

    //se implementa esta clase cuando usemos recycler
}
