package com.example.coinscreencap.ui.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("coinIcon")
fun loadIconToImageView(imageView: ImageView,url:String){
    Glide.with(imageView.context)
        .load(url)
        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
        .into(imageView)
}