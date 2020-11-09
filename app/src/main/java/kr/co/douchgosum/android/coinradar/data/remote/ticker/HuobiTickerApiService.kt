package kr.co.douchgosum.android.coinradar.data.remote.ticker

import retrofit2.http.GET

interface HuobiTickerApiService {

/**
 * KRW, ETH, BTC, HC(Huobi Coin) 지원
 *
 * */
    @GET("market/tickers")
    suspend fun getTickers(): HuobiTickerResponse
}