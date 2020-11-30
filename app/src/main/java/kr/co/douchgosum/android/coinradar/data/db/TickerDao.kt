package kr.co.douchgosum.android.coinradar.data.db

import androidx.room.*

@Dao
interface TickerDao {

    @Query("SELECT tickers.*, ticker_symbols.* FROM tickers LEFT JOIN ticker_symbols WHERE tickers.base_symbol = ticker_symbols.symbol COLLATE NOCASE")
    fun getAllTickerWithSymbols(): List<TickerWithSymbol>

    @Transaction
    @Query("SELECT * from tickers ORDER BY current_price DESC")
    fun getAllTickers(): List<TickerWithSymbolAndThumbnail>

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