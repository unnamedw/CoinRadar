package kr.co.douchgosum.android.coinradar.data.remote.huobi

import retrofit2.http.GET

interface HuobiApiService {

/**
 * KRW, ETH, BTC, HC(Huobi Coin) 지원
 *
 * */
    @GET("market/tickers")
    suspend fun getTickers(): HuobiTickerResponse
}