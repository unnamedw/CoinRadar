package kr.co.douchgosum.android.coinradar.data.repository

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kr.co.douchgosum.android.coinradar.data.Exchange
import kr.co.douchgosum.android.coinradar.data.Ticker
import kr.co.douchgosum.android.coinradar.data.api.GopaxApi
import kr.co.douchgosum.android.coinradar.data.api.GopaxApiService
import kr.co.douchgosum.android.coinradar.utils.dateToMillis
import java.text.SimpleDateFormat
import java.util.*

class GopaxDataRepository(
    context: Context,
    private val gopaxApiService: GopaxApiService
): DataRepository(context) {

    override suspend fun getAllTickers(): Flow<Ticker> = flow {
        if (isNetworkAvailable()) {
            GopaxApi.retrofitService.getTickers()
                .map {
                    val currency = it.name.split('-')
                    val timeStamp = dateToMillis(it.time, Exchange.GOPAX)
                    val ticker = Ticker(
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

}

