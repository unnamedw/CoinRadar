package kr.co.douchgosum.android.coinradar.data.repository

import android.util.Log
import com.squareup.moshi.Moshi
import kr.co.douchgosum.android.coinradar.data.db.*
import kr.co.douchgosum.android.coinradar.data.remote.bithumb.BithumbApiService
import kr.co.douchgosum.android.coinradar.data.remote.bithumb.BithumbTicker
import org.koin.java.KoinJavaComponent.get

class BithumbRepository(
    private val bithumbApiService: BithumbApiService
): Repository() {

    suspend fun getAllTickers(): List<Ticker> {
        if (isNetworkAvailable()) {
            fetchTickerData()
        }
        return tickerDao.getAllTickers()
    }

    suspend fun getAllTickerWithSymbolAndThumbnails(): List<TickerWithSymbolAndThumbnail> {
        if (isNetworkAvailable()) {
            fetchTickerData()
        }
        return tickerDao.getTickerWithSymbolAndThumbnail()
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