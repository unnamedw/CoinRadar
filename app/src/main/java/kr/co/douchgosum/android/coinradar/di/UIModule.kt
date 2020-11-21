package kr.co.douchgosum.android.coinradar.di

import kr.co.douchgosum.android.coinradar.ui.home.ExchangeListViewModel
import kr.co.douchgosum.android.coinradar.ui.home.HomeViewModel
import kr.co.douchgosum.android.coinradar.ui.home.TickerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * View and ViewModel Injection
 * 
 * */
val viewModelModule = module {
    viewModel {
        HomeViewModel(get())
    }

    viewModel {
        ExchangeListViewModel(get())
    }

    viewModel {
        TickerViewModel(get())
    }

}