package co.edu.unab.leal.jakson.silviapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;

public class ProductoAdapater extends RecyclerView.Adapter {

    private ArrayList<Producto> productos;
    public ProductoAdapater (ArrayList <Producto> productos){
        this.productos = productos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_detalle, parent, false);
        return new ProductoViewHolder(vista);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ProductoViewHolder holderBind = (ProductoViewHolder) holder;
        Producto prodBind = productos.get(position);
        holderBind.textViewNombre.setText(prodBind.getNombre());
        holderBind.textViewDescripcion.setText(prodBind.getDescripcion());
        holderBind.textViewPrecio.setText("$"+prodBind.getPrecio());

    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    class ProductoViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewNombre;
        private TextView textViewDescripcion;
        private TextView textViewPrecio;



        public ProductoViewHolder (@NonNull View itemView){
            super(itemView);

            textViewNombre = itemView.findViewById(R.id.textView_nombre);
            textViewDescripcion = itemView.findViewById(R.id.textView_descripcion);
            textViewPrecio = itemView.findViewById(R.id.textView_precio);

        }

    }

}


