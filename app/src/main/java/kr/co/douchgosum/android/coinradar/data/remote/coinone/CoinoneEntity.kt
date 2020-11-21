package kr.co.douchgosum.android.coinradar.data.remote.coinone

import com.squareup.moshi.Json
import kr.co.douchgosum.android.coinradar.data.db.Ticker

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
) {

    fun toTicker(base: String, timestamp: Long): Ticker {
        val preLast = yesterdayLast
        val last = last
        val flucPrice = preLast-last
        val flucRate = if (preLast!=0.0) flucPrice/preLast else 0.0
        return Ticker(
            baseSymbol = base,
            quoteSymbol = "krw",
            currentPrice = last,
            timeStamp = timestamp * 1000, // 코인원은 타임스탬프가 초(s) 단위로 표시되므로 1000을 곱하여 밀리 초(ms) 단위로 변경
            fluctuatePrice24H = flucPrice,
            fluctuateRate24H = flucRate
        )
    }
}