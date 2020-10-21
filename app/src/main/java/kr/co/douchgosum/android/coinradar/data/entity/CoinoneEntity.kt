package kr.co.douchgosum.android.coinradar.data.entity

import com.squareup.moshi.Json

data class CoinoneTicker(
    val currency: String,
    val high: Double,
    val low: Double,
    val first: Double,
    val last: Double,
    val volume: Double,
    @Json(name = "yesterday_high") val yesterdayHigh: Double,
    @Json(name = "yesterday_low") val yesterdayLow: Double,
    @Json(name = "yesterday_first") val yesterdayFirst: Double,
    @Json(name = "yesterday_last") val yesterdayLast: Double,
    @Json(name = "yesterday_volume") val yesterdayVolume: Double
)