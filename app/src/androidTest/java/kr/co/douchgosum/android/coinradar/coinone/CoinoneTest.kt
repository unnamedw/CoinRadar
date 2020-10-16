package kr.co.douchgosum.android.coinradar.coinone

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking
import kr.co.douchgosum.android.coinradar.data.Ticker
import kr.co.douchgosum.android.coinradar.data.api.CoinoneApi
import kr.co.douchgosum.android.coinradar.data.entity.CoinoneTicker

class CoinoneTest {
    //    @Test
    fun coinoneTest() = runBlocking {
//        val result = CoinoneApi.retrofitService.getTickers()
//        println("result= $result")
        val response = coinoneFlow()
        response.collect {
            println(it)
        }

    }
    suspend fun coinoneFlow(): Flow<Ticker> = flow {
        if (true) {
            CoinoneApi.retrofitService.getTickers()
                .run {
                    val result = (get("result") as String)
                    val errorCode = (get("errorCode") as String).toInt()
                    val timestamp = (get("timestamp") as String).toLong()
                    filterNot {
                        val key = it.key
                        key.equals("result", true) ||
                                key.equals("errorcode", true) ||
                                key.equals("timestamp", true)
                    }.map {
                        val moshi = Moshi.Builder()
                            .add(KotlinJsonAdapterFactory())
                            .build()
                        val adapter = moshi.adapter(CoinoneTicker::class.java)
                        val coinoneTicker = adapter.fromJsonValue(it.value)!!
                        val ticker = Ticker(
                            baseCurrency = it.key,
                            quoteCurrency = "krw",
                            openPrice = coinoneTicker.first,
                            closePrice = coinoneTicker.last,
                            timeStamp = timestamp*1000
                        )
                        emit(ticker)
                    }
                }
        } else {

        }
    }.flowOn(Dispatchers.IO)
}