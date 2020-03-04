package co.edu.unab.tiendapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//Heredo el RecyclerView. Adapter
public class ProductoAdapter extends RecyclerView.Adapter {

    ArrayList<Producto> productos;

    public ProductoAdapter(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    class ProductoViewHolder extends RecyclerView.ViewHolder{
        TextView txvNombre;
        TextView txvPrecio;


        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            //Agragamos lo siguiente, el manejador de la vista del layout item_producto:
            txvNombre= itemView.findViewById(R.id.txv_nombre);
            txvPrecio= itemView.findViewById(R.id.txv_precio);

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Vamos a definir el layout que va a tener cada elemento de la lista

        //Identifico el layout y lo guardo en una vista
        View miVista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);

        //Creo la clase del viewholder y le paso la vista creada con anterioridad
        return new ProductoViewHolder(miVista);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //Vamos a indicar de los datos que le pasé, qué va en qué parte
        ProductoViewHolder miHolder= (ProductoViewHolder) holder;
        Producto miProducto= productos.get(position);
        miHolder.txvNombre.setText(miProducto.getNombre());
        miHolder.txvPrecio.setText("$"+ miProducto.getPrecio());
    }

    @Override
    public int getItemCount() {

        //Cuantos elementos voy a tener en el Recyclerview
        return  this.productos.size();
    }
}
