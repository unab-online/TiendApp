package co.edu.unab.leal.jakson.silviapp.view.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import co.edu.unab.leal.jakson.silviapp.model.entity.Producto;
import co.edu.unab.leal.jakson.silviapp.R;

public class ProductoAdapter extends RecyclerView.Adapter {

    private List<Producto> productos;
    onItemClickListener espichador;

    public ProductoAdapter(List<Producto> productos, onItemClickListener espichador) {
        this.productos = productos;
        this.espichador = espichador;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
        this.notifyDataSetChanged();
    }

    public ProductoAdapter(List<Producto> productos) {
        this.productos = productos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_detalle, parent, false);
        return new ProductoViewHolder(vista);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        ProductoViewHolder holderBind = (ProductoViewHolder) holder;
        final Producto prodBind = productos.get(position);
        holderBind.textViewNombre.setText(prodBind.getNombre());
        holderBind.textViewDescripcion.setText(prodBind.getDescripcion());
        holderBind.textViewPrecio.setText("$" + prodBind.getPrecio());

        holderBind.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                espichador.onItemClick(prodBind, position);
            }
        });

        holderBind.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("tap", "click sostenido en : " + prodBind);
                return false;
            }

        });

    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public interface onItemClickListener {

        void onItemClick(Producto producto, int posicion);

    }

    class ProductoViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewNombre;
        private TextView textViewDescripcion;
        private TextView textViewPrecio;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewNombre = itemView.findViewById(R.id.textView_nombre);
            textViewDescripcion = itemView.findViewById(R.id.textView_descripcion);
            textViewPrecio = itemView.findViewById(R.id.textView_precio);

        }

    }

}


