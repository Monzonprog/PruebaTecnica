package jorgemonzon.pruebatecnica.Utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import jorgemonzon.pruebatecnica.Class.UserItem;
import jorgemonzon.pruebatecnica.Interfaces.IDataListaUsers;
import jorgemonzon.pruebatecnica.Interfaces.IDataUser;
import jorgemonzon.pruebatecnica.Interfaces.IDataUserBorrar;
import jorgemonzon.pruebatecnica.Interfaces.IDataUserCrear;
import jorgemonzon.pruebatecnica.Interfaces.IDataUserModificar;
import jorgemonzon.pruebatecnica.Interfaces.IDataUserOpcionesCard;
import jorgemonzon.pruebatecnica.Interfaces.RestClient;
import okhttp3.ResponseBody;
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
    private IDataUserBorrar borrarListener;
    private IDataUserCrear crearListener;
    private IDataUserModificar modificarListener;

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
                        if (data == null) {
                            userListener.conexionNoEncontrado();

                        } else {
                            userListener.conexionCorrecta(data);

                        }
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

    public void removeUser(IDataUserBorrar listener, int numeroUsuario) {
        borrarListener = listener;
        Call<ResponseBody> call = restClient.removeUser(numeroUsuario);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                switch (response.code()) {
                    case 200:
                        borrarListener.conexionCorrectaBorrarUser();
                        break;
                    case 401:
                        borrarListener.conexionIncorrectaBorrarUser();
                        break;
                    default:
                        borrarListener.conexionIncorrectaBorrarUser();

                        break;
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                borrarListener.conexionIncorrectaBorrarUser();

                Log.e("error", t.toString());
            }

        });
    }

    public void createUser(IDataUserCrear listener, String nombreUsuario, String fechaUsuario) {
        crearListener = listener;
        Call<UserItem> call = restClient.createUser(nombreUsuario, fechaUsuario);
        call.enqueue(new Callback<UserItem>() {
            @Override
            public void onResponse(Call<UserItem> call, Response<UserItem> response) {
                switch (response.code()) {
                    case 200:
                        UserItem data = response.body();
                        crearListener.conexionCorrectaCrearUser(data);
                        break;
                    case 401:
                        crearListener.conexionIncorrectaCrearUser();
                        break;
                    default:
                        crearListener.conexionIncorrectaCrearUser();

                        break;
                }
            }

            @Override
            public void onFailure(Call<UserItem> call, Throwable t) {
                crearListener.conexionIncorrectaCrearUser();

                Log.e("error", t.toString());
            }

        });
    }

    public void updateUser(IDataUserModificar listener, int id, String nombreUsuario, String fechaUsuario) {
        modificarListener = listener;
        Call<UserItem> call = restClient.updateUser(id, nombreUsuario, fechaUsuario);
        call.enqueue(new Callback<UserItem>() {
            @Override
            public void onResponse(Call<UserItem> call, Response<UserItem> response) {
                switch (response.code()) {
                    case 200:
                        UserItem data = response.body();
                        modificarListener.conexionCorrectaModificarUser(data);
                        break;
                    case 401:
                        modificarListener.conexionIncorrectaModificarUser();
                        break;
                    default:
                        modificarListener.conexionIncorrectaModificarUser();

                        break;
                }
            }

            @Override
            public void onFailure(Call<UserItem> call, Throwable t) {
                modificarListener.conexionIncorrectaModificarUser();

                Log.e("error", t.toString());
            }

        });
    }
}
