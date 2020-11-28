package kr.co.douchgosum.android.coinradar.data.remote.gopax

import kr.co.douchgosum.android.coinradar.data.db.Ticker
import java.text.SimpleDateFormat
import java.util.*


data class GopaxTicker(
    val name: String,
    val open: Double,
    val high: Double,
    val low: Double,
    val close: Double,
    val volume: Double,
    val time: String
) {

    fun toTicker(): Ticker {
        val currency = name.split('-')
        val timeStamp = utcTimeMillis(time)
        val open = open
        val close = close
        val flucPrice = close-open
        val flucRate = if (flucPrice!=0.0) flucPrice/open else 0.0
        return Ticker.Builder().apply {
            baseSymbol = currency[0]
            quoteSymbol = currency[1]
            currentPrice = close
            this.timeStamp = timeStamp
            fluctuatePrice24H = close-open
            fluctuateRate24H = flucRate
            exchange = "gopax"
        }.build()
    }

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