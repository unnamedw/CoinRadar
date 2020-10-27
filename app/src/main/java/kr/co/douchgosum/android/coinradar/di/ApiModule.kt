package kr.co.douchgosum.android.coinradar.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kr.co.douchgosum.android.coinradar.data.remote.exchange.CoinGeckoExchangeService
import kr.co.douchgosum.android.coinradar.data.remote.ticker.*
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
val apiModule = module {
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
//            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    single {  }

    //Ticker
    single<BithumbTickerApiService> { createRetrofitService(BITHUMB_API_URL) }
    single<CoinoneTickerApiService> { createRetrofitService(COINONE_API_URL) }
    single<GopaxTickerApiService> { createRetrofitService(GOPAX_API_URL) }
    single<HuobiTickerApiService> { createRetrofitService(HUOBI_API_URL) }
    single<UpbitTickerApiService> { createRetrofitService(UPBIT_API_URL) }

    //Exchange
    single<CoinGeckoExchangeService> { createRetrofitService(COINGECKO_API_URL) }

}







