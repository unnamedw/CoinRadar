package kr.co.douchgosum.android.coinradar.data.entity

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
) 