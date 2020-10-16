package kr.co.douchgosum.android.coinradar.data.repository

import android.content.Context
import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kr.co.douchgosum.android.coinradar.MyApplication
import kr.co.douchgosum.android.coinradar.data.Ticker
import kr.co.douchgosum.android.coinradar.data.api.BithumbApiService
import kr.co.douchgosum.android.coinradar.data.entity.BithumbTicker
import org.json.JSONObject
import org.koin.experimental.property.inject
import java.util.*

class BithumbDataRepository(
    context: Context,
    private val bithumbApiService: BithumbApiService
): DataRepository(context) {

    override suspend fun getAllTickers(): Flow<Ticker> = flow {
        //네트워크 연결 가능한 경우
        if (isNetworkAvailable()) {
            bithumbApiService.getTickers()
                    //응답실패 체크
                .also {
                    if (it.status != "0000") {
                        Log.d("TAG", "status: ${it.status}")
                    }
                }
                    //데이터 파싱
                .data
                .run {
                    val date = (get("date") as String).toLong()
                    map {
                        if (it.key != "date") {
                            val moshi = Moshi.Builder()
                                .add(KotlinJsonAdapterFactory())
                                .build()
                            val adapter = moshi.adapter(BithumbTicker::class.java)
                            val bithumbTicker = adapter.fromJsonValue(it.value)!!
                            val ticker = Ticker(
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
        //네트워크 연결이 끊어져 있는 경우우
       else {

        }
    }.flowOn(Dispatchers.IO)

}