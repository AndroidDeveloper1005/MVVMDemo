package com.example.kamanakulkarni.mvvmdemo;

import android.app.Application;
import android.content.Context;

import com.example.kamanakulkarni.mvvmdemo.model.DataRepository;
import com.example.kamanakulkarni.mvvmdemo.model.network_call.WebServiceClient;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public WebServiceClient getWebServiceClient(){
        return WebServiceClient.getInstance();
    }

    public DataRepository getDataRepository(){
        return DataRepository.getsInstance(getWebServiceClient());
    }
}
