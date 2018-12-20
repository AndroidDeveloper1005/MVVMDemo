package com.example.kamanakulkarni.mvvmdemo.model.network_call;

import android.content.Context;

import com.google.android.gms.common.api.Api;

import java.io.IOException;

import io.reactivex.Observable;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebServiceClient{

    private Retrofit retrofit;

    private static ApiInterface apiInterface;

    private static WebServiceClient instance;


    public static ApiInterface getApiInterface() {
        return apiInterface;
    }

    public static void setApiInterface(ApiInterface apiInterface) {
        WebServiceClient.apiInterface = apiInterface;
    }

    private WebServiceClient(){

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://31b64036-d693-4a1e-869a-bc97ed9aa24b.mock.pstmn.io/")
                .build();

//        setApiInterface(apiInterface);

    }

/*
    public static ApiInterface provideApi(Retrofit retrofit){

        if (apiInterface !=null){
            synchronized (Api.class){
                if (apiInterface!=null){
                }
            }
        }

        return apiInterface;
    }
*/

    public static WebServiceClient getInstance(){

        if (instance==null){
            instance = new WebServiceClient();
        }

        return instance;
    }

    public Observable<Response> getProductData(){
        apiInterface = retrofit.create(ApiInterface.class);
/*

        if (getApiInterface()!=null)
            return apiInterface.getProducts();
*/

        return apiInterface.getProducts();
    }

}
