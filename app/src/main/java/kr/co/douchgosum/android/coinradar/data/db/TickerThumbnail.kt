package kr.co.douchgosum.android.coinradar.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "ticker_thumbnails")
data class TickerThumbnail(
    @PrimaryKey
    @ColumnInfo(collate = ColumnInfo.NOCASE) val symbol: String,
    val image: String?
)