package jorgemonzon.pruebatecnica.Utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import jorgemonzon.pruebatecnica.Class.UserItem;
import jorgemonzon.pruebatecnica.Interfaces.IDataListaUsers;
import jorgemonzon.pruebatecnica.Interfaces.IDataUser;
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
    private IDataUser userListener;

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
        Call<List<UserItem>> call = restClient.getListaUsers();
        call.enqueue(new Callback<List<UserItem>>() {
            @Override
            public void onResponse(Call<List<UserItem>> call, Response<List<UserItem>> response) {
                switch (response.code()) {
                    case 200:
                        List<UserItem> data = response.body();
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
            public void onFailure(Call<List<UserItem>> call, Throwable t) {
                listaUsersListener.conexionIncorrecta();

                Log.e("error", t.toString());
            }

        });
    }

    public void getUser(IDataUser listener, int numeroUsuario){
        userListener = listener;
        Call<UserItem> call = restClient.getUser(numeroUsuario);
        call.enqueue(new Callback<UserItem>() {
            @Override
            public void onResponse(Call<UserItem> call, Response<UserItem> response) {
                switch (response.code()) {
                    case 200:
                        UserItem data = response.body();
                        userListener.conexionCorrecta(data);
                        break;
                    case 401:
                        userListener.conexionIncorrecta();
                        break;
                    default:
                        userListener.conexionIncorrecta();

                        break;
                }
            }

            @Override
            public void onFailure(Call<UserItem> call, Throwable t) {
                userListener.conexionIncorrecta();

                Log.e("error", t.toString());
            }

        });
    }
}
