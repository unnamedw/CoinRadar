package kr.co.douchgosum.android.coinradar.data.remote.upbit

import retrofit2.http.GET
import retrofit2.http.Query

interface UpbitApiService {

    @GET("ticker")
    suspend fun getTickers(
        @Query("markets") markets: List<String>
    ): List<UpbitTicker>

    @GET("market/all")
    suspend fun getAllMarkets(): List<UpbitMarket>
}