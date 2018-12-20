package com.example.kamanakulkarni.mvvmdemo.model.network_call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductResponse {


    @SerializedName("HomeDecorProduct")
    @Expose
    private List<ProductData> productList = null;

    public List<ProductData> getHomeDecorProduct() {
        return productList;
    }

    public void setHomeDecorProduct(List<ProductData> homeDecorProduct) {
        this.productList = homeDecorProduct;
    }

}
