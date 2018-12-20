package com.example.kamanakulkarni.mvvmdemo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;

import com.example.kamanakulkarni.mvvmdemo.App;
import com.example.kamanakulkarni.mvvmdemo.model.DataRepository;
import com.example.kamanakulkarni.mvvmdemo.model.model.Product;
import com.example.kamanakulkarni.mvvmdemo.model.network_call.ProductData;

import java.util.List;

public class ProductViewModel extends AndroidViewModel{

    public MediatorLiveData<List<ProductData>> productObservableData;
    public ObservableBoolean showProgress = new ObservableBoolean(false);
    public ObservableBoolean isLoading = new ObservableBoolean(false);
    public boolean isFromRefresh = false;
    LiveData<List<ProductData>> productLiveData;

    private DataRepository repository;

    public ProductViewModel(@NonNull Application application) {
        super(application);

        productObservableData = new MediatorLiveData<>();

        productObservableData.setValue(null);

        repository = ((App)getApplication()).getDataRepository();
    }

    public void fetchProductData(){

        productLiveData = repository.fetchProductResponse();
        productObservableData.addSource(productLiveData, productObservableData::setValue);
    }

    public LiveData<List<ProductData>> getProductList(){
        return productObservableData;
    }

    public void onRefresh(){
        /*isFromRefresh = true;*/
        isLoading.set(true);
        productObservableData.setValue(null);
        productObservableData.removeSource(productLiveData);
        fetchProductData();
    }

}
