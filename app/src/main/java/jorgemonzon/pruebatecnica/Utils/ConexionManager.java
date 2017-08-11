package jorgemonzon.pruebatecnica.Utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jorgemonzon.pruebatecnica.Class.ListaUsersObject;
import jorgemonzon.pruebatecnica.Interfaces.IDataListaUsers;
import jorgemonzon.pruebatecnica.Interfaces.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jorge on 11/08/17.
 */

public class ConexionManager {

    private Gson gson;
    private Retrofit retrofit;
    private RestClient restClient;
    private IDataListaUsers listaUsersListener;

    public ConexionManager(String url) {

        gson = new GsonBuilder()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        restClient = retrofit.create(RestClient.class);
    }


    public void getListaUsers(IDataListaUsers listener){
        listaUsersListener = listener;
        Call<ListaUsersObject> call = restClient.getListaUsers();
        call.enqueue(new Callback<ListaUsersObject>() {
            @Override
            public void onResponse(Call<ListaUsersObject> call, Response<ListaUsersObject> response) {
                switch (response.code()) {
                    case 200:
                        ListaUsersObject data = response.body();
                        listaUsersListener.conexionCorrecta(data);
                        break;
                    case 401:
                        listaUsersListener.conexionIncorrecta();
                        break;
                    default:
                        listaUsersListener.conexionIncorrecta();

                        break;
                }
            }

            @Override
            public void onFailure(Call<ListaUsersObject> call, Throwable t) {
                listaUsersListener.conexionIncorrecta();

                Log.e("error", t.toString());
            }

        });
    }
}
