package co.edu.unab.leal.jakson.silviapp.model.repository;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import co.edu.unab.leal.jakson.silviapp.model.entity.Producto;
import co.edu.unab.leal.jakson.silviapp.model.db.local.BaseDatos;
import co.edu.unab.leal.jakson.silviapp.model.db.local.ProductoDAO;
import co.edu.unab.leal.jakson.silviapp.model.db.network.CallBackFirestore;

public class ProductoRepository {

    BaseDatos bdROOM;
    FirebaseFirestore fireDB;

    public ProductoRepository(Context contexto) {
        bdROOM = BaseDatos.obtenerInstancia(contexto);
        fireDB = FirebaseFirestore.getInstance();
    }

    public List<Producto> obtenerTodosRoom() {
        ProductoDAO productoDAO = bdROOM.productoDAO();
        return productoDAO.obtenerTodos();
    }

    public void obtenerTodosFirestore(final CallBackFirestore<List<Producto>> callBackFirestore) {
        fireDB.collection("productos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<Producto> productos = new ArrayList<>();
                    if (task.getResult() != null) {
                        for (QueryDocumentSnapshot documento : task.getResult()) {
                            Producto miProducto = documento.toObject(Producto.class);
                            miProducto.setId(documento.getId());
                            productos.add(miProducto);
                        }
                    }
                    callBackFirestore.correcto(productos);
                }
            }
        });
    }

    public void editarFirestore(final Producto miProducto, final CallBackFirestore<Producto> callBackFirestore) {
        fireDB.collection("productos").document(miProducto.getId()).set(miProducto).
                addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            callBackFirestore.correcto(miProducto);
                        }
                    }
                });
    }

    public void eliminarFirestore(final Producto miProducto, final CallBackFirestore<Producto> callBackFirestore) {
        fireDB.collection("productos").document(miProducto.getId()).delete().
                addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            callBackFirestore.correcto(miProducto);
                        }
                    }
                });
    }

    public void agregarFirestore(final Producto miProducto, final CallBackFirestore<Producto> callBackFirestore) {
        fireDB.collection("productos").add(miProducto).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if (task.isSuccessful()) {
                    callBackFirestore.correcto(miProducto);
                }
            }
        });
    }

}






