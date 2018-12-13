package com.example.kamanakulkarni.mvvmdemo.adapter;

import android.databinding.DataBindingUtil;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.kamanakulkarni.mvvmdemo.model.Product;
import com.example.kamanakulkarni.mvvmdemo.R;
import com.example.kamanakulkarni.mvvmdemo.databinding.RowProductBinding;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<? extends Product> mProductList;

    public ProductAdapter(){
        mProductList = new ArrayList<>();
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
        holder.binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return mProductList==null ? 0 : mProductList.size();//mProductList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RowProductBinding binding;

        public ViewHolder(RowProductBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public void setItem(int position){
            binding.setProductData(mProductList.get(position));
            binding.tvActualMRP.setPaintFlags(binding.tvActualMRP.getPaintFlags()|Paint.STRIKE_THRU_TEXT_FLAG);
        }

    }

    public void setProductList(final List<Product> productList){
/*
        if (mProductList!=null && !mProductList.isEmpty()){
          //  this.mProductList.clear();
          //  this.mProductList.addAll(mProductList);
          //  notifyItemRangeChanged(2, mProductList.size());
            //notifyDataSetChanged();


        }*/

        if (mProductList==null){
            mProductList = productList;
            notifyItemRangeChanged(0, productList.size());
        }else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mProductList.size();
                }

                @Override
                public int getNewListSize() {
                    return productList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mProductList.get(oldItemPosition).getName() == productList.get(newItemPosition).getName();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Product newProduct = productList.get(newItemPosition);
                    Product oldProduct = mProductList.get(oldItemPosition);
                    return (newProduct.getName() == oldProduct.getName()&& newProduct.getColor()==oldProduct.getColor());
                    //return false;
                }
            });

            mProductList = productList;
            result.dispatchUpdatesTo(this);
        }
    }
}
