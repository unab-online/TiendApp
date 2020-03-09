package co.edu.unab.gonzalez.carlos.tiendapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductoAdapter extends RecyclerView.Adapter {


    ArrayList<Producto> productos;
    interfaceescucghadorclick miEscuchador;

    public ProductoAdapter(ArrayList<Producto> productos, interfaceescucghadorclick miEscuchador) {
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
            txvNombre = itemView.findViewById(R.id.txv1);
            txvPrecio = itemView.findViewById(R.id.txv2);

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("hice-clic","hice clic");
                }
            });*/

        }
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View miVista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto,parent,false);// obtener el contexto

        return new ProductoViewHolder(miVista);
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        ProductoViewHolder miHolder = (ProductoViewHolder)holder;
        final Producto miProducto = productos.get(position);
        miHolder.txvNombre.setText(miProducto.getNombre());
        miHolder.txvPrecio.setText(""+miProducto.getPrecio());

        miHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("hice-clic","hice clic");
                miEscuchador.metodoclickear(miProducto, position);
            }
        });

        miHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("hice-clic-sos","hice clic sostenido");
                return false;
            }
        });


    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    interface interfaceescucghadorclick{
        void metodoclickear(Producto miproducto, int posicion);

    }
}
