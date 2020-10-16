package kr.co.douchgosum.android.coinradar.data.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private const val BASE_URL = "https://api.upbit.com/v1/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

object UpbitApi {
    val retrofitService: UpbitApiService by lazy {
        retrofit.create(
            UpbitApiService::class.java)
    }
}