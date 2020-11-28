package kr.co.douchgosum.android.coinradar.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kr.co.douchgosum.android.coinradar.data.remote.coingecko.CoinGeckoExchange

@Dao
interface ExchangeDao {

    @Query("SELECT * FROM coingecko_exchanges")
    fun getAll(): LiveData<List<CoinGeckoExchange>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(exchanges: List<CoinGeckoExchange>)

}