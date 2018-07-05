package ru.ftc.android.shifttemple.network;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.users.data.UsersLocalDataSourceImpl;
import ru.ftc.android.shifttemple.features.users.data.UsersLocalRepositoryImpl;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:32
 */

public final class RetrofitProvider {

    private static final String BASE_URL = "http://ksware.ru/sandbox/gf-api/";

    private final Retrofit retrofit;


    public RetrofitProvider(final Context context) {
        // TODO: ask it is normal?
        String token = (new UsersLocalRepositoryImpl(new UsersLocalDataSourceImpl(context))).getUserToken();
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(createClient(token))
                .build();
    }
    //TODO: move interceptor
    private OkHttpClient createClient(final String token) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        builder.addInterceptor(logInterceptor);

        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();



                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("token", token)
                        .addEncodedPathSegments("/") // TODO: remove it for production api
                        .build();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .url(url);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        return builder.build();

    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

}