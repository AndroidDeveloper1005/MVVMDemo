package com.example.kamanakulkarni.mvvmdemo.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.kamanakulkarni.mvvmdemo.BaseActivity;
import com.example.kamanakulkarni.mvvmdemo.model.model.Product;
import com.example.kamanakulkarni.mvvmdemo.R;
import com.example.kamanakulkarni.mvvmdemo.databinding.ActivityIndexBinding;
import com.example.kamanakulkarni.mvvmdemo.model.network_call.ProductData;

public class IndexActivity extends BaseActivity{

    ActivityIndexBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeFullScreen();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_index);

        if (savedInstanceState==null){
            FragmentProductList productListFragment = new FragmentProductList();
            getSupportFragmentManager().beginTransaction().add(R.id.container, productListFragment,"ProductList").commit();
        }
    }

    /*show detail view of product*/
    public void show(ProductData product){
        FragmentProductDetail productDetail = new FragmentProductDetail();

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("product")
                .replace(R.id.container, productDetail, null)
                .commit();
    }

}
