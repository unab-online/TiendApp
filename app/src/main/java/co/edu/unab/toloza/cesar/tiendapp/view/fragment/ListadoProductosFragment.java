package co.edu.unab.toloza.cesar.tiendapp.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.edu.unab.toloza.cesar.tiendapp.R;
import co.edu.unab.toloza.cesar.tiendapp.model.entity.Producto;
import co.edu.unab.toloza.cesar.tiendapp.model.repository.ProductoRepository;
import co.edu.unab.toloza.cesar.tiendapp.view.activity.EditarActivity;
import co.edu.unab.toloza.cesar.tiendapp.view.activity.ListadoActivity;
import co.edu.unab.toloza.cesar.tiendapp.view.activity.LoginActivity;
import co.edu.unab.toloza.cesar.tiendapp.view.adapter.ProductoAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListadoProductosFragment extends Fragment {

    private RecyclerView rvProductos;
    private Button buttonAgrear, buttonCerrarSesion;
    private List<Producto> productos;
    private ProductoRepository productoRepository;
    private ProductoAdapter miAdapter;

    public ListadoProductosFragment() {
        // Required empty public constructor
    }

    private void AsociarElementos(View view){
        rvProductos = view.findViewById(R.id.rv_productos);
        buttonAgrear = view.findViewById(R.id.button_agregar_producto);
        buttonCerrarSesion = view.findViewById(R.id.button_cerrar_sesion);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_listado_productos, container, false);
        AsociarElementos(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        productoRepository = new ProductoRepository(getContext());

        productos = new ArrayList<>();

        miAdapter = new ProductoAdapter(productos, (producto, position) -> {
            Toast.makeText(getContext(), "Hice click " + producto, Toast.LENGTH_SHORT).show();
            Navigation.findNavController(getView())
                    .navigate(ListadoProductosFragmentDirections.actionNavegacionProductosToEditarProductoFragment(producto));
        });

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        rvProductos.setLayoutManager(manager);
        rvProductos.setAdapter(miAdapter);
        rvProductos.setHasFixedSize(true);

        productoRepository.obtenerTodosProductoAPI(respuesta -> miAdapter.setProductos(respuesta));

        buttonCerrarSesion.setOnClickListener(v -> {
            SharedPreferences misPreferencias = getActivity().getSharedPreferences(getString(R.string.preferencias), Context.MODE_PRIVATE);
            SharedPreferences.Editor miEditor = misPreferencias.edit();
            miEditor.clear();
            miEditor.apply();
            Intent in = new Intent(getContext(), LoginActivity.class);
            startActivity(in);
            getActivity().finish();
        });

        buttonAgrear.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(ListadoProductosFragmentDirections.actionNavegacionProductosToAgregarProductoFragment("Texto de prueba"));
        });

    }
}
