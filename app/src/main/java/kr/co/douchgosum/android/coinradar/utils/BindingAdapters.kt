package kr.co.douchgosum.android.coinradar.utils

import android.content.ContentProvider
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import kr.co.douchgosum.android.coinradar.R
import kr.co.douchgosum.android.coinradar.data.db.Ticker
import org.koin.java.KoinJavaComponent.get

@BindingAdapter("srcUrl")
fun bindImage(view: ImageView, url: String? = "") {
    Glide.with(view.context)
        .load(url)
        .into(view)
}

@BindingAdapter("price")
fun bindTickerPrice(view: TextView, price: Double) {
    view.text = Ticker.toFormattedString(price)
}

@BindingAdapter("flucRate")
fun bindTickerFlucRate(view: TextView, flucRate: Double) {
    val context = view.context
    val color: Int
    val rate: String
    when {
        flucRate>0 -> {
            color = ContextCompat.getColor(context, R.color.colorRed)
            rate = "${context.resources.getString(R.string.arrow_up)}${String.format("%.2f", flucRate)}%"
        }
        flucRate<0 -> {
            color = ContextCompat.getColor(context, R.color.colorBlue)
            rate = "${context.resources.getString(R.string.arrow_down)}${String.format("%.2f", flucRate)}%"
        }
        else -> {
            color = ContextCompat.getColor(context, R.color.colorBlack)
            rate = "N/A"
        }
    }
    with(view) {
        setTextColor(color)
        text = rate
    }
//    android:textColor='@{ticker.fluctuateRate24H>0 ? @color/colorRed : (ticker.fluctuateRate24H&lt;0 ? @color/colorBlue : @color/colorBlack)}'
//    android:text='@{(ticker.fluctuateRate24H!=0 ? (ticker.fluctuateRate24H>0 ? @string/arrow_up+"+" : @string/arrow_down+"") + String.format("%.2f", ticker.fluctuateRate24H) + "%" : "(N/A)") + ""}'
}

@BindingAdapter("flucPrice")
fun bindTickerFlucPrice(view: TextView, flucPrice: Double) {
    val context = view.context
    val color: Int
    val price: String
    when {
        flucPrice>0 -> {
            color = ContextCompat.getColor(context, R.color.colorRed)
            price = "+${flucPrice}"
        }
        flucPrice<0 -> {
            color = ContextCompat.getColor(context, R.color.colorBlue)
            price = "$flucPrice"
        }
        else -> {
            color = ContextCompat.getColor(context, R.color.colorBlack)
            price = "-"
        }
    }
    with(view) {
        setTextColor(color)
        text = price
    }
//    android:textColor='@{ticker.fluctuatePrice24H>0 ? @color/colorRed : (ticker.fluctuatePrice24H&lt;0 ? @color/colorBlue : @color/colorBlack)}'
//    android:text='@{ticker.fluctuatePrice24H!=0 ? (ticker.fluctuatePrice24H>0 ? "+" : "") + ticker.toFormattedString(ticker.fluctuatePrice24H) : " "}'

}

