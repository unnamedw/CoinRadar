package kr.co.douchgosum.android.coinradar.data.repository

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kr.co.douchgosum.android.coinradar.data.Ticker
import kr.co.douchgosum.android.coinradar.data.remote.ticker.CoinoneTickerApiService
import kr.co.douchgosum.android.coinradar.data.remote.entity.CoinoneTicker

class CoinoneRepository(
    context: Context,
    private val coinoneTickerApiService: CoinoneTickerApiService
): Repository(context) {

    suspend fun getAllTickers(): Flow<Ticker> = flow {
        if (isNetworkAvailable()) {
            coinoneTickerApiService.getTickers()
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
                                timeStamp = timestamp * 1000 // 코인원은 타임스탬프가 초(s) 단위로 표시되므로 1000을 곱하여 밀리 초(ms) 단위로 변경
                            )
                        emit(ticker)
                    }
                }
        } else {

        }
    }.flowOn(Dispatchers.IO)
}