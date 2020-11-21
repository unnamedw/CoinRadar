package kr.co.douchgosum.android.coinradar.data.remote.bithumb

import com.squareup.moshi.Json
import kr.co.douchgosum.android.coinradar.data.db.Ticker

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
) {

    fun toTicker(base: String, time: Long): Ticker {
        return Ticker(
            baseSymbol = base,
            quoteSymbol = "krw",
            currentPrice = closingPrice.toDouble(),
            timeStamp = time,
            fluctuatePrice24H = fluctate24H.toDouble(),
            fluctuateRate24H = fluctateRate24H.toDouble()
        )
    }

}