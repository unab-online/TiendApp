package co.edu.unab.vasquez.nodier.tiendapp.model.bd.network;

import java.util.List;

public interface CallBackFirestore<T> {

    //void obtenerTodos (List<Producto> productos);
    void correcto(T respuesta);

}
