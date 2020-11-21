package kr.co.douchgosum.android.coinradar.data.repository

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kr.co.douchgosum.android.coinradar.data.remote.gopax.GopaxApiService
import kr.co.douchgosum.android.coinradar.data.db.Ticker

class GopaxRepository(
    context: Context,
    private val gopaxApiService: GopaxApiService
): Repository(context) {

    suspend fun getAllTickers(): List<Ticker> {
        var tickerList = emptyList<Ticker>()
        if (isNetworkAvailable()) {
             tickerList = gopaxApiService.getTickers().map { gopaxTicker ->
                gopaxTicker.toTicker()
            }
        }

        return tickerList
    }




}



