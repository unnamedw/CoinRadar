package kr.co.douchgosum.android.coinradar.data.remote.ticker

import retrofit2.http.GET
import retrofit2.http.Path

interface BithumbTickerApiService {

/**
 * KRW 만 지원
 *
 * */
    @GET("ticker/{order_payment}")
    suspend fun getTickers(
        @Path("order_payment") orderPayment: String = "all_krw"
    ): BithumbTickerResponse


}