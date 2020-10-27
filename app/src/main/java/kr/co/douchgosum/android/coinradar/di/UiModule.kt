package kr.co.douchgosum.android.coinradar.di

import kr.co.douchgosum.android.coinradar.data.repository.BithumbRepository
import kr.co.douchgosum.android.coinradar.data.repository.ExchangeRepository
import kr.co.douchgosum.android.coinradar.ui.home.ExchangeListViewModel
import kr.co.douchgosum.android.coinradar.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * View and ViewModel Injection
 * 
 * */
val uiModule = module {

    viewModel<HomeViewModel> {
        HomeViewModel(get<BithumbRepository>())
    }

    viewModel<ExchangeListViewModel> {
        ExchangeListViewModel(get<ExchangeRepository>())
    }

}