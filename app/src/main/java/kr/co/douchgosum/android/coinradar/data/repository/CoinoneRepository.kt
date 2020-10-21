package kr.co.douchgosum.android.coinradar.data.repository

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kr.co.douchgosum.android.coinradar.data.Exchange
import kr.co.douchgosum.android.coinradar.data.Ticker
import kr.co.douchgosum.android.coinradar.data.api.CoinoneApiService
import kr.co.douchgosum.android.coinradar.data.entity.CoinoneTicker

class CoinoneRepository(
    context: Context,
    private val coinoneApiService: CoinoneApiService
): Repository(context) {

    override suspend fun getAllTickers(): Flow<Ticker> = flow {
        if (isNetworkAvailable()) {
            coinoneApiService.getTickers()
                .run {
                    val moshi = Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                    val adapter = moshi.adapter(CoinoneTicker::class.java)
                    val result = (get("result") as String)
                    val errorCode = (get("errorCode") as String).toInt()
                    val timestamp = (get("timestamp") as String).toLong()
                    filterNot {
                        val key = it.key
                        key.equals("result", true) ||
                                key.equals("errorcode", true) ||
                                key.equals("timestamp", true)
                    }.map {
                        val coinoneTicker = adapter.fromJsonValue(it.value)!!
                        val ticker =
                            Ticker(
                                baseCurrency = it.key,
                                quoteCurrency = "krw",
                                openPrice = coinoneTicker.first,
                                closePrice = coinoneTicker.last,
                                timeStamp = timestamp * 1000
                            )
                        emit(ticker)
                    }
                }
        } else {

        }
    }.flowOn(Dispatchers.IO)
}