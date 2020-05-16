package co.edu.unab.toloza.cesar.tiendapp.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import co.edu.unab.toloza.cesar.tiendapp.R;
import co.edu.unab.toloza.cesar.tiendapp.model.entity.Producto;
import co.edu.unab.toloza.cesar.tiendapp.model.repository.ProductoRepository;
import co.edu.unab.toloza.cesar.tiendapp.view.activity.EditarActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditarProductoFragment extends Fragment {

    private TextView titulo;
    private EditText nombre, precio, descripcion;
    private Button btnEditar, btnEliminar;
    private ProductoRepository productoRepository;

    public EditarProductoFragment() {
        // Required empty public constructor
    }

    private void asociarElementos(View view){
        titulo = view.findViewById(R.id.text_editar_titulo);
        nombre = view.findViewById(R.id.editText_editar_nombre);
        precio = view.findViewById(R.id.editText_editar_precio);
        descripcion = view.findViewById(R.id.editText_editar_desc);
        btnEditar = view.findViewById(R.id.button_editar);
        btnEliminar = view.findViewById(R.id.button_borrar);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_editar_producto, container, false);
        asociarElementos(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        productoRepository = new ProductoRepository(getContext());

        final Producto producto = EditarProductoFragmentArgs.fromBundle(getArguments()).getProducto();

        titulo.setText(getString(R.string.titulo_editar, producto.getNombre()));
        nombre.setText(producto.getNombre());
        precio.setText(String.valueOf(producto.getPrecio()));
        descripcion.setText(producto.getDescripcion());

        btnEditar.setOnClickListener(v -> {
            producto.setNombre(nombre.getText().toString());
            producto.setPrecio(Double.valueOf(precio.getText().toString()));
            producto.setDescripcion(descripcion.getText().toString());

            productoRepository.editarProductoAPI(producto, respuesta -> {
                Navigation.findNavController(getView())
                        .navigate(EditarProductoFragmentDirections.actionEditarProductoFragmentToNavegacionProductos());
            });
        });

        btnEliminar.setOnClickListener(v -> {
            productoRepository.eliminarProductoAPI(producto, respuesta -> {
                Navigation.findNavController(getView())
                        .navigate(EditarProductoFragmentDirections.actionEditarProductoFragmentToNavegacionProductos());
            });
        });
    }
}
