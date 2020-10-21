package kr.co.douchgosum.android.coinradar.gopax

import kotlinx.coroutines.runBlocking

class GopaxTest {
    //    @Test
    fun gopaxTest() = runBlocking {
//        val result = GopaxApi.retrofitService.getTickers()
//        println("result= $result")
    }
//    suspend fun gopaxFlow(): Flow<Ticker> = flow {
//        if (true) {
//            GopaxApi.service.getTickers()
//                .map {
//                    val currency = it.name.split('-')
//                    val timeStamp = dateToMillis(it.time, Exchange.GOPAX)
//                    val ticker = Ticker(
//                        baseCurrency = currency[0],
//                        quoteCurrency = currency[1],
//                        openPrice = it.open,
//                        closePrice = it.close,
//                        timeStamp = timeStamp
//                    )
//                    emit(ticker)
//                }
//
//        } else {
//
//        }
//    }.flowOn(Dispatchers.IO)
}