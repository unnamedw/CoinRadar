package kr.co.douchgosum.android.coinradar.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kr.co.douchgosum.android.coinradar.data.remote.ticker.BithumbTickerApiService
import kr.co.douchgosum.android.coinradar.data.db.TickerDao
import kr.co.douchgosum.android.coinradar.data.remote.ticker.BithumbTicker
import kr.co.douchgosum.android.coinradar.data.remote.ticker.Ticker
import org.koin.java.KoinJavaComponent.get

/**
 * Mapper 를 쓰지 않고
 * Repository 에 API Entity 와 Domain Model 을 Mapping 하는 작업을 구현
 *
 * */
class BithumbRepository(
    context: Context,
    private val bithumbTickerApiService: BithumbTickerApiService,
    private val bithumbDao: TickerDao
): Repository(context) {

    val moshi = get(Moshi::class.java)


    fun getAll(): LiveData<List<Ticker>> {
        return bithumbDao.getAllTickers()
    }

    suspend fun update() {
        //네트워크 연결 가능한 경우
        if (isNetworkAvailable()) {
            bithumbTickerApiService.getTickers()
                .also {
                    if (it.status != "0000") {
                        Log.d("TAG", "status: ${it.status}")
                    }
                }
                .data.run {
                    val moshi = Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                    val adapter = moshi.adapter(BithumbTicker::class.java)
                    val date = (get("date") as String).toLong()
                    val result = mutableListOf<Ticker>()
                    map {
                        if (it.key != "date") {
                            val bithumbTicker = adapter.fromJsonValue(it.value)!!
                            val ticker =
                                Ticker(
                                    baseCurrency = it.key,
                                    quoteCurrency = "krw",
                                    openPrice = bithumbTicker.openingPrice.toDouble(),
                                    closePrice = bithumbTicker.closingPrice.toDouble(),
                                    timeStamp = date
                                )
                            result.add(ticker)
                        }
                    }
//                    bithumbDao.insertAll(result)
                    bithumbDao.insertAll(result)
                }
        }
    }

    suspend fun getAllTickers(): Flow<Ticker> = flow {
        //네트워크 연결 가능한 경우
        if (isNetworkAvailable()) {
            bithumbTickerApiService.getTickers()
                .also {
                    if (it.status != "0000") {
                        Log.d("TAG", "status: ${it.status}")
                    }
                }
                .data.run {
                    val adapter = moshi.adapter(BithumbTicker::class.java)
                    val date = (get("date") as String).toLong()
                    map {
                        if (it.key != "date") {
                            val bithumbTicker = adapter.fromJsonValue(it.value)!!
                            val ticker =
                                Ticker(
                                    baseCurrency = it.key,
                                    quoteCurrency = "krw",
                                    openPrice = bithumbTicker.openingPrice.toDouble(),
                                    closePrice = bithumbTicker.closingPrice.toDouble(),
                                    timeStamp = date
                                )
                            emit(ticker)
                        }
                    }
                }
        }
    }.flowOn(Dispatchers.IO)

}