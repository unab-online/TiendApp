package co.edu.unab.tiendapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//Heredo el RecyclerView. Adapter
public class ProductoAdapter extends RecyclerView.Adapter {

    ArrayList<Producto> productos;

    //Intancia de la interfaz y el  constructor del adapter, esto va casi al final
    onItemClickListener miEscuchador;
    public ProductoAdapter(ArrayList<Producto> productos, onItemClickListener miEscuchador) {
        this.productos = productos;
        this.miEscuchador = miEscuchador;
    }

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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        //Vamos a indicar de los datos que le pasé, qué va en qué parte
       final ProductoViewHolder miHolder= (ProductoViewHolder) holder;
        final Producto miProducto= productos.get(position);
        miHolder.txvNombre.setText(miProducto.getNombre());
        miHolder.txvPrecio.setText("$"+ miProducto.getPrecio());

        //Para el itemclick que  es la mas profesional
        miHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miEscuchador.onItemClick(miProducto, position);
            }
        });

    }

    @Override
    public int getItemCount() {

        //Cuantos elementos voy a tener en el Recyclerview
        return  this.productos.size();
    }

    //crear interfaz onitemclick
    interface onItemClickListener{//cualquier nombre
        void onItemClick(Producto miProducto, int posicion); //cualquier nombre x2


    }

}
