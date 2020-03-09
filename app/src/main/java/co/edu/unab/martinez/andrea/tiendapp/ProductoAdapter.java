package co.edu.unab.martinez.andrea.tiendapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductoAdapter extends RecyclerView.Adapter {

    ArrayList<Producto> productos;
    onItemClickListener miListener;

    public ProductoAdapter(ArrayList<Producto> productos, onItemClickListener miListener) {
        this.productos = productos;
        this.miListener = miListener;
    }
    class ProductoViewHolder extends RecyclerView.ViewHolder{

        TextView txvNombre;
        TextView txvPrecio;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            txvNombre = itemView.findViewById(R.id.txv_nombre);
            txvPrecio = itemView.findViewById(R.id.txv_precio);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View miVista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);

        return new ProductoViewHolder(miVista);
    } // Definir vista

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        ProductoViewHolder miHolder =(ProductoViewHolder)holder;
        final Producto miProducto = productos.get(position);
        miHolder.txvNombre.setText(miProducto.getNombre());
        miHolder.txvPrecio.setText("$"+miProducto.getPrecio());

        miHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miListener.onItemClick(miProducto,position);
            }
        });

        miHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("Prueba-click","Hice click sostenido"+miProducto);
                return false;
            }
        });

    } // Se asignan los elementos segun la vista

    @Override
    public int getItemCount() {
        return productos.size();
    } // Cuantos elementos

    interface onItemClickListener{ // Puede tener cualquier nombre
        void onItemClick(Producto miproducto, int posicion);

    }
}
