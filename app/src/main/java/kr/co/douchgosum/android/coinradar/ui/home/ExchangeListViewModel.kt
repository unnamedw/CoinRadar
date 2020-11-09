package kr.co.douchgosum.android.coinradar.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.co.douchgosum.android.coinradar.data.remote.exchange.CoinGeckoExchange
import kr.co.douchgosum.android.coinradar.data.repository.ExchangeRepository

class ExchangeListViewModel(
    private val exchangeRepository: ExchangeRepository
): ViewModel() {
    private val _exchanges = exchangeRepository.getAllExchanges()
    val exchanges: LiveData<List<CoinGeckoExchange>>
        get() = _exchanges

    fun refreshExchanges() = viewModelScope.launch {
        exchangeRepository.update()
    }
}