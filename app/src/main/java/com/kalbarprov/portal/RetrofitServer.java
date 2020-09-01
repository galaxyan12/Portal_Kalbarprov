package com.kalbarprov.portal;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServer {
    private static final String BASE_URL = "https://litbang.kalbarprov.go.id/public/";
    private static Retrofit retrofit = null;

    public static Retrofit connectRetrofit(){
        if (retrofit == null ){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client((new OkHttpClient.Builder()).build())
                    .build();
        }
        return retrofit;
    }
}
