package kr.co.douchgosum.android.coinradar.data.repository

import com.squareup.moshi.Moshi
import kr.co.douchgosum.android.coinradar.data.remote.coinone.CoinoneApiService
import kr.co.douchgosum.android.coinradar.data.remote.coinone.CoinoneTicker
import kr.co.douchgosum.android.coinradar.data.db.Ticker
import org.koin.java.KoinJavaComponent.get

class CoinoneRepository(
    private val coinoneApiService: CoinoneApiService
): Repository() {

    suspend fun getAllTickers(): List<Ticker> {
        var tickerList = emptyList<Ticker>()
        if (isNetworkAvailable()) {
            val adapter = moshi.adapter(CoinoneTicker::class.java)

            coinoneApiService.getTickers().run {
                val result = (this["result"] as String)
                val errorCode = (this["errorCode"] as String).toInt()
                val timestamp = (this["timestamp"] as String).toLong()

                tickerList = filterNot {
                    val key = it.key
                    key.equals("result", true) ||
                            key.equals("errorcode", true) ||
                            key.equals("timestamp", true)
                }.map { tickerItem ->
                    val coinoneTicker = adapter.fromJsonValue(tickerItem.value)!!
                    coinoneTicker.toTicker(tickerItem.key, timestamp)
                }.also { data ->
                    tickerDao.insertAll(data)
                }
            }
        }
        return tickerList
    }
}