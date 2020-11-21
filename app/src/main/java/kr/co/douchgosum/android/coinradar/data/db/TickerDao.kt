package kr.co.douchgosum.android.coinradar.data.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TickerDao {

    @Query("SELECT * from tickers ORDER BY current_price DESC")
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