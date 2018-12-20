package com.example.kamanakulkarni.mvvmdemo.model.network_response;

import com.example.kamanakulkarni.mvvmdemo.model.model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NetworkResponse {

    private int code;
    private String message;
    private JSONObject result;
    private List<Product> productList = new ArrayList<>();


    public NetworkResponse(String response ){
        try {
            JSONObject jsonObject = new JSONObject(response);
            code = jsonObject.getInt("code");
           // message = jsonObject.getString("message");
            if (code==200) {
                result = jsonObject.getJSONObject("result");
                if (result!=null){
                    JSONArray jsonArray = result.getJSONArray("HomeDecorProduct");
                    if (jsonArray!=null && jsonArray.length()>0){
                        for (int i=0; i<jsonArray.length(); i++){
                            Product product = new Product(jsonArray.get(i).toString());
                            productList.add(product);
                        }
                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getProductList(){
        return productList;
    }
}
