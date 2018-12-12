package com.example.kamanakulkarni.mvvmdemo.util;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class BindingUtils {

    @BindingAdapter({"imageUrl","placeHolder" })
    public static void loadImage(ImageView imageView, String imageUrl, Drawable error){
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.AUTOMATIC).placeholder(error))
                .into(imageView);
    }

}
