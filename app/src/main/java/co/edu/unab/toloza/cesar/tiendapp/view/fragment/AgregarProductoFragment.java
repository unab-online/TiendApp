package co.edu.unab.toloza.cesar.tiendapp.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Map;

import co.edu.unab.toloza.cesar.tiendapp.R;
import co.edu.unab.toloza.cesar.tiendapp.model.db.network.ProductoAPI;
import co.edu.unab.toloza.cesar.tiendapp.model.db.network.TiendAppService;
import co.edu.unab.toloza.cesar.tiendapp.model.entity.Producto;
import co.edu.unab.toloza.cesar.tiendapp.model.repository.ProductoRepository;
import io.grpc.NameResolver;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class AgregarProductoFragment extends Fragment {

    private EditText nombreProducto, precioProducto, descripcionProducto;
    private Button btnAgregar;
    private TextView textTitulo;
    private ProductoRepository productoRepository;

    public AgregarProductoFragment() {
        // Required empty public constructor
    }

    private void asociarElementos(View view){
        nombreProducto = view.findViewById(R.id.editText_editar_nombre);
        precioProducto = view.findViewById(R.id.editText_editar_precio);
        descripcionProducto = view.findViewById(R.id.editText_editar_desc);
        btnAgregar = view.findViewById(R.id.button_agregar_nuevo);
        textTitulo = view.findViewById(R.id.text_editar_titulo);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_agregar_producto, container, false);
        asociarElementos(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String texto = AgregarProductoFragmentArgs.fromBundle(getArguments()).getPrueba();
        textTitulo.setText(texto);

        productoRepository = new ProductoRepository(getContext());

        btnAgregar.setOnClickListener(v -> {
            String nombre = nombreProducto.getText().toString();
            String descripcion = descripcionProducto.getText().toString();
            double precio = Double.valueOf(precioProducto.getText().toString());
            Producto nuevoProducto = new Producto(nombre, descripcion, precio);

            productoRepository.agregarProductoAPI(nuevoProducto, respuesta -> {
                Navigation.findNavController(getView()).navigate(AgregarProductoFragmentDirections.actionAgregarProductoFragmentToNavegacionProductos());
            });
        });
    }
}
