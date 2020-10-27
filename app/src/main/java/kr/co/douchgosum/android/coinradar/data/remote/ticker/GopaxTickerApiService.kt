package kr.co.douchgosum.android.coinradar.data.remote.ticker

import kr.co.douchgosum.android.coinradar.data.remote.entity.GopaxTicker
import retrofit2.http.GET


interface GopaxTickerApiService {


/**
 * BTC(37), KRW(69) 지원
 * */
    @GET("trading-pairs/stats")
    suspend fun getTickers(): List<GopaxTicker>

}

