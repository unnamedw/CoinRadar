package kr.co.douchgosum.android.coinradar.deprecated

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
//
//private const val BITHUMB_API_URL = "https://api.bithumb.com/public/"
//
//private val moshi = Moshi.Builder()
//    .add(KotlinJsonAdapterFactory())
//    .build()
//
//private val okHttpClient = OkHttpClient().newBuilder()
//    .connectTimeout(1000, TimeUnit.SECONDS)
//    .readTimeout(1000, TimeUnit.SECONDS)
//    .writeTimeout(1000, TimeUnit.SECONDS)
////    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//    .build()
//
//private val retrofit = Retrofit.Builder()
//    .baseUrl(BASE_URL)
//    .client(okHttpClient)
//    .addConverterFactory(MoshiConverterFactory.create(moshi))
//    .build()
//
//object BithumbApi {
//    val service: BithumbApiService by lazy {
//        retrofit.create(BithumbApiService::class.java)
//    }
//}
