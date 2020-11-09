package kr.co.douchgosum.android.coinradar.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import kr.co.douchgosum.android.coinradar.data.remote.ticker.Ticker

@Dao
interface TickerDao {


    @Query("SELECT * from tickers")
    fun getAllTickers(): LiveData<List<Ticker>>

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