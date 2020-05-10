package co.edu.unab.vasquez.nodier.tiendapp.model.repository;

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

import co.edu.unab.vasquez.nodier.tiendapp.model.bd.local.BaseDatos;
import co.edu.unab.vasquez.nodier.tiendapp.model.bd.network.CallBackFirestore;
import co.edu.unab.vasquez.nodier.tiendapp.model.entity.Producto;

public class ProductoRepository {

    BaseDatos bdROOM;
    FirebaseFirestore bdFirestore;

    public ProductoRepository(Context contexto) {
        bdROOM = BaseDatos.obtenerInstancia(contexto);
        bdFirestore = FirebaseFirestore.getInstance();
    }

    // Si quiero tener el metodo que devuelva todos los productos

    /*public List<Producto> obtenerTodosRoom() {
        ProductoDAO productoDAO = bdROOM.productoDAO();
        return productoDAO.obtenerTodos();
    }
*/
    public void obtenerTodosFirestore(final CallBackFirestore<List<Producto>> callBackFirestore) {
        bdFirestore.collection("productos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    //no se puede un Lst.Nodi
                    /**productos = new List<Producto>() {}*/
                    //Pero si esto
                    List<Producto> productos = new ArrayList<>();
                    //Recorremos la respuesta
                    //el Task nos devuelve un listado de los doumentos de esa colección
                    if (task.getResult() != null) {
                        for (QueryDocumentSnapshot documento : task.getResult()) {
                            //Los datos que llegan en ql query conviertalos y teniendo en cuenta los
                            // atributos definidos en la clase Productos... y guardelos en la variable.
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

   /* public void escucharTodosFirestore(final CallBackFirestore<List<Producto>> callBackFirestore) {
        bdFirestore.collection("productos").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                List<Producto> productos = new ArrayList<>();
                if (queryDocumentSnapshots != null) {
                    for (QueryDocumentSnapshot documento : queryDocumentSnapshots) {
                        //Los datos que llegan en ql query conviertalos y teniendo en cuenta los
                        // atributos definidos en la clase Productos... y guardelos en la variable.
                        Producto miProducto = documento.toObject(Producto.class);
                        miProducto.setId(documento.getId());
                        productos.add(miProducto);
                    }
                }
                callBackFirestore.correcto(productos);
            }
        });
    }*/

    public void editarFirestore(final Producto miProducto, final CallBackFirestore<Producto> callBackFirestore) {
        bdFirestore.collection("productos").document(miProducto.getId()).set(miProducto).
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
        bdFirestore.collection("productos").document(miProducto.getId()).delete().
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
        bdFirestore.collection("productos").add(miProducto).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if (task.isSuccessful()) {
                    callBackFirestore.correcto(miProducto);
                }
            }
        });
    }

}






