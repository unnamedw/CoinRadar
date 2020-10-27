package kr.co.douchgosum.android.coinradar.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import kr.co.douchgosum.android.coinradar.data.remote.entity.CoinGeckoExchange
import kr.co.douchgosum.android.coinradar.data.repository.ExchangeRepository

class ExchangeListViewModel(
    private val exchangeRepository: ExchangeRepository
): ViewModel() {
    private val _exchanges = MutableLiveData<List<CoinGeckoExchange>>()
    val exchanges: LiveData<List<CoinGeckoExchange>>
        get() = _exchanges

    fun refreshExchanges() = viewModelScope.launch {
        exchangeRepository.getAllExchanges()
            .onCompletion {

            }
            .collect { exchange ->

            }
    }
}