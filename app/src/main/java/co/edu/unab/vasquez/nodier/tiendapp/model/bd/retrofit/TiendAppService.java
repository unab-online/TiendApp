package co.edu.unab.vasquez.nodier.tiendapp.model.bd.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class TiendAppService {

    private static Retrofit instancia;
    private static String URL = "https://tiendapp-97a4b.firebaseio.com/ ";

    public static Retrofit obtenerInstancia(){
        if (instancia ==null){
            instancia = new Retrofit.Builder().baseUrl(URL).
                    addConverterFactory(GsonConverterFactory.create()).build();
        }
        return instancia;
    }
}
