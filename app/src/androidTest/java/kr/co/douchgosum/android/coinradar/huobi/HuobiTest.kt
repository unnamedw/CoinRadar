package kr.co.douchgosum.android.coinradar.huobi

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking
import kr.co.douchgosum.android.coinradar.data.Ticker
import kr.co.douchgosum.android.coinradar.data.api.HuobiApi
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HuobiTest {
    @Test
    fun huobiTest() = runBlocking {
//        val result = HuobiApi.retrofitService.getTickers()
////        println("result= $result")
        val response = getAllTickers()
        response.collect {
            println(it)
        }
    }

    suspend fun getAllTickers(): Flow<Ticker> = flow {
        if (true) {
            HuobiApi.retrofitService.getTickers()
                .run {
                    val timestamp = ts
                    data.map { huobiTicker ->
                        val dividedSymbol = divideHuobiSymbol(huobiTicker.symbol).also {
                            if (it.isEmpty()) {
                                println("후오비 심볼: ${huobiTicker.symbol}")
                            }
                        }
                        val ticker = Ticker(
                            baseCurrency = dividedSymbol[0],
                            quoteCurrency = dividedSymbol[1],
                            openPrice = huobiTicker.open,
                            closePrice = huobiTicker.close,
                            timeStamp = timestamp
                        )
                        emit(ticker)
                    }
                }
        }
    }.flowOn(Dispatchers.IO)

    private fun divideHuobiSymbol(symbol: String): List<String> {
        val suffixes = listOf("btc", "krw", "eth", "ht", "usdt")
        suffixes.forEach { suffix ->
            if (symbol.endsWith(suffix, ignoreCase = true)) {
                return listOf(symbol.replace(suffix, "", ignoreCase = true), suffix)
            }
        }
        return emptyList()
    }
}