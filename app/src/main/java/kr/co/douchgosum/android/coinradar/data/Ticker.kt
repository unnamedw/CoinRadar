package kr.co.douchgosum.android.coinradar.data

data class Ticker (
    val baseCurrency: String,
    val quoteCurrency: String,
    val openPrice: Double,
    val closePrice: Double,
    val timeStamp: Long
) {
    val flucPrice24H: Double = this.closePrice - this.openPrice
    val flucRate24H: Double = (flucPrice24H/openPrice)*100
}