package kr.co.douchgosum.android.coinradar.data.repository

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kr.co.douchgosum.android.coinradar.data.Exchange
import kr.co.douchgosum.android.coinradar.data.Ticker
import kr.co.douchgosum.android.coinradar.data.api.UpbitApiService
import kr.co.douchgosum.android.coinradar.data.entity.UpbitMarket

class UpbitRepository(
    context: Context,
    private val upbitApiService: UpbitApiService
) : Repository(context) {

    override suspend fun getAllTickers(): Flow<Ticker> = flow {
        if (isNetworkAvailable()) {
            val marketList = getAllMarkets()
                .map {
                    it.market
                }.toList()
            upbitApiService.getTickers(markets = marketList)
                .map { upbitTicker ->
                    val marketSymbol = upbitTicker.market.split("-")
                    val ticker =
                        Ticker(
                            baseCurrency = marketSymbol[1],
                            quoteCurrency = marketSymbol[0],
                            openPrice = upbitTicker.openingPrice,
                            closePrice = upbitTicker.tradePrice,
                            timeStamp = upbitTicker.timestamp
                        )
                    emit(ticker)
                }
        }
    }

    suspend fun getAllMarkets(): Flow<UpbitMarket> = flow {
        upbitApiService.getAllMarkets()
            .map {upbitMarket ->
                emit(upbitMarket)
            }
    }.flowOn(Dispatchers.IO)
}