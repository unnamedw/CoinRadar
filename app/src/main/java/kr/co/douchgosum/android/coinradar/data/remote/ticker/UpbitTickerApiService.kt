package kr.co.douchgosum.android.coinradar.data.remote.ticker

import kr.co.douchgosum.android.coinradar.data.remote.entity.UpbitMarket
import kr.co.douchgosum.android.coinradar.data.remote.entity.UpbitTicker
import retrofit2.http.GET
import retrofit2.http.Query

interface UpbitTickerApiService {

    @GET("ticker")
    suspend fun getTickers(
        @Query("markets") markets: List<String>
    ): List<UpbitTicker>

    @GET("market/all")
    suspend fun getAllMarkets(): List<UpbitMarket>
}