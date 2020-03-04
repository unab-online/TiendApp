package co.edu.unab.amazonappexample.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import co.edu.unab.amazonappexample.Producto;
import co.edu.unab.amazonappexample.R;

public class ProductoAdapter extends RecyclerView.Adapter {
    private ArrayList<Producto> productos;


    public ProductoAdapter(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mivista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto,parent,false);
        return new ViewHolder(mivista);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView,text_precio,text_detalle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView= itemView.findViewById(R.id.text_nombre);
            text_precio= itemView.findViewById(R.id.text_precio);
            text_detalle= itemView.findViewById(R.id.text_detalle);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        ViewHolder miholder= (ViewHolder) holder;
        final Producto producto= productos.get(position);
        miholder.textView.setText(producto.getNombre());
        miholder.text_precio.setText("$"+producto.getPrecio());
        miholder.text_detalle.setText(producto.getDescripcion());
        miholder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(((ViewHolder) holder).textView.getContext(), producto.getNombre(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }
}
