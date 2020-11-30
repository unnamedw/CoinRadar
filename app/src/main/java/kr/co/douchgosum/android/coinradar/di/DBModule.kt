package kr.co.douchgosum.android.coinradar.di

import kr.co.douchgosum.android.coinradar.data.db.AppDatabase
import kr.co.douchgosum.android.coinradar.data.repository.*
import org.koin.dsl.module

/**
 * Repository, LocalDB Injection
 *
 * */
val dbModule = module {

    /**
     * Database
     * */
    single { AppDatabase.getDatabase(get()) }

    /**
     * DAOs
     * */
    single { get<AppDatabase>().tickerDao() }
    single { get<AppDatabase>().exchangeDao() }
    single { get<AppDatabase>().tickerSymbolDao() }
    single { get<AppDatabase>().tickerThumnailDao() }

    /**
     * Repositories
     * */
    single { BithumbRepository(get()) }
    single { CoinGeckoRepository(get()) }
    single { CoinoneRepository(get()) }
    single { GopaxRepository(get()) }
    single { HuobiRepository(get()) }
    single { UpbitRepository(get()) }

}