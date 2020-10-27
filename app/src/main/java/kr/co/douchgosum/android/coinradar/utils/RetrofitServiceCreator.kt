package kr.co.douchgosum.android.coinradar.utils

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import org.koin.java.KoinJavaComponent.get
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Retrofit Service 를 만들 때 사용
 * Moshi Converter 와 OkHttpClient 는 Koin 을 이용하여 주입
 *
 * */
inline fun <reified T> createRetrofitService(baseUrl: String): T = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(
        get(Moshi::class.java)
    ))
    .client(
        get(OkHttpClient::class.java)
    )
    .baseUrl(baseUrl)
    .build()
    .create(T::class.java)
