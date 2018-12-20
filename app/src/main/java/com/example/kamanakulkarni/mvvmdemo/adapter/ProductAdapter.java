package com.example.kamanakulkarni.mvvmdemo.adapter;

import android.databinding.DataBindingUtil;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.kamanakulkarni.mvvmdemo.callback.IProductClickListener;
import com.example.kamanakulkarni.mvvmdemo.model.model.Product;
import com.example.kamanakulkarni.mvvmdemo.R;
import com.example.kamanakulkarni.mvvmdemo.databinding.RowProductBinding;
import com.example.kamanakulkarni.mvvmdemo.model.network_call.ProductData;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<? extends ProductData> mProductList;

    public ProductAdapter(){
        mProductList = new ArrayList<>();
    }

    private IProductClickListener listener;

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
            binding.setCallBack(listener);
            binding.setProductData(mProductList.get(position));
            binding.tvActualMRP.setPaintFlags(binding.tvActualMRP.getPaintFlags()|Paint.STRIKE_THRU_TEXT_FLAG);
        }

    }

    public void setProductList(final List<ProductData> productList){

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
                    ProductData newProduct = productList.get(newItemPosition);
                    ProductData oldProduct = mProductList.get(oldItemPosition);
                    return (newProduct.getName() == oldProduct.getName()&& newProduct.getColor()==oldProduct.getColor());
                    //return false;
                }
            });

            mProductList = productList;
            result.dispatchUpdatesTo(this);
        }
    }

    public void setProductListener(IProductClickListener listener){
        this.listener = listener;
    }
}
