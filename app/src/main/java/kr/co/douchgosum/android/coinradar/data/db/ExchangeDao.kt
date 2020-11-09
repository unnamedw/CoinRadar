package kr.co.douchgosum.android.coinradar.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kr.co.douchgosum.android.coinradar.data.remote.exchange.CoinGeckoExchange

@Dao
interface ExchangeDao {

    @Query("SELECT * FROM coingecko_exchanges")
    fun getAllExchanges(): LiveData<List<CoinGeckoExchange>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExchanges(exchanges: List<CoinGeckoExchange>)

}