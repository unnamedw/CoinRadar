package kr.co.douchgosum.android.coinradar.data.repository

import kr.co.douchgosum.android.coinradar.data.db.Ticker
import kr.co.douchgosum.android.coinradar.data.db.TickerDao
import kr.co.douchgosum.android.coinradar.data.db.TickerWithSymbolAndThumbnail
import kr.co.douchgosum.android.coinradar.data.remote.coingecko.CoinGeckoApiService
import kr.co.douchgosum.android.coinradar.data.remote.coingecko.CoinGeckoExchange

class CoinGeckoRepository(
    private val coinGeckoApiService: CoinGeckoApiService
): Repository() {

    suspend fun getAllTickers(currency: String): List<TickerWithSymbolAndThumbnail> {
        if (isNetworkAvailable()) {
            coinGeckoApiService.getTickers().map { coinGeckoTicker ->
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

