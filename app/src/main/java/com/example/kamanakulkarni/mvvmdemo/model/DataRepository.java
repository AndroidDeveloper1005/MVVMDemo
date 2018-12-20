package com.example.kamanakulkarni.mvvmdemo.model;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.kamanakulkarni.mvvmdemo.model.network_call.Response;
import com.example.kamanakulkarni.mvvmdemo.model.network_call.ProductData;
import com.example.kamanakulkarni.mvvmdemo.model.network_call.WebServiceClient;
import java.util.List;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DataRepository implements BaseViewModel{

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private static DataRepository sInstance;

    private MutableLiveData<List<ProductData>> productLiveData;

    private final WebServiceClient client;

    private DataRepository(WebServiceClient webServiceClient){
        this.client = webServiceClient;
        productLiveData = new MutableLiveData<>();

    }

    public static DataRepository getsInstance(final WebServiceClient webServiceClient){
        if (sInstance==null) {
            synchronized (DataRepository.class) {
                if (sInstance == null) {
                    sInstance = new DataRepository(webServiceClient);
                }
            }
        }
        return sInstance;
    }

    public MutableLiveData<List<ProductData>> fetchProductResponse(){

        client.getProductData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(Response response) {
                        Log.e("data", response.getCode().toString());
                        productLiveData.postValue(response.getResult().getHomeDecorProduct());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("data", e.toString());

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return productLiveData;
    }


    @Override
    public void onClear() {
        compositeDisposable.clear();
    }
}

