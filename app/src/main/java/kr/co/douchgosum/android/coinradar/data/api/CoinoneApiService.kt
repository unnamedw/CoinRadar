package kr.co.douchgosum.android.coinradar.data.api

import retrofit2.http.GET
import retrofit2.http.Query

interface CoinoneApiService {


/**
* KRW 만 지원
 *
* */
    @GET("ticker")
    suspend fun getTickers(
        @Query("currency") currency: String = "all"
    ): Map<String, Any>


}