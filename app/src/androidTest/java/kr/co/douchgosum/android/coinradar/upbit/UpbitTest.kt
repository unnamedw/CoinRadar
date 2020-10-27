package kr.co.douchgosum.android.coinradar.upbit

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kr.co.douchgosum.android.coinradar.data.Ticker
import kr.co.douchgosum.android.coinradar.data.api.UpbitApi
import kr.co.douchgosum.android.coinradar.data.remote.entity.UpbitMarket
import org.json.JSONArray
import org.junit.Test

class UpbitTest {

    @Test
    fun upbitTest(): Unit = runBlocking<Unit> {
        val marketList = getAllMarkets().map {
            it.market
        }.toList()
        UpbitApi.service.getTickers(markets = marketList)
            .map { upbitTicker ->
                val marketSymbol = upbitTicker.market.split("-")
                val ticker =
                    Ticker(
                        baseCurrency = marketSymbol[1],
                        quoteCurrency = marketSymbol[0],
                        openPrice = upbitTicker.openingPrice,
                        closePrice = upbitTicker.tradePrice,
                        timeStamp = upbitTicker.timestamp
                    )
                println(ticker)
            }
        println(System.currentTimeMillis())
    }

    //    @Test
    fun upbitApiTest() = runBlocking {
        val markets = listOf("krw-btc","krw-eth","krw-btt")
        val result = UpbitApi.service.getTickers(markets)
        println("result= ${JSONArray(result).length()}")
    }



    //    @Test
    fun orderBookTest() {
        val result = runBlocking {
            val request = OrderBookApi.retrofitService.orderbookRequest("krw-btt")

        }

        Log.d("MyUpbit", "Json: ${result}")

    }



    suspend fun getAllMarkets(): Flow<UpbitMarket> = flow {
        UpbitApi.service.getAllMarkets()
            .map {upbitMarket ->
                emit(upbitMarket)
            }
    }.flowOn(Dispatchers.IO)

}