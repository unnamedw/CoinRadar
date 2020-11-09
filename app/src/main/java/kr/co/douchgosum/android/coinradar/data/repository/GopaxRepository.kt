package kr.co.douchgosum.android.coinradar.data.repository

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kr.co.douchgosum.android.coinradar.data.remote.ticker.GopaxTickerApiService
import kr.co.douchgosum.android.coinradar.data.remote.ticker.Ticker
import java.text.SimpleDateFormat
import java.util.*

class GopaxRepository(
    context: Context,
    private val gopaxTickerApiService: GopaxTickerApiService
): Repository(context) {

    suspend fun getAllTickers(): Flow<Ticker> = flow {
        if (isNetworkAvailable()) {
            gopaxTickerApiService.getTickers()
                .map {
                    val currency = it.name.split('-')
                    val timeStamp = dateToMillis(it.time)
                    val ticker =
                        Ticker(
                            baseCurrency = currency[0],
                            quoteCurrency = currency[1],
                            openPrice = it.open,
                            closePrice = it.close,
                            timeStamp = timeStamp
                        )
                    emit(ticker)
                }
        } else {

        }
    }.flowOn(Dispatchers.IO)


    private fun dateToMillis(utc: String): Long {
        val utcStr = utc.toLowerCase(Locale.ROOT)
            .replace("t", " ")
            .replace("z", "")
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.KOREA)
            .apply {
                timeZone = TimeZone.getTimeZone("UTC")
            }
        val date = format.parse(utcStr)

        return date?.time ?: -1
    }
}



