package kr.co.douchgosum.android.coinradar.ui.home

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kr.co.douchgosum.android.coinradar.data.db.TickerWithSymbolAndThumbnail
import kr.co.douchgosum.android.coinradar.data.repository.BithumbRepository

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
                    val data = repository.getAllTickerWithSymbolAndThumbnails()
                    synchronized(_tickers) {
                        _tickers.postValue(data)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                delay(3000)
            }
        }
    }

    private suspend fun getTickerDataAsync() =
        withContext(viewModelScope.coroutineContext) {
            repository.getAllTickerWithSymbolAndThumbnails()
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

