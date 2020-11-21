package kr.co.douchgosum.android.coinradar.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.DecimalFormat
import java.util.*

@Entity(tableName = "tickers")
data class Ticker (
    @ColumnInfo(name = "base_symbol") val baseSymbol: String,
    @ColumnInfo(name = "quote_symbol") val quoteSymbol: String,
    @ColumnInfo(name = "current_price") val currentPrice: Double,
    @ColumnInfo(name = "timestamp") val timeStamp: Long,
    @ColumnInfo(name = "fluctuate_price_24h") val fluctuatePrice24H: Double,
    @ColumnInfo(name = "fluctuate_rate_24h") val fluctuateRate24H: Double,
    @ColumnInfo(name = "image") val image: String = "",
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "exchange") val exchange: String =""
) {
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String = "${baseSymbol}_${quoteSymbol}"
        .toLowerCase(Locale.ROOT)

    /**
    * Double 형 데이터를 유효숫자 까지만 표시하여 문자열로 반환해준다.
     * ex) 1234.5678000 -> 1,234.5678
     *
     * 최대 소숫점 8자리 까지 표기
     * */
    fun toFormattedString(double: Double): String {
        val formatter = DecimalFormat("#,###.########")
        return formatter.format(double)
    }

    /**
     * Ticker Builder
     * */
    inner class Builder {
        var baseSymbol: String = ""
        var quoteSymbol: String = ""
        var currentPrice: Double = 0.0
        var timeStamp: Long = 0
        var fluctuatePrice24H: Double = 0.0
        var fluctuateRate24H: Double = 0.0
        var image: String = ""
        var name: String = ""
        var exchange: String = ""

        fun build(): Ticker =
            Ticker(
                this.baseSymbol,
                this.quoteSymbol,
                this.currentPrice,
                this.timeStamp,
                this.fluctuatePrice24H,
                this.fluctuateRate24H,
                this.image,
                this.name,
                this.exchange
            )
    }
}



