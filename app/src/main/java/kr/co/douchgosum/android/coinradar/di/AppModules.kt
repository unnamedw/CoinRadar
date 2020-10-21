package kr.co.douchgosum.android.coinradar.di

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kr.co.douchgosum.android.coinradar.data.api.*
import kr.co.douchgosum.android.coinradar.data.db.AppDatabase
import kr.co.douchgosum.android.coinradar.data.db.TickerDao
import kr.co.douchgosum.android.coinradar.data.repository.BithumbRepository
import kr.co.douchgosum.android.coinradar.ui.main1.HomeViewModel
import kr.co.douchgosum.android.coinradar.utils.createRetrofitService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Koin 의존성 주입
 *
 * */

private const val BITHUMB_API_URL = "https://api.bithumb.com/public/"
private const val COINONE_API_URL = "https://api.coinone.co.kr/"
private const val GOPAX_API_URL = "https://api.gopax.co.kr/"
private const val HUOBI_API_URL = "https://api-cloud.huobi.co.kr/"
private const val UPBIT_API_URL = "https://api.upbit.com/v1/"

/**
 * Retrofit API Service Injection
 * */
val apiModule = module {
    single<Moshi> {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single<OkHttpClient> {
        OkHttpClient().newBuilder()
            .connectTimeout(1000, TimeUnit.SECONDS)
            .readTimeout(1000, TimeUnit.SECONDS)
            .writeTimeout(1000, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    single<BithumbApiService> { createRetrofitService(BITHUMB_API_URL) }
    single<CoinoneApiService> { createRetrofitService(COINONE_API_URL) }
    single<GopaxApiService> { createRetrofitService(GOPAX_API_URL) }
    single<HuobiApiService> { createRetrofitService(HUOBI_API_URL) }
    single<UpbitApiService> { createRetrofitService(UPBIT_API_URL) }
}


/**
 * Repository, LocalDB Injection
 * */
val modelModule = module {
    single<AppDatabase> { AppDatabase.getDatabase(get()) }
    single<TickerDao> { get<AppDatabase>().tickerDao() }
    single<BithumbRepository> { BithumbRepository(get<Context>(), get<BithumbApiService>(), get<TickerDao>()) }
}


/**
 * View and ViewModel Injection
 * */
val uiModule = module {
    viewModel<HomeViewModel> { HomeViewModel(get<BithumbRepository>()) }
}

