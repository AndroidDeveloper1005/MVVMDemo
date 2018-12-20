package com.example.kamanakulkarni.mvvmdemo.model.network_call;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("getProducts/")
    Observable<Response> getProducts();
}
