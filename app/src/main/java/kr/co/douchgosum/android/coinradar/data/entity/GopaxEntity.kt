package kr.co.douchgosum.android.coinradar.data.entity

import kr.co.douchgosum.android.coinradar.data.Ticker
import kr.co.douchgosum.android.coinradar.data.Tickerizable


data class GopaxTicker(
    val name: String,
    val open: Double,
    val high: Double,
    val low: Double,
    val close: Double,
    val volume: Double,
    val time: String
)