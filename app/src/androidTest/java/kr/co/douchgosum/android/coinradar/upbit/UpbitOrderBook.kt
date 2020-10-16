package kr.co.douchgosum.android.coinradar.upbit

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

data class OrderBook(
    val market: String,
    val timestamp: String,
    @Json(name = "total_ask_size") val totalAskSize: String,
    @Json(name = "total_bid_size") val totalBidSize: String,
    @Json(name = "orderbook_units") val orderbookUnits: List<Order>
)

data class Order(
    @Json(name = "ask_price") val askPrice: String,
    @Json(name = "bid_price") val bidPrice: String,
    @Json(name = "ask_size") val askSize: String,
    @Json(name = "bid_size") val bidSize: String
)

