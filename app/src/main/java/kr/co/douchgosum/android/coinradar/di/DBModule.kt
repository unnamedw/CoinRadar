package kr.co.douchgosum.android.coinradar.di

import android.content.Context
import kr.co.douchgosum.android.coinradar.data.remote.ticker.BithumbTickerApiService
import kr.co.douchgosum.android.coinradar.data.remote.exchange.CoinGeckoExchangeService
import kr.co.douchgosum.android.coinradar.data.db.AppDatabase
import kr.co.douchgosum.android.coinradar.data.db.ExchangeDao
import kr.co.douchgosum.android.coinradar.data.db.TickerDao
import kr.co.douchgosum.android.coinradar.data.repository.BithumbRepository
import kr.co.douchgosum.android.coinradar.data.repository.ExchangeRepository
import org.koin.dsl.module

/**
 * Repository, LocalDB Injection
 *
 * */
val dbModule = module {
    single<AppDatabase> { AppDatabase.getDatabase(get<Context>()) }
    single<TickerDao> { get<AppDatabase>().tickerDao() }
    single<ExchangeDao> { get<AppDatabase>().exchangeDao() }
    single<BithumbRepository> { BithumbRepository(get<Context>(), get<BithumbTickerApiService>(), get<TickerDao>()) }
    single<ExchangeRepository> { ExchangeRepository(get<Context>(), get<ExchangeDao>(), get<CoinGeckoExchangeService>()) }
}