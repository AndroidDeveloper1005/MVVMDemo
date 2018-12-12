package com.example.kamanakulkarni.mvvmdemo.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Menu;

import com.example.kamanakulkarni.mvvmdemo.BaseActivity;
import com.example.kamanakulkarni.mvvmdemo.viewmodel.IndexViewModel;
import com.example.kamanakulkarni.mvvmdemo.util.Logger;
import com.example.kamanakulkarni.mvvmdemo.network_response.NetworkResponse;
import com.example.kamanakulkarni.mvvmdemo.adapter.ProductAdapter;
import com.example.kamanakulkarni.mvvmdemo.R;
import com.example.kamanakulkarni.mvvmdemo.databinding.ActivityIndexBinding;

public class IndexActivity extends BaseActivity {

    ActivityIndexBinding binding;
    IndexViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeFullScreen();
        viewModel = ViewModelProviders.of(this).get(IndexViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_index);
        binding.setViewModel(viewModel);
        binding.setIsLoading(viewModel.isLoading);
        binding.setShowProgress(viewModel.showProgress);
        binding.setLifecycleOwner(this);

        final ProductAdapter adapter = new ProductAdapter();
        binding.rvProduct.setAdapter(adapter);
        viewModel.fetchProductData();

        viewModel.liveData.observe(this, new Observer<NetworkResponse>() {
            @Override
            public void onChanged(@Nullable NetworkResponse networkResponse) {
                viewModel.productData.setValue(networkResponse.getProductList());
                if (viewModel.getProductList().getValue()!=null && !viewModel.getProductList().getValue().isEmpty())
                    adapter.setProductList(viewModel.getProductList().getValue());
                Logger.logMessage("productData", String.valueOf(viewModel.getProductList().getValue().size()));
            }
        });
    }

}
