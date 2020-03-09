package co.edu.unab.vasquez.nodier.tiendapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductorAdapter extends RecyclerView.Adapter {

    ArrayList<Producto> productos;// son los productos que voy a mostrar
    //
    OnItemClickListener miEscuchador;

    //crear un constructor


    public ProductorAdapter(ArrayList<Producto> productos, OnItemClickListener miEscuchador) {
        this.productos = productos;
        this.miEscuchador = miEscuchador;
    }

    public ProductorAdapter(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    class ProductoViewHolder extends  RecyclerView.ViewHolder{

        TextView txvNombre;
        TextView txvPrecio;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            txvNombre = itemView.findViewById(R.id.txv_nombre);
            txvPrecio = itemView.findViewById(R.id.txv_Precio);

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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
      //decir en que lado va
        ProductoViewHolder miHolder = (ProductoViewHolder) holder; // voy a castear el objeto generico que le llega de la clase
        final Producto miProducto = productos.get(position);
        miHolder.txvNombre.setText(miProducto.getNombre());
        miHolder.txvPrecio.setText(miProducto.getPrecio()+"");

        //no es la correcta, yo quiero que le haga click se ejecute
        miHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Log.d("Prueba-click", "Hice clic"+miProducto);
                miEscuchador.onItemClick(miProducto,position);
            }
        });

        miHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("Prueba-click", "Hice clic sostenido"+miProducto);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return productos.size();
        //contar elementos
    }

    //se implementa esta clase cuando usemos recycler

    // trabar el código separando el onclicklistener

    interface OnItemClickListener{
        void onItemClick(Producto miProducto, int posicion);
    }
}
