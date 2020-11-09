package kr.co.douchgosum.android.coinradar.data.remote.ticker

import retrofit2.http.GET


interface GopaxTickerApiService {


/**
 * BTC(37), KRW(69) 지원
 * */
    @GET("trading-pairs/stats")
    suspend fun getTickers(): List<GopaxTicker>

}

