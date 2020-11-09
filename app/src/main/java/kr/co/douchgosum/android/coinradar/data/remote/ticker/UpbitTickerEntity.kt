package kr.co.douchgosum.android.coinradar.data.remote.ticker

import com.squareup.moshi.Json

data class UpbitTicker(
    val market: String,
    @Json(name = "trade_date") val tradeDate: String,
    @Json(name = "trade_time") val tradeTime: String,
    @Json(name = "trade_date_kst") val tradeDateKst: String,
    @Json(name = "trade_time_kst") val tradeTimeKst: String,
    @Json(name = "trade_timestamp") val tradeTimestamp: String,
    @Json(name = "opening_price") val openingPrice: Double,
    @Json(name = "high_price") val highPrice: Double,
    @Json(name = "low_price") val lowPrice: Double,
    @Json(name = "trade_price") val tradePrice: Double,
    @Json(name = "prev_closing_price") val prevClosingPrice: Double,
    val change: String,
    @Json(name = "change_price") val changePrice: Double,
    @Json(name = "change_rate") val changeRate: Double,
    @Json(name = "signed_change_price") val signedChangePrice: Double,
    @Json(name = "signed_change_rate") val signedChangeRate: Double,
    @Json(name = "trade_volume") val tradeVolume: Double,
    @Json(name = "acc_trade_price") val accTradePrice: Double,
    @Json(name = "acc_trade_price_24h") val accTradePrice24h: Double,
    @Json(name = "acc_trade_volume") val accTradeVolume: Double,
    @Json(name = "acc_trade_volume_24h") val accTradeVolume24h: Double,
    @Json(name = "highest_52_week_price") val highest52WeekPrice: Double,
    @Json(name = "highest_52_week_date") val highest52WeekDate: String,
    @Json(name = "lowest_52_week_price") val lowest52WeekPrice: Double,
    @Json(name = "lowest_52_week_date") val lowest52Weekdate: String,
    val timestamp: Long
)

data class UpbitMarket(
    val market: String,
    @Json(name = "korean_name") val koreanName: String,
    @Json(name = "english_name") val englishName: String
)
