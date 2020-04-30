package co.edu.unab.saavedra.juan.tiendapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ProductoRepository {

    BaseDatos dbRoom;
    FirebaseFirestore firestoreDB;

    public ProductoRepository(Context contexto) {
        dbRoom = BaseDatos.obtenerInstancia(contexto);
        firestoreDB = FirebaseFirestore.getInstance();
    }

    public List<Producto> obtenerTodosRoom(){
        ProductoDAO productoDAO = dbRoom.productoDAO();
        return productoDAO.obtenerTodos();
    }

    public void obtenerTodosFirestore(final CallBackFirestore<List<Producto>> callBackFirestore){
        firestoreDB.collection("productos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    List<Producto> productos = new ArrayList<>();
                    if(task.getResult()!=null) {
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

    public void escucharTodos(final CallBackFirestore<List<Producto>> callBackFirestore){
        firestoreDB.collection("productos").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                List<Producto> productos = new ArrayList<>();
                if(queryDocumentSnapshots!=null) {
                    for (QueryDocumentSnapshot documento : queryDocumentSnapshots) {
                        Producto miProducto = documento.toObject(Producto.class);
                        miProducto.setId(documento.getId());
                        productos.add(miProducto);
                    }
                }
                callBackFirestore.correcto(productos);
            }
        });
    }

    public void editarFirestore(final Producto miProducto, final CallBackFirestore<Producto> callBackFirestore){
        firestoreDB.collection("productos").document(miProducto.getId()).set(miProducto).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    callBackFirestore.correcto(miProducto);
                }
            }
        });
    }

    public void eliminarFirestore(final Producto miProducto, final CallBackFirestore<Producto> callBackFirestore){
        firestoreDB.collection("productos").document(miProducto.getId()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    callBackFirestore.correcto(miProducto);
                }
            }
        });
    }
}
