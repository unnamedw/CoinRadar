package kr.co.douchgosum.android.coinradar.data.remote.bithumb

import retrofit2.http.GET
import retrofit2.http.Path

interface BithumbApiService {

/**
 * KRW 만 지원
 *
 * */
    @GET("ticker/{order_payment}")
    suspend fun getTickers(
        @Path("order_payment") orderPayment: String = "all_krw"
    ): BithumbTickerResponse


}