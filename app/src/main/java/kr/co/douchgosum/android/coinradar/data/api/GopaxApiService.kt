package kr.co.douchgosum.android.coinradar.data.api

import kr.co.douchgosum.android.coinradar.data.entity.GopaxTicker
import retrofit2.http.GET


interface GopaxApiService {


/**
 * BTC(37), KRW(69) 지원
 * */
    @GET("trading-pairs/stats")
    suspend fun getTickers(): List<GopaxTicker>

}

