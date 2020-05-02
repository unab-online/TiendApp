package co.edu.unab.toloza.cesar.tiendapp.model.repository;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import co.edu.unab.toloza.cesar.tiendapp.model.db.local.BaseDatos;
import co.edu.unab.toloza.cesar.tiendapp.model.db.local.ProductoDAO;
import co.edu.unab.toloza.cesar.tiendapp.model.entity.Producto;
import co.edu.unab.toloza.cesar.tiendapp.model.db.network.CallBackFirestore;

public class ProductoRepository {

    private BaseDatos roomDB;
    private FirebaseFirestore firestoreDB;

    public ProductoRepository(Context context) {

        roomDB = BaseDatos.obtenerInstancia(context);
        firestoreDB = FirebaseFirestore.getInstance();
    }

    public List<Producto> obtenerTodoRoom(){
        ProductoDAO productoDAO = roomDB.productoDAO();
        return productoDAO.obtenerTodos();
    }

    public void obtenerTodosFirestore(final CallBackFirestore<List<Producto>> callBackFirestore){
        firestoreDB.collection("productos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    List<Producto> productos = new ArrayList<>();
                    if(task.getResult() != null){
                        for (QueryDocumentSnapshot document: task.getResult()) {
                            Producto producto = document.toObject(Producto.class);
                            producto.setId(document.getId());
                            productos.add(producto);
                        }
                    }
                    callBackFirestore.correcto(productos);
                }
            }
        });
    }

    public void escucharTodosFirestore(final CallBackFirestore<List<Producto>> callBackFirestore){
        firestoreDB.collection("productos").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                List<Producto> productos = new ArrayList<>();
                if(queryDocumentSnapshots != null){
                    for (QueryDocumentSnapshot document: queryDocumentSnapshots){
                        Producto producto = document.toObject(Producto.class);
                        producto.setId(document.getId());
                        productos.add(producto);
                    }
                }
                callBackFirestore.correcto(productos);
            }
        });
    }

    public void editarFirestore(final Producto producto, final CallBackFirestore<Producto> callBackFirestore){
        firestoreDB.collection("productos").document(producto.getId()).set(producto).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    callBackFirestore.correcto(producto);
                }
            }
        });
    }

    public void eliminarFirestore(final Producto producto, final CallBackFirestore<Producto> callBackFirestore){
        firestoreDB.collection("productos").document(producto.getId()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    callBackFirestore.correcto(producto);
                }
            }
        });
    }

    public void agregarFirestore(final Producto producto, final CallBackFirestore<Producto> callBackFirestore){
        firestoreDB.collection("productos").add(producto).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if (task.isSuccessful()){
                    callBackFirestore.correcto(producto);
                }
            }
        });
    }
}
