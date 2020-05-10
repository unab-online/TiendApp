package co.edu.unab.rey.carlos.crackapp.model.bd.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class TiendAppService {

    private static Retrofit instancia;
    private static String URL = "https://tiendapp-8218c.firebaseio.com/";

    public static Retrofit obtenerInstancias(){
        if (instancia==null){
            instancia = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instancia;
    }
}

