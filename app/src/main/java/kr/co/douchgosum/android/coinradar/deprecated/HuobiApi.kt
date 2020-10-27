package kr.co.douchgosum.android.coinradar.deprecated

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

//private const val BASE_URL = "https://api-cloud.huobi.co.kr/"
//
//private val moshi = Moshi.Builder()
//    .add(KotlinJsonAdapterFactory())
//    .build()
//
//private val okHttpClient = OkHttpClient().newBuilder()
//    .connectTimeout(1000, TimeUnit.SECONDS)
//    .readTimeout(1000, TimeUnit.SECONDS)
//    .writeTimeout(1000, TimeUnit.SECONDS)
//    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//    .build()
//
//private val retrofit = Retrofit.Builder()
//    .baseUrl(BASE_URL)
//    .client(okHttpClient)
//    .addConverterFactory(MoshiConverterFactory.create(moshi))
//    .build()
//
//object HuobiApi {
//    val service : HuobiApiService by lazy {
//        retrofit.create(HuobiApiService::class.java)
//    }
//}