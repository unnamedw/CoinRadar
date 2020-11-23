package kr.co.douchgosum.android.coinradar.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(tableName = "ticker_thumbnails")
data class TickerThumbnail(
    @PrimaryKey
    val symbol: String,
    val imgUrl: String
)