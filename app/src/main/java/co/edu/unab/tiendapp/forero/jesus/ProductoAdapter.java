package co.edu.unab.tiendapp.forero.jesus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductoAdapter extends RecyclerView.Adapter {

    class ProductoViewHolder extends RecyclerView.ViewHolder{
        TextView txvNombre;
        TextView txvPrecio;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            txvNombre=itemView.findViewById(R.id.txv_nombre);
            txvPrecio=itemView.findViewById(R.id.txv_precio);
        }
    }
    @NonNull

    ArrayList<Producto> productos;
    onItemClickListener miEscuchador;


    public ProductoAdapter(@NonNull ArrayList<Producto> productos, onItemClickListener miEscuchador) {
        this.productos = productos;
        this.miEscuchador = miEscuchador;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View miVista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto,parent,false);
        return new ProductoViewHolder(miVista);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ProductoViewHolder miHolder= (ProductoViewHolder) holder;
        final Producto miProducto = productos.get(position);
        miHolder.txvNombre.setText(miProducto.getNombre());
        miHolder.txvPrecio.setText(miProducto.getPrecio().toString());

        miHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miEscuchador.OnItemClick(miProducto,position);
            }
        });


    }

    @Override
    public int getItemCount() {

        return productos.size();

    }

    interface  onItemClickListener{
        void OnItemClick(Producto miProducto, int position);
    }
}
