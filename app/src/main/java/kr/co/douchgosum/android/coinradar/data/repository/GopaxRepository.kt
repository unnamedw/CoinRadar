package kr.co.douchgosum.android.coinradar.data.repository

import kr.co.douchgosum.android.coinradar.data.remote.gopax.GopaxApiService
import kr.co.douchgosum.android.coinradar.data.db.Ticker

class GopaxRepository(
    private val gopaxApiService: GopaxApiService
): Repository() {

    suspend fun getAllTickers(): List<Ticker> {
        var tickerList = emptyList<Ticker>()
        if (isNetworkAvailable()) {
             tickerList = gopaxApiService.getTickers().map { gopaxTicker ->
                gopaxTicker.toTicker()
            }.also { data ->
                 tickerDao.insertAll(data)
             }
        }

        return tickerList
    }




}



