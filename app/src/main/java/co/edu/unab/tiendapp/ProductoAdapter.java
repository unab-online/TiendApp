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

public class ProductoAdapter extends RecyclerView.Adapter {

    ArrayList<Producto> productos;
    NombreDeInterface miEscuchador;

    public ProductoAdapter(ArrayList<Producto> productos, NombreDeInterface miEscuchador) {
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
            txvNombre = itemView.findViewById(R.id.txv_nombre);
            txvPrecio = itemView.findViewById(R.id.txv_precio);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.d("Prueba-click", "Hice clic");
//                }
//            });

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View miVista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);

        return new ProductoViewHolder(miVista);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        final ProductoViewHolder miHolder = (ProductoViewHolder) holder;

        final Producto miProducto = productos.get(position);
        miHolder.txvNombre.setText(miProducto.getNombre());
        miHolder.txvPrecio.setText("$"+miProducto.getPrecio());

        miHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miEscuchador.metodoParaelItemClick(miProducto, position);
            }
        });

        miHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("Prueba-click", "Hice clic sostenido "+miProducto);
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    interface NombreDeInterface{
        void metodoParaelItemClick(Producto miproducto, int posicion);
    }


}
