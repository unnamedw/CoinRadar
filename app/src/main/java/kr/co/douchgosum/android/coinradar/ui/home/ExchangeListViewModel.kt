package kr.co.douchgosum.android.coinradar.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.co.douchgosum.android.coinradar.data.remote.coingecko.CoinGeckoExchange
import kr.co.douchgosum.android.coinradar.data.repository.CoinGeckoRepository

class ExchangeListViewModel(
    private val coinGeckoRepository: CoinGeckoRepository
): ViewModel() {
    private val _exchanges = MutableLiveData<List<CoinGeckoExchange>>().apply {
//        value = coinGeckoRepository.getAllExchanges()
    }
    val exchanges: LiveData<List<CoinGeckoExchange>>
        get() = _exchanges

    fun refreshExchanges() = viewModelScope.launch {
//        coinGeckoRepository.update()
    }
}