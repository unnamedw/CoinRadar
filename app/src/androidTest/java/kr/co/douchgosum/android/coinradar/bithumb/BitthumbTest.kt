package kr.co.douchgosum.android.coinradar.bithumb

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kr.co.douchgosum.android.coinradar.data.Ticker
import kr.co.douchgosum.android.coinradar.data.api.BithumbApi
import kr.co.douchgosum.android.coinradar.data.entity.BithumbTicker

class BitthumbTest {
    //    @Test
    fun bithumbApiTest() = runBlocking {
//        val result = BithumbApi.retrofitService.getTickers()
//        println("result= $result")
        bithumbFlow().collect {
            println(it.timeStamp)
        }

    }
    suspend fun bithumbFlow(): Flow<Ticker> = flow{
        BithumbApi.service.getTickers()
            //응답실패 체크
            .also {
                if (it.status != "0000") {
                    Log.d("MyTag", "status: ${it.status}")
                }
            }
            //데이터 파싱
            .data
            .run {
                val date = (get("date") as String).toLong()
                map {
                    if (it.key != "date") {
//                        Log.d("MyTag", "value: ${it.value}")
                        val moshi = Moshi.Builder()
                            .add(KotlinJsonAdapterFactory())
                            .build()
                        val adapter = moshi.adapter(BithumbTicker::class.java)
                        val bithumbTicker = adapter.fromJsonValue(it.value)!!
                        val ticker =
                            Ticker(
                                baseCurrency = it.key,
                                quoteCurrency = "krw",
                                openPrice = bithumbTicker.openingPrice.toDouble(),
                                closePrice = bithumbTicker.closingPrice.toDouble(),
                                timeStamp = date
                            )
                        println("티커 = $ticker")
                        emit(ticker)
                    }
                }
            }
    }
}