package kr.co.douchgosum.android.coinradar.gopax



data class GopaxAsset(
    val id: String,
    val name: String
)


data class GopaxPair(
    val id: String,
    val name: String,
    val baseAsset: String,
    val quoteAsset: String
)