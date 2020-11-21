package kr.co.douchgosum.android.coinradar.di

import kr.co.douchgosum.android.coinradar.data.db.AppDatabase
import kr.co.douchgosum.android.coinradar.data.repository.BithumbRepository
import kr.co.douchgosum.android.coinradar.data.repository.CoinGeckoRepository
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

    /**
     * Repositories
     * */
    single { BithumbRepository(get(), get(), get()) }
    single { CoinGeckoRepository(get(), get(), get()) }

}