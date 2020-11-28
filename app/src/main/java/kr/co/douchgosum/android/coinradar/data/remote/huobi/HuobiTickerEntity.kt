package kr.co.douchgosum.android.coinradar.data.remote.huobi

import kr.co.douchgosum.android.coinradar.data.db.Ticker

data class HuobiTickerResponse(
    val status: String,
    val ts: Long,
    val data: List<HuobiTicker>
)

data class HuobiTicker(
    val amount: Double,
    val count: Long,
    val open: Double,
    val close: Double,
    val low: Double,
    val high: Double,
    val vol: Double,
    val symbol: String
)  {

    fun toTicker(timestamp: Long): Ticker {
        val dividedSymbol = divideHuobiSymbol(symbol)
        val open = open
        val close = close
        val flucPrice = close-open
        val flucRate = if (flucPrice!=0.0) flucPrice/open else 0.0
        return Ticker.Builder().apply {
            baseSymbol = dividedSymbol[0]
            quoteSymbol = dividedSymbol[1]
            currentPrice = close
            timeStamp = timestamp
            fluctuatePrice24H = flucPrice
            fluctuateRate24H = flucRate
            exchange = "huobi"
        }.build()
    }

    private fun divideHuobiSymbol(symbol: String): List<String> {
        val suffixes = listOf("btc", "krw", "eth", "ht", "usdt")
        suffixes.forEach { suffix ->
            if (symbol.endsWith(suffix, ignoreCase = true)) {
                return listOf(symbol.replace(suffix, "", ignoreCase = true), suffix)
            }
        }
        return emptyList()
    }

}