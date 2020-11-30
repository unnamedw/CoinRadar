package kr.co.douchgosum.android.coinradar.data.remote.coingecko

import com.squareup.moshi.Json
import kr.co.douchgosum.android.coinradar.data.db.Ticker
import java.text.SimpleDateFormat
import java.util.*

data class CoinGeckoTicker(
    val id: String?,
    val symbol: String?,
    val name: String?,
    val image: String?,
    @Json(name = "current_price") val currentPrice: Double?,
    @Json(name = "market_cap") val marketCap: Double?,
    @Json(name = "market_cap_rank") val marketCapRank: Double?,
    @Json(name = "fully_diluted_valuation") val fullyDilutedValuation: Double?,
    @Json(name = "total_volume") val totalVolume: Double?,
    @Json(name = "high_24h") val high24H: Double?,
    @Json(name = "low_24h") val low24H: Double?,
    @Json(name = "price_change_24h") val priceChange24H: Double?,
    @Json(name = "price_change_percentage_24h") val priceChangePercentage24H: Double?,
    @Json(name = "market_cap_change_24h") val marketCapChange24H: Double?,
    @Json(name = "market_cap_change_percentage_24h") val marketCapChangePercentage24H: Double?,
    @Json(name = "circulating_supply") val circulatingSupply: Double?,
    @Json(name = "total_supply") val totalSupply: Double?,
    @Json(name = "max_supply") val maxSupply: Double?,
    @Json(name = "ath") val ath: Double?,
    @Json(name = "ath_change_percentage") val athChangePercentage: Double?,
    @Json(name = "ath_date") val athDate: String?,
    val atl: Double?,
    @Json(name = "atl_change_percentage") val atlChangePercentage: Double?,
    @Json(name = "atl_date") val atlDate: String?,
    val roi: Map<Any, Any>?,
    @Json(name = "last_updated") val lastUpdated: String?
) {

    fun toTicker(currency: String?): Ticker = Ticker.Builder().apply {
        baseSymbol = symbol ?: ""
        quoteSymbol = currency ?: ""
        currentPrice = this@CoinGeckoTicker.currentPrice ?: 0.0
        timeStamp = utcTimeMillis(lastUpdated ?: "")
        fluctuatePrice24H = priceChange24H ?: 0.0
        fluctuateRate24H = priceChangePercentage24H ?: 0.0
        exchange = "coingecko"
    }.build()

    private fun utcTimeMillis(utc: String): Long {
        val utcStr = utc.toLowerCase(Locale.ROOT)
            .replace("t", " ")
            .replace("z", "")
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.KOREA)
            .apply {
                timeZone = TimeZone.getTimeZone("UTC")
            }
        val date = format.parse(utcStr)

        return date?.time ?: -1
    }
}