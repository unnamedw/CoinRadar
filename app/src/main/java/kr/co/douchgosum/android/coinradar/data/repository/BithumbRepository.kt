package kr.co.douchgosum.android.coinradar.data.repository

import android.util.Log
import com.squareup.moshi.Moshi
import kr.co.douchgosum.android.coinradar.data.remote.bithumb.BithumbApiService
import kr.co.douchgosum.android.coinradar.data.db.TickerDao
import kr.co.douchgosum.android.coinradar.data.remote.bithumb.BithumbTicker
import kr.co.douchgosum.android.coinradar.data.db.Ticker
import kr.co.douchgosum.android.coinradar.data.db.TickerWithSymbol
import kr.co.douchgosum.android.coinradar.data.db.TickerWithSymbolAndThumbnail
import org.koin.java.KoinJavaComponent.get

class BithumbRepository(
    private val bithumbApiService: BithumbApiService
): Repository() {

    suspend fun getAllTickers(): List<TickerWithSymbolAndThumbnail> {
        if (isNetworkAvailable()) {
            fetchTickerData()
        }
        return tickerDao.getAllTickers()
    }

    private suspend fun fetchTickerData() {
        bithumbApiService.getTickers().also {
            if (it.status != "0000") {
                Log.d("TAG", "status: ${it.status}")
            }
        }.data.run {
            val adapter = moshi.adapter(BithumbTicker::class.java)
            val date = (get("date") as String).toLong()
            val tickerList = filter {
                it.key != "date"
            }.map {
                val bithumbTicker = adapter.fromJsonValue(it.value)!!
                bithumbTicker.toTicker(it.key, date)
            }.also { data ->
                tickerDao.insertAll(data)
            }
        }
    }

    suspend fun getAllTickerWithSymbols(): List<TickerWithSymbol> {
        return tickerDao.getAllTickerWithSymbols()
    }

}