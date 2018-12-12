package com.example.kamanakulkarni.mvvmdemo.network_response;

import android.content.Context;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebServiceClient{

    private Retrofit retrofit;

    private ApiInterface apiInterface;

    private static WebServiceClient instance;

    private WebServiceClient(final Context context){

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://31b64036-d693-4a1e-869a-bc97ed9aa24b.mock.pstmn.io/")
                //https://31b64036-d693-4a1e-869a-bc97ed9aa24b.mock.pstmn.io/
                .build();

    }

    public static WebServiceClient getInstance(Context context){

        if (instance==null){
            instance = new WebServiceClient(context);
        }

        return instance;
    }

    public void getProductData(final INetworkListener listener, final int requestCode){
        apiInterface = retrofit.create(ApiInterface.class);
        apiInterface.getProducts().enqueue(new ApiCallBack(requestCode, listener));
    }

    public interface INetworkListener{
        void onSuccess(String response, int requestCode);
        void onFailure(String message, int requestCode);
    }

    private class ApiCallBack implements Callback<ResponseBody>{

        private int requestCode;
        private INetworkListener iNetworkListener;

        ApiCallBack(int requestCode, INetworkListener iNetworkListener){
            this.requestCode = requestCode;
            this.iNetworkListener = iNetworkListener;
        }

        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            validateResponse(call.request(), response, this.iNetworkListener, this.requestCode);
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            validateError(call.request(), t);
        }
    }

    private void validateError(Request call, Throwable t) {


    }

    private void validateResponse(Request request, Response<ResponseBody> response, INetworkListener iNetworkListener, int requestCode) {

        try {
            ResponseBody data = response.body();
            ResponseBody error = response.errorBody();
            if (response.code()==200){
                if (data != null){

                    String responseString = data.string();
                    //Logger.logMessage("ProductData", responseString);
                  /*  JSONObject jsonObject = new JSONObject(responseString);
                    Object resultObject = jsonObject.opt("result");
*/
                    iNetworkListener.onSuccess(responseString, requestCode);

                }

            }else {
                iNetworkListener.onFailure(error.string(), requestCode);
            }

        }catch (IOException e){

        } /*catch (JSONException e) {
            e.printStackTrace();
        }*/
    }
}
