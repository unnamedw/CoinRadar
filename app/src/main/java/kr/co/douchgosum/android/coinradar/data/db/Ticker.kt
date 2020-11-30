package kr.co.douchgosum.android.coinradar.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.text.CollationKey
import java.text.DecimalFormat
import java.util.*
import kotlin.math.abs
import kotlin.math.roundToInt

@Entity(tableName = "tickers",
    primaryKeys = ["base_symbol", "quote_symbol", "exchange"],
    indices = [Index("base_symbol", unique = true)])
data class Ticker (
    @ColumnInfo(name = "base_symbol") val baseSymbol: String,
    @ColumnInfo(name = "quote_symbol") val quoteSymbol: String,
    @ColumnInfo(name = "current_price") val currentPrice: Double,
    @ColumnInfo(name = "timestamp") val timeStamp: Long,
    @ColumnInfo(name = "fluctuate_price_24h") val fluctuatePrice24H: Double,
    @ColumnInfo(name = "fluctuate_rate_24h") val fluctuateRate24H: Double,
    @ColumnInfo(name = "exchange") val exchange: String
) {
//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = "id")
//    var id: Int = 0

    /**
     * 숫자의 길이가 지나치게 길어지지 않게 조정한다.
     * 1000 이상인 경우 정수만,
     * 1 이상인 경우 소숫점 둘째 자리까지만 표시(반올림)
     * */

    /**
     * Ticker Builder
     * */
    class Builder {
        var baseSymbol: String = ""
        var quoteSymbol: String = ""
        var currentPrice: Double = 0.0
        var timeStamp: Long = 0
        var fluctuatePrice24H: Double = 0.0
        var fluctuateRate24H: Double = 0.0
        var exchange: String = ""

        fun build(): Ticker =
            Ticker(
                this.baseSymbol,
                this.quoteSymbol,
                this.currentPrice,
                this.timeStamp,
                this.fluctuatePrice24H,
                this.fluctuateRate24H,
                this.exchange
            )
    }

    companion object {
        /**
         * Double 형 데이터를 유효숫자 까지만 표시하여 문자열로 반환해준다.
         * ex) 1234.5678000 -> 1,234.5678
         *
         * 최대 소숫점 8자리 까지 표기
         * */
        fun toFormattedString(double: Double): String {
            val formatter = DecimalFormat("#,###.########")
            return formatter.format(double.trim())
        }

        private fun Double.trim(): Double {
            val absolute = abs(this)
            return when {
                absolute >= 1000 -> {
                    this.toLong()*1.0
                }
                absolute >=1 -> {
                    (this*100).roundToInt()/100.0
                }
                else -> this
            }
        }
    }
}



