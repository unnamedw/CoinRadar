package kr.co.douchgosum.android.coinradar.data.remote.coingecko

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinGeckoApiService {

    /**
     * 지원하는 코인의 마켓데이터(현재 가격, 시가총액, 썸네일 등)
     * */
    @GET("coins/markets")
    suspend fun getTickers(
        @Query("vs_currency") currency: String = "krw",
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 100
    ): List<CoinGeckoTicker>

    /**
     * 지원하는 모든 코인을 조회
     * */
    @GET("coins/list")
    suspend fun getAllSupportedCoinList(): List<CoinGeckoCoinListItem>

    @GET("coins/{id}")
    suspend fun getAllCoins(
        @Path("id") ids: List<String>
    ): List<Map<Any, Any>>

    /**
     * 총 370여개 정도 거래소가 있음
     * */
    @GET("exchanges")
    suspend fun getAllExchanges(
        @Query("per_page") perPage: Int = 100,
        @Query("page") page: Int = 1
    ): List<CoinGeckoExchange>



}