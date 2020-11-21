package kr.co.douchgosum.android.coinradar.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kr.co.douchgosum.android.coinradar.data.remote.bithumb.BithumbApiService
import kr.co.douchgosum.android.coinradar.data.remote.coingecko.CoinGeckoApiService
import kr.co.douchgosum.android.coinradar.data.remote.coinone.CoinoneApiService
import kr.co.douchgosum.android.coinradar.data.remote.gopax.GopaxApiService
import kr.co.douchgosum.android.coinradar.data.remote.huobi.HuobiApiService
import kr.co.douchgosum.android.coinradar.data.remote.upbit.UpbitApiService
import kr.co.douchgosum.android.coinradar.utils.createRetrofitService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

private const val BITHUMB_API_URL = "https://api.bithumb.com/public/"
private const val COINONE_API_URL = "https://api.coinone.co.kr/"
private const val GOPAX_API_URL = "https://api.gopax.co.kr/"
private const val HUOBI_API_URL = "https://api-cloud.huobi.co.kr/"
private const val UPBIT_API_URL = "https://api.upbit.com/v1/"
private const val COINGECKO_API_URL = "https://api.coingecko.com/api/v3/"

/**
 * Retrofit API Service Injection
 *
 * */
val retrofitModule = module {
    single<Moshi> {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single<OkHttpClient> {
        OkHttpClient().newBuilder()
            .connectTimeout(3000, TimeUnit.SECONDS)
            .readTimeout(3000, TimeUnit.SECONDS)
            .writeTimeout(3000, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
            .build()
    }
}

val apiModule = module {
    /**
     * API SERVICE
     *
     * */
    //Ticker
    single<BithumbApiService> {
        createRetrofitService(BITHUMB_API_URL)
    }
    single<CoinoneApiService> {
        createRetrofitService(COINONE_API_URL)
    }
    single<GopaxApiService> {
        createRetrofitService(GOPAX_API_URL)
    }
    single<HuobiApiService> {
        createRetrofitService(HUOBI_API_URL)
    }
    single<UpbitApiService> {
        createRetrofitService(UPBIT_API_URL)
    }

    //CoinGecko
    single<CoinGeckoApiService> {
        createRetrofitService(COINGECKO_API_URL)
    }
}








