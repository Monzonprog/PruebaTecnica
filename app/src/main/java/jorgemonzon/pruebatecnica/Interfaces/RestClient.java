package jorgemonzon.pruebatecnica.Interfaces;

import java.util.List;

import jorgemonzon.pruebatecnica.Class.UserItem;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by jorge on 11/08/17.
 */

public interface RestClient {

    @GET("getall")
    Call<List<UserItem>> getListaUsers();

    @GET("get")
    Call<UserItem> getUser(@Query("id")int busquedaUsuario);

    @GET("remove")
    Call <ResponseBody>removeUser(@Query("id")int busquedaUsuario);

    @FormUrlEncoded
    @POST("create")
    Call<UserItem> createUser(@Field("name") String Name,
                              @Field("birthdate") String Birthdate);

    @POST("update")
    Call<UserItem> updateUser(@Field("id") int Id,
                              @Field("name") String Name,
                              @Field("birthdate") String Birthdate);


}
