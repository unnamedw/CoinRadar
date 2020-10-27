package kr.co.douchgosum.android.coinradar.data.remote.ticker

import retrofit2.http.GET
import retrofit2.http.Query

interface CoinoneTickerApiService {


/**
* KRW 만 지원
 *
* */
    @GET("ticker")
    suspend fun getTickers(
        @Query("currency") currency: String = "all"
    ): Map<String, Any>


}