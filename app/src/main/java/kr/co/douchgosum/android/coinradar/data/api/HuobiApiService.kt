package kr.co.douchgosum.android.coinradar.data.api

import kr.co.douchgosum.android.coinradar.data.entity.HuobiTickerResponse
import retrofit2.http.GET

interface HuobiApiService {

/**
 * KRW, ETH, BTC, HC(Huobi Coin) 지원
 *
 * */
    @GET("market/tickers")
    suspend fun getTickers(): HuobiTickerResponse
}