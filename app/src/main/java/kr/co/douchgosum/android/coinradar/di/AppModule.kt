package kr.co.douchgosum.android.coinradar.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kr.co.douchgosum.android.coinradar.data.api.BithumbApi
import kr.co.douchgosum.android.coinradar.data.api.UpbitApiService
import kr.co.douchgosum.android.coinradar.data.entity.BithumbTickerResponse
import kr.co.douchgosum.android.coinradar.data.repository.BithumbDataRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

val baseUrl = ""
val apiModule = module {
    factory {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
    single {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .baseUrl(baseUrl)
            .build()
    }
}
val appModule = module {
    single {

    }
    single {

    }
}