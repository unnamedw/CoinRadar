package kr.co.douchgosum.android.coinradar.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kr.co.douchgosum.android.coinradar.data.Exchange

@Entity(tableName = "tickers")
data class Ticker (
    @PrimaryKey val baseCurrency: String,
    val quoteCurrency: String,
    val openPrice: Double,
    val closePrice: Double,
    val timeStamp: Long
) {
    var fluctuatePrice24H: Double = this.closePrice - this.openPrice
    var fluctuateRate24H: Double = (fluctuatePrice24H/openPrice)*100

//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = "id")
//    var id: Long = 0

}