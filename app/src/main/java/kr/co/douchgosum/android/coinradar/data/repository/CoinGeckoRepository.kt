package kr.co.douchgosum.android.coinradar.data.repository

import android.content.Context
import kr.co.douchgosum.android.coinradar.data.db.Ticker
import kr.co.douchgosum.android.coinradar.data.db.TickerDao
import kr.co.douchgosum.android.coinradar.data.remote.coingecko.CoinGeckoApiService
import kr.co.douchgosum.android.coinradar.data.remote.coingecko.CoinGeckoExchange

class CoinGeckoRepository(
    context: Context,
    private val coinGeckoApiService: CoinGeckoApiService,
    private val tickerDao: TickerDao
): Repository(context) {

    suspend fun getAllTickers(currency: String): List<Ticker> {
        if (isNetworkAvailable()) {
            coinGeckoApiService.getAllTickers().map { coinGeckoTicker ->
                coinGeckoTicker.toTicker(currency)
            }
            .also { tickers ->
                tickerDao.insertAll(tickers)
            }
        }
        return tickerDao.getAllTickers()
    }

    fun updateTickers() {

    }

    fun getAllExchanges(): List<CoinGeckoExchange> {
        return emptyList()
    }


}

