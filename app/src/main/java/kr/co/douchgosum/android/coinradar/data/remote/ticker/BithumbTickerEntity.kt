package kr.co.douchgosum.android.coinradar.data.remote.ticker

import com.squareup.moshi.Json

data class BithumbTickerResponse(
    val status: String,
    val data: Map<String, Any>
)

data class BithumbTicker(
    @Json(name ="opening_price") val openingPrice: String,
    @Json(name = "closing_price") val closingPrice: String,
    @Json(name = "min_price") val minPrice: String,
    @Json(name = "max_price") val maxPrice: String,
    @Json(name = "units_traded") val unitsTraded: String,
    @Json(name = "acc_trade_value") val accTradeValue: String,
    @Json(name = "prev_closing_price") val prevClothingPrice: String,
    @Json(name = "units_traded_24H") val unitsTraded24H: String,
    @Json(name = "acc_trade_value_24H") val accTradeValue24H: String,
    @Json(name = "fluctate_24H") val fluctate24H: String,
    @Json(name = "fluctate_rate_24H") val fluctateRate24H: String
)