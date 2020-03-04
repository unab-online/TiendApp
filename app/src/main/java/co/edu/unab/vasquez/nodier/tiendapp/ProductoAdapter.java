package co.edu.unab.vasquez.nodier.tiendapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductoAdapter extends RecyclerView.Adapter {

    ArrayList<Producto> productos;
    // Creamos un constructor del arraylist.
    public ProductoAdapter(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    class ProductoViewHolder extends RecyclerView.ViewHolder{

        TextView txvNombre;
        TextView txvPrecio;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            txvNombre = itemView.findViewById(R.id.txv_nombre);
            //txvPrecio = itemView.findViewById(R.id.txvPrecio);
        }
    }

    //Se define la vista.
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Aquí debemos pasar el contexto pero no podemos pasar el getApplicatio, ni el getBaseContext ni This
        //FORMA 1
        // el parent es el recyclerView
        // de ese parent saque el contexto
        View miVista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent,false);

        return new ProductoViewHolder(miVista);
    }
    // Se asignan los elementos a la vista
    //Bind-ear se conoce como setear la información
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
          ProductoViewHolder miHolder = (ProductoViewHolder ) holder;
          Producto miProducto = productos.get(position);
          miHolder.txvNombre.setText(miProducto.getNombre());
    }

    @Override
    public int getItemCount() {
        return productos.size(); // devolvemos la cantidad de elementos que tenemos guardados.
    }
}
