package kr.co.douchgosum.android.coinradar.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import kr.co.douchgosum.android.coinradar.data.db.ExchangeDao
import kr.co.douchgosum.android.coinradar.data.remote.exchange.CoinGeckoExchangeService
import kr.co.douchgosum.android.coinradar.data.remote.exchange.CoinGeckoExchange

class ExchangeRepository(
    context: Context,
    private val exchangeDao: ExchangeDao,
    private val coinGeckoExchangeService: CoinGeckoExchangeService
): Repository(context) {

    fun getAllExchanges(): LiveData<List<CoinGeckoExchange>> = exchangeDao.getAllExchanges()

    suspend fun update() {
        exchangeDao.insertExchanges(
            coinGeckoExchangeService.getAllExchanges()
        )
    }

//    suspend fun updateExchanges(): Flow<CoinGeckoExchange> = flow {
//        coinGeckoExchangeService.getAllExchanges()
//            .forEach { coinGeckoExchange ->
//                emit(coinGeckoExchange)
//            }
//    }.flowOn(Dispatchers.IO)

}