package jorgemonzon.pruebatecnica.Interfaces;

import jorgemonzon.pruebatecnica.Class.DateTime;
import jorgemonzon.pruebatecnica.Class.ListaUsersObject;
import jorgemonzon.pruebatecnica.Class.UserItem;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by jorge on 11/08/17.
 */

public interface RestClient {

    @GET("getAll")
    Call<ListaUsersObject> getListaUsers();

    @GET("get")
    Call<UserItem> getUser(@Query("id")int busquedaUsuario);

    @GET("remove")
    Call removeUser(@Query("id")int busquedaUsuario);

    @POST("create")
    Call<UserItem> createUser(@Field("Id") int Id,
                              @Field("body") String Name,
                              @Field("userId") DateTime Birthdate);

    @POST("update")
    Call<UserItem> updateUser(@Field("Id") int Id,
                              @Field("body") String Name,
                              @Field("userId") DateTime Birthdate);


}
