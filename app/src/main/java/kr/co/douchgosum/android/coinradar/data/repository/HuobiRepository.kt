package kr.co.douchgosum.android.coinradar.data.repository

import android.content.Context
import kr.co.douchgosum.android.coinradar.data.remote.huobi.HuobiApiService
import kr.co.douchgosum.android.coinradar.data.db.Ticker

class HuobiRepository (
    context: Context,
    private val huobiApiService: HuobiApiService
): Repository(context) {

    suspend fun getAllTickers(): List<Ticker> {
        var tickerList = emptyList<Ticker>()
        if (isNetworkAvailable()) {
            huobiApiService.getTickers().run {
                val timestamp = ts
                tickerList = data.map { huobiTicker ->
                    huobiTicker.toTicker(timestamp)
                }
            }
        }
        return tickerList
    }


}