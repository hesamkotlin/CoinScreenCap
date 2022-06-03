package com.example.coinscreencap.ui.util

import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.coinscreencap.R
import java.text.DecimalFormat
import kotlin.math.roundToInt


@BindingAdapter("coinIcon")
fun loadIconToImageView(imageView: ImageView, url: String?) {
    url ?: return
    Glide.with(imageView.context)
        .load(url)
        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
        .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
        .listener(object :RequestListener<Drawable>{
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                Log.d("GlideLoadStatus", e.toString())
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                Log.d("GlideLoadStatus", "onResourceReady")
                return false
            }

        })
        .into(imageView)
}

@BindingAdapter("coinDescription")
fun loadSymbolText(textView: TextView, symbol: String?) {
    symbol ?: return
    val text = textView.context.getString(R.string.description, symbol)
    textView.text = text
}

@BindingAdapter("coinPercentageChangeColor")
fun loadChangeTextColor(textView: TextView, change: Float?) {
    change ?: return
    if (change < 0) {
        textView.setTextColor(textView.context.resources.getColor(R.color.negativeChange))
    } else
        textView.setTextColor(textView.context.resources.getColor(R.color.positiveChange))

}

@BindingAdapter("coinPercentageSign")
fun loadPercentageSign(textView: TextView, change: Float?) {
    change ?: return
    val text = textView.context.getString(R.string.percentage_sign, change.toString())
    textView.text = text
}

@BindingAdapter("coinDollarSign")
fun loadPriceSign(textView: TextView, price: String?) {
    price ?: return
    val text = textView.context.getString(R.string.dollar_sign, price)
    textView.text = text
}

@BindingAdapter("coinBtcSign")
fun loadBtcSign(textView: TextView, btcPrice: String?) {
    btcPrice ?: return
    val text = textView.context.getString(R.string.btc_price_text, btcPrice)
    textView.text = text
}



