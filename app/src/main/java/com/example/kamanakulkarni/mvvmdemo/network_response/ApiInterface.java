package com.example.kamanakulkarni.mvvmdemo.network_response;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("getProducts/")
    Call<ResponseBody> getProducts();
}
