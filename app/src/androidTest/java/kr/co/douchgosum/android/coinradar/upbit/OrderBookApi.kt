package kr.co.douchgosum.android.coinradar.upbit

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val BASE_URL = "https://api.upbit.com/v1/"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

object OrderBookApi {
    val retrofitService: OrderBookApiService by lazy {
        retrofit.create(OrderBookApiService::class.java)
    }
}

interface OrderBookApiService {

    @GET("orderbook")
    suspend fun orderbookRequest(
        @Query("markets") id: String? = "krw-btc"
    ): List<OrderBook>
}