package kr.co.douchgosum.android.coinradar.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("srcUrl")
fun bindImage(view: ImageView, url: String) {
    Glide.with(view.context)
        .load(url)
        .into(view)
}