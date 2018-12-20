package com.example.kamanakulkarni.mvvmdemo.view;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kamanakulkarni.mvvmdemo.R;
import com.example.kamanakulkarni.mvvmdemo.adapter.ProductAdapter;
import com.example.kamanakulkarni.mvvmdemo.callback.IProductClickListener;
import com.example.kamanakulkarni.mvvmdemo.databinding.FragmentProductListBinding;
import com.example.kamanakulkarni.mvvmdemo.model.network_call.ProductData;
import com.example.kamanakulkarni.mvvmdemo.viewmodel.ProductViewModel;

import java.util.List;

public class FragmentProductList extends Fragment implements IProductClickListener {

    private FragmentProductListBinding binding;

    private ProductAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_list, container,false);
        adapter = new ProductAdapter();
        adapter.setProductListener(this);
        binding.rvProduct.setAdapter(adapter);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final ProductViewModel viewModel= ViewModelProviders.of(this).get(ProductViewModel.class);

        binding.setViewModel(viewModel);
        binding.setIsLoading(viewModel.isLoading);
        binding.setShowProgress(viewModel.showProgress);
        binding.setLifecycleOwner(this);

        viewModel.fetchProductData();

        viewModel.productObservableData.observe(this, new Observer<List<ProductData>>() {
            @Override
            public void onChanged(@Nullable List<ProductData> productData) {
                viewModel.isLoading.set(false);

                if (viewModel.getProductList().getValue()!=null && !viewModel.getProductList().getValue().isEmpty()){
                    adapter.setProductList(viewModel.getProductList().getValue());
                }

                //binding.executePendingBindings();
            }
        });

    }

    @Override
    public void onProductClick(ProductData product) {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)){
            ((IndexActivity)getActivity()).show(product);
        }
    }
}
