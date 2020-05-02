package co.edu.unab.toloza.cesar.tiendapp.model.db.network;

import java.util.List;

public interface CallBackFirestore<T> {

    void correcto (T respuesta);
}
