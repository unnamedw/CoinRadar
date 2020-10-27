package kr.co.douchgosum.android.coinradar.data.remote.ticker

import kr.co.douchgosum.android.coinradar.data.remote.entity.HuobiTickerResponse
import retrofit2.http.GET

interface HuobiTickerApiService {

/**
 * KRW, ETH, BTC, HC(Huobi Coin) 지원
 *
 * */
    @GET("market/tickers")
    suspend fun getTickers(): HuobiTickerResponse
}