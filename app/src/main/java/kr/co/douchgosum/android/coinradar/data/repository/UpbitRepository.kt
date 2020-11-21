package kr.co.douchgosum.android.coinradar.data.repository

import android.content.Context
import kotlinx.coroutines.flow.*
import kr.co.douchgosum.android.coinradar.data.db.Ticker
import kr.co.douchgosum.android.coinradar.data.remote.upbit.UpbitApiService
import kr.co.douchgosum.android.coinradar.data.remote.upbit.UpbitMarket

class UpbitRepository(
    context: Context,
    private val upbitApiService: UpbitApiService
) : Repository(context) {

    suspend fun getAllTickers(): List<Ticker> {
        var tickerList = emptyList<Ticker>()
        if (isNetworkAvailable()) {
            val marketList = getAllMarkets().map {
                it.market
            }.toList()
            tickerList = upbitApiService.getTickers(marketList).map { upbitTicker ->
                upbitTicker.toTicker()
            }
        }
        return tickerList
    }

    private suspend fun getAllMarkets(): List<UpbitMarket> = upbitApiService.getAllMarkets()
}