package kr.co.douchgosum.android.coinradar.data.api

import kr.co.douchgosum.android.coinradar.data.entity.BithumbTickerResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface BithumbApiService {

/**
 * UnitTest 시 반드시 suspend 로 선언하는 것 잊어버리지 말 것! Adapter Error 의 원인 -> Data 받아오기 전에 종료
 * */


/**
 * KRW 만 지원
 * */
    @GET("ticker/{order_payment}")
    suspend fun getTickers(
        @Path("order_payment") orderPayment: String = "all_krw"
    ): BithumbTickerResponse
}