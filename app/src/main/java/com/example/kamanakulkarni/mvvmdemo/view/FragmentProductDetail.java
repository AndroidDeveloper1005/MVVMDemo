package com.example.kamanakulkarni.mvvmdemo.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kamanakulkarni.mvvmdemo.R;
import com.example.kamanakulkarni.mvvmdemo.databinding.FragmentProductDetailBinding;

public class FragmentProductDetail extends Fragment {

    FragmentProductDetailBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_detail,container,false );
        return binding.getRoot();
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

}
