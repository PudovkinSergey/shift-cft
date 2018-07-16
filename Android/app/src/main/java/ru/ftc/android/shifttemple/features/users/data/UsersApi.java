package ru.ftc.android.shifttemple.features.users.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import ru.ftc.android.shifttemple.features.books.domain.model.Success;
import ru.ftc.android.shifttemple.features.books.domain.model.Wrapper;
import ru.ftc.android.shifttemple.features.users.domain.model.User;


public interface UsersApi {

    @GET("users/{id}")
    Call<Wrapper<User>> getUser(@Path("id") String id);

    @FormUrlEncoded
    @POST("users/login")
    Call<Wrapper<User>> loginUser(@Field("login") String login,
                                   @Field("password") String password);

    @POST("users/check_token")
    Call<Wrapper<Success>> checkUserToken(@Field("token") String token);



    @POST("users/register")
    Call<Wrapper<User>> createUser(@Body User user);


}
