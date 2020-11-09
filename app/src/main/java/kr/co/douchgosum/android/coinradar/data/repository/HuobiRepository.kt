package kr.co.douchgosum.android.coinradar.data.repository

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kr.co.douchgosum.android.coinradar.data.remote.ticker.HuobiTickerApiService
import kr.co.douchgosum.android.coinradar.data.remote.ticker.Ticker

class HuobiRepository (
    context: Context,
    private val huobiTickerApiService: HuobiTickerApiService
): Repository(context) {

    suspend fun getAllTickers(): Flow<Ticker> = flow {
        if (isNetworkAvailable()) {
            huobiTickerApiService.getTickers()
                .run {
                    val timestamp = ts
                    data.map { huobiTicker ->
                        val dividedSymbol = divideHuobiSymbol(huobiTicker.symbol)
                        val ticker =
                            Ticker(
                                baseCurrency = dividedSymbol[0],
                                quoteCurrency = dividedSymbol[1],
                                openPrice = huobiTicker.open,
                                closePrice = huobiTicker.close,
                                timeStamp = timestamp
                            )
                        emit(ticker)
                    }
                }
        }
    }.flowOn(Dispatchers.IO)

    private fun divideHuobiSymbol(symbol: String): List<String> {
        val suffixes = listOf("btc", "krw", "eth", "ht", "usdt")
        suffixes.forEach { suffix ->
            if (symbol.endsWith(suffix, ignoreCase = true)) {
                return listOf(symbol.replace(suffix, "", ignoreCase = true), suffix)
            }
        }
        return emptyList()
    }

}