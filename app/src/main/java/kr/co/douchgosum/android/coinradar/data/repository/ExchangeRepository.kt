package kr.co.douchgosum.android.coinradar.data.repository

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kr.co.douchgosum.android.coinradar.data.remote.exchange.CoinGeckoExchangeService
import kr.co.douchgosum.android.coinradar.data.remote.entity.CoinGeckoExchange

class ExchangeRepository(
    context: Context,
    private val coinGeckoExchangeService: CoinGeckoExchangeService
): Repository(context) {

    suspend fun getAllExchanges(): Flow<CoinGeckoExchange> = flow {
        coinGeckoExchangeService.getAllExchanges()
            .forEach { coinGeckoExchange ->
                emit(coinGeckoExchange)
            }
    }.flowOn(Dispatchers.IO)

}