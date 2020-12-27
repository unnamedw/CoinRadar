package kr.co.douchgosum.android.coinradar.data.db

import androidx.room.*
import retrofit2.http.GET

@Dao
interface TickerDao {

    @Query("SELECT tickers.*, ticker_symbols.* FROM tickers LEFT JOIN ticker_symbols ON tickers.base_symbol = ticker_symbols.symbol COLLATE NOCASE")
    fun getAllTickerWithSymbols(): List<TickerWithSymbol>

    @Query("SELECT tickers.*, ticker_thumbnails.* FROM tickers LEFT JOIN ticker_thumbnails ON tickers.base_symbol = ticker_thumbnails.symbol COLLATE NOCASE")
    fun getAllTickerWithThumbnails(): List<TickerWithThumbnail>

    @Query("SELECT tickers.*, " +
            "ticker_symbols.symbol AS symbol_symbol, " +
            "ticker_symbols.name AS symbol_name, " +
            "ticker_thumbnails.symbol AS thumbnail_symbol, " +
            "ticker_thumbnails.image AS thumbnail_image FROM tickers " +
            "LEFT JOIN ticker_symbols ON tickers.base_symbol = ticker_symbols.symbol COLLATE NOCASE " +
            "LEFT JOIN ticker_thumbnails ON tickers.base_symbol = ticker_thumbnails.symbol COLLATE NOCASE")
//    @Query("SELECT tickers.*, ticker_symbols.name AS symbol_name, ticker_thumbnails.image AS thumbnail_image FROM tickers LEFT JOIN ticker_symbols ON tickers.base_symbol = ticker_symbols.symbol LEFT JOIN ticker_thumbnails ON tickers.base_symbol = ticker_thumbnails.symbol COLLATE NOCASE")
    fun getTickerWithSymbolAndThumbnail(): List<TickerWithSymbolAndThumbnail>

    @Transaction
    @Query("SELECT * FROM tickers ORDER BY current_price DESC")
    fun getAllTickers(): List<Ticker>

    @Query("DELETE FROM tickers")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(ticker: Ticker)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tickers: List<Ticker>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ticker: Ticker)

    @Update
    suspend fun update(tickers: List<Ticker>)

}