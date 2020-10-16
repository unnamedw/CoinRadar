package kr.co.douchgosum.android.coinradar.utils

import kr.co.douchgosum.android.coinradar.data.Exchange
import java.text.SimpleDateFormat
import java.util.*

fun dateToMillis(utc: String, exchange: Exchange): Long {
    if (exchange == Exchange.GOPAX) {
        val utcStr = utc.toLowerCase()
            .replace("t", " ")
            .replace("z", "")
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.KOREA)
            .apply {
                timeZone = TimeZone.getTimeZone("UTC")
            }
        val date: Date = format.parse(utcStr)
        val millis = date.time
        return millis
    }
    return -1
}

