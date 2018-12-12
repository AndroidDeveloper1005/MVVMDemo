package com.example.kamanakulkarni.mvvmdemo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;

import com.example.kamanakulkarni.mvvmdemo.model.Product;
import com.example.kamanakulkarni.mvvmdemo.network_response.NetworkResponse;
import com.example.kamanakulkarni.mvvmdemo.network_response.WebServiceClient;

import java.util.List;

public class IndexViewModel extends AndroidViewModel implements WebServiceClient.INetworkListener{

    public static final int FETCH_PRODUCT_CODE=1001;

    public MutableLiveData<List<Product>> productData = new MutableLiveData<>();
    public MutableLiveData<NetworkResponse> liveData = new MutableLiveData<>();
    public ObservableBoolean showProgress = new ObservableBoolean(false);
    public ObservableBoolean isLoading = new ObservableBoolean(false);
    private boolean isFromRefresh = false;

    public IndexViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetchProductData(){
        if (!isFromRefresh){
            showProgress.set(true);
        }else {
            isLoading.set(true);
            showProgress.set(false);
        }
        WebServiceClient.getInstance(getApplication().getApplicationContext()).getProductData(this, FETCH_PRODUCT_CODE);
    }

    @Override
    public void onSuccess(String response, int requestCode) {
        NetworkResponse networkResponse = new NetworkResponse(response);

        if (networkResponse.getProductList()!=null && networkResponse.getProductList().size()>0){

            if (!isFromRefresh && showProgress.get()){
                showProgress.set(false);
            }else if (isFromRefresh && isLoading.get()){
                isLoading.set(false);
            } else {
                showProgress.set(false);
                isLoading.set(false);
            }

            liveData.setValue(networkResponse);
        }
    }

    @Override
    public void onFailure(String message, int requestCode) {

        isLoading.set(false);
        showProgress.set(false);
    }

    public MutableLiveData<NetworkResponse> getLiveData(){
        return liveData;
    }

    public MutableLiveData<List<Product>> getProductList(){
        return productData;
    }

    public void onRefresh(){
        isFromRefresh = true;
        fetchProductData();
    }
}
