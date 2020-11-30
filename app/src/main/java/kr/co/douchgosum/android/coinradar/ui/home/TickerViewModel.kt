package kr.co.douchgosum.android.coinradar.ui.home

import androidx.lifecycle.*
import kotlinx.coroutines.*
import kr.co.douchgosum.android.coinradar.data.db.Ticker
import kr.co.douchgosum.android.coinradar.data.db.TickerWithSymbolAndThumbnail
import kr.co.douchgosum.android.coinradar.data.repository.*
import java.lang.Exception

class TickerViewModel(
    private val repository: BithumbRepository
): ViewModel(), LifecycleEventObserver {
    private var shouldUpdate = false
    private val _tickers = MutableLiveData<List<TickerWithSymbolAndThumbnail>>()
    val tickers: LiveData<List<TickerWithSymbolAndThumbnail>>
        get() = _tickers

    private fun startUpdate() = viewModelScope.launch {
        shouldUpdate = true
        withContext(Dispatchers.IO) {
            while (shouldUpdate) {
                try {
                    val data = repository.getAllTickers()
                    synchronized(_tickers) {
                        _tickers.postValue(data)
                    }
                    println("MyTest ${repository.getAllTickerWithSymbols()}")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                delay(3000)
            }
        }
    }

    private fun stopUpdate() {
        shouldUpdate = false
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when(event) {
            Lifecycle.Event.ON_START -> {
                startUpdate()
                println("뷰모델 onStart")
            }
            Lifecycle.Event.ON_STOP -> {
                stopUpdate()
                println("뷰모델 onStop")
            }
            else -> {}
        }
    }

    class TickerViewModelFactory(
        private val repository: BithumbRepository
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return TickerViewModel(repository) as T
        }
    }
}

