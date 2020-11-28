package kr.co.douchgosum.android.coinradar.ui.home

import androidx.lifecycle.*
import kotlinx.coroutines.*
import kr.co.douchgosum.android.coinradar.data.db.Ticker
import kr.co.douchgosum.android.coinradar.data.repository.BithumbRepository
import kr.co.douchgosum.android.coinradar.data.repository.CoinGeckoRepository
import kr.co.douchgosum.android.coinradar.data.repository.Repository
import java.lang.Exception

class HomeViewModel(
    private val repository: BithumbRepository
): ViewModel(), LifecycleEventObserver {

    private var isUpdating = false

    private val _tickers = MutableLiveData<List<Ticker>>()
    val tickers: LiveData<List<Ticker>>
        get() = _tickers

    private fun startUpdate() = viewModelScope.launch {
        isUpdating = true
        while (isUpdating) {
            try {
                _tickers.value = repository.getAllTickers()
//                _tickers.value = repository.getAllTickers("").toList()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            delay(3000)
        }
    }

    private fun stopUpdate() {
        isUpdating = false
    }

    /**
     * Fragment 생명주기에 따른 이벤트 설정
     * */
    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
//        when(event) {
//            Lifecycle.Event.ON_START -> { startUpdate(); println("뷰모델 onStart") }
//            Lifecycle.Event.ON_STOP -> { stopUpdate(); println("뷰모델 onStop") }
//            else -> {}
//        }
    }

    class HomeViewModelFactory(
        private val repository: BithumbRepository
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return HomeViewModel(repository) as T
        }
    }
}

