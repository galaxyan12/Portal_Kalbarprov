package com.kalbarprov.portal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIRequestData {

    @GET("link.json")
    Call<List<RedirectModel>> ardRequestLink();
}
