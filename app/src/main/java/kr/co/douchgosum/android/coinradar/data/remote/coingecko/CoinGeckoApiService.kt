package kr.co.douchgosum.android.coinradar.data.remote.coingecko

import retrofit2.http.GET
import retrofit2.http.Query

interface CoinGeckoApiService {

    @GET("coins/markets")
    suspend fun getAllTickers(
        @Query("vs_currency") currency: String = "krw",
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 100
    ): List<CoinGeckoTicker>

    /**
     * 총 370여개 정도 거래소가 있음
     * */
    @GET
    suspend fun getAllExchanges(
        @Query("per_page") perPage: Int = 100,
        @Query("page") page: Int = 1
    ): List<CoinGeckoExchange>

}