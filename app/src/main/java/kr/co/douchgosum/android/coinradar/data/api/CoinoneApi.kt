package kr.co.douchgosum.android.coinradar.data.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private const val BASE_URL = "https://api.coinone.co.kr/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

object CoinoneApi {
    val retrofitService: CoinoneApiService by lazy {
        retrofit.create(
            CoinoneApiService::class.java)
    }
}