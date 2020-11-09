package kr.co.douchgosum.android.coinradar.data.remote.exchange

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "coingecko_exchanges")
data class CoinGeckoExchange(
    @PrimaryKey val id: String,
    val name: String,
    @Json(name = "year_established") val yearEstablished: Int,
    val country: String,
    val description: String,
    val url: String,
    val image: String,
    @Json(name = "has_trading_incentive") val hasTradingIncentive: Boolean,
    @Json(name = "trust_score") val trustScore: Int,
    @Json(name = "trust_score_rank") val trustScoreRank: Int,
    @Json(name = "trade_volume_24h_btc") val tradeVolume24hBtc: Double,
    @Json(name = "trade_volume_24h_btc_normalized") val tradeVolume24hBtcNormalized: Double
)