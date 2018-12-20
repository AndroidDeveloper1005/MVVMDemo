package com.example.kamanakulkarni.mvvmdemo.model.model;

import android.databinding.BaseObservable;

import org.json.JSONException;
import org.json.JSONObject;

public class Product{

    private String name;
    private String color;
    private String image;
    private String originalPrice;
    private String sellingPrice;
    private String discount;
    private int rating;
    private String quantityInStock;

    public Product(String product){
        try {
            JSONObject childJson = new JSONObject(product);

            if (childJson!=null){
                name = childJson.optString("name", "");
                image = childJson.optString("image", "");
                color = childJson.optString("color", "");
                originalPrice = childJson.optString("originalPrice", "");
                sellingPrice = childJson.optString("sellingPrice", "");
                discount = childJson.optString("discount", "");
                rating = childJson.optInt("rating",0);
                quantityInStock = childJson.optString("quantityInStock", "");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public String getSellingPrice() {
        return sellingPrice;
    }

    public String getDiscount() {
        return discount;
    }

    public String getImage(){
        return image;
    }

    public String getQuantityInStock() {
        return quantityInStock;
    }

    public int getRating() {
        return rating;
    }


}
