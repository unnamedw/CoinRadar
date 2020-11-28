package kr.co.douchgosum.android.coinradar.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import kr.co.douchgosum.android.coinradar.R
import org.koin.java.KoinJavaComponent.get

@BindingAdapter("srcUrl")
fun bindImage(view: ImageView, url: String) {
    Glide.with(view.context)
        .load(url)
        .into(view)
}