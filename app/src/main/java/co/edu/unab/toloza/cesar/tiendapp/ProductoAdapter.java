package co.edu.unab.toloza.cesar.tiendapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter {

    List<Producto> productos;
    OnItemClickListener onItemClickListener;

    public ProductoAdapter(List<Producto> productos, OnItemClickListener onItemClickListener) {
        this.productos = productos;
        this.onItemClickListener = onItemClickListener;
    }

    class ProductoViewHolder extends RecyclerView.ViewHolder{
        TextView txvNombre, txvPrecio;
        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            txvNombre = itemView.findViewById(R.id.tv_nombre);
            txvPrecio = itemView.findViewById(R.id.tv_precio);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View miVsta = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);
        return new ProductoViewHolder(miVsta);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ProductoViewHolder miHolder = (ProductoViewHolder) holder;
        final Producto miProducto = productos.get(position);
        miHolder.txvNombre.setText(miProducto.getNombre());
        miHolder.txvPrecio.setText("$"+miProducto.getPrecio());

        miHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(miProducto, position);
            }
        });


        /*miHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Prueba-Click", "Hice click "+ miProducto);
            }
        });
        miHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("Prueba-Click", "Hice click-sostenido "+ miProducto);
                return false;
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    interface OnItemClickListener{
        void onItemClick(Producto producto, int position);
    }
}
