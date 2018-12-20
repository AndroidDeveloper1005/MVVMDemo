package com.example.kamanakulkarni.mvvmdemo.model.network_call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("result")
    @Expose
    private ProductResponse result;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public ProductResponse getResult() {
        return result;
    }

    public void setResult(ProductResponse result) {
        this.result = result;
    }

}

