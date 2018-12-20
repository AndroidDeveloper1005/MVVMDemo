package com.example.kamanakulkarni.mvvmdemo.callback;

import com.example.kamanakulkarni.mvvmdemo.model.model.Product;
import com.example.kamanakulkarni.mvvmdemo.model.network_call.ProductData;

public interface IProductClickListener {

    public void onProductClick(ProductData product);
}
