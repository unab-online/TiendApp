package co.edu.unab.vasquez.nodier.tiendapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter {

    List<Producto> productos;
    NombreDeInterface miEscuchador;

    public ProductoAdapter(List<Producto> productos, NombreDeInterface miEscuchador) {
        this.productos = productos;
        this.miEscuchador = miEscuchador;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    /* // Creamos un constructor del arraylist.
    public ProductoAdapter(ArrayList<Producto> productos, NombreDeInterface miEscuchador) {
        this.productos = productos;
    }*/

    class ProductoViewHolder extends RecyclerView.ViewHolder{

        TextView txvNombre;
        TextView txvPrecio;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            txvNombre = itemView.findViewById(R.id.txv_nombre);
            txvPrecio = itemView.findViewById(R.id.txv_precio);
        }
    }

    //Se define la vista.
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Aquí debemos pasar el contexto pero no podemos pasar el getApplicatio, ni el getBaseContext ni This
        //FORMA 1
        // el parent es el recyclerView
        // de ese parent saque el contexto
        View miVista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent,false);

        return new ProductoViewHolder(miVista);
    }
    // Se asignan los elementos a la vista
    //Bind-ear se conoce como setear la información
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder,final int position) {

          final ProductoViewHolder miHolder = (ProductoViewHolder ) holder;
          final Producto miProducto = productos.get(position);

          miHolder.txvNombre.setText(miProducto.getNombre());
          miHolder.txvPrecio.setText("$ "+miProducto.getPrecio());

         miHolder.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 miEscuchador.metodoParaelItemClick(miProducto,position);
                 Log.d("Prueba-click","Hice click en "+miProducto);
             }
         });


    }

    @Override
    public int getItemCount() {
        return productos.size(); // devolvemos la cantidad de elementos que tenemos guardados.
    }

    interface NombreDeInterface{
        //Le podemos pasar os parámetros que queramos
        void metodoParaelItemClick(Producto miProducto, int posicion);
    }

}
