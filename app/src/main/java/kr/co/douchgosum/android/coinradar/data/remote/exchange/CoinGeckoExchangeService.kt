package kr.co.douchgosum.android.coinradar.data.remote.exchange

import retrofit2.http.GET
import retrofit2.http.Query

interface CoinGeckoExchangeService {

    /**
     * 총 370여개 정도 거래소가 있음
     * */
    @GET
    suspend fun getAllExchanges(
        @Query("per_page") perPage: Int = 100,
        @Query("page") page: Int = 1
    ): List<CoinGeckoExchange>

}