package com.example.kamanakulkarni.mvvmdemo.adapter;

import android.databinding.DataBindingUtil;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.kamanakulkarni.mvvmdemo.model.Product;
import com.example.kamanakulkarni.mvvmdemo.R;
import com.example.kamanakulkarni.mvvmdemo.databinding.RowProductBinding;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product> productList;

    public ProductAdapter(){
        productList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowProductBinding productBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.row_product,parent,false);
        return new ViewHolder(productBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {

        holder.setItem(position);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RowProductBinding binding;

        public ViewHolder(RowProductBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public void setItem(int position){
            binding.setProductData(productList.get(position));
            binding.executePendingBindings();
            binding.tvActualMRP.setPaintFlags(binding.tvActualMRP.getPaintFlags()|Paint.STRIKE_THRU_TEXT_FLAG);
        }

    }

    public void setProductList(List<Product> productList){

        if (productList!=null && !productList.isEmpty()){
            this.productList.clear();
            this.productList.addAll(productList);
            notifyDataSetChanged();
        }
    }
}
