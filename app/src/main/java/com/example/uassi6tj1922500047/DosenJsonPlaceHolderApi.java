package com.example.uassi6tj1922500047;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface DosenJsonPlaceHolderApi {
    @GET("dosen.php")
    Call<List<DosenPost>> getPosts(

    );
    @GET("barang.php")
    Call<List<DosenPost>> getPosts(@QueryMap Map<String, String> parameters);
}
