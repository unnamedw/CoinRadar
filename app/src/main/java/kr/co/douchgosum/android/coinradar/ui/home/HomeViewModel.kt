package kr.co.douchgosum.android.coinradar.ui.home

import androidx.lifecycle.*
import kotlinx.coroutines.*
import kr.co.douchgosum.android.coinradar.data.repository.BithumbRepository
import java.lang.Exception

class HomeViewModel(
    private val bithumbRepository: BithumbRepository
): ViewModel(), LifecycleEventObserver {

    val tickers = bithumbRepository.getAll()
    private var isUpdating = false

    private fun startUpdate() = viewModelScope.launch {
        isUpdating = true
        while (isUpdating) {
            try {
                bithumbRepository.update()
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
        when(event) {
            Lifecycle.Event.ON_START -> { startUpdate(); println("뷰모델 onStart") }
            Lifecycle.Event.ON_STOP -> { stopUpdate(); println("뷰모델 onStop") }
            else -> {}
        }
    }

    class HomeViewModelFactory(
        private val bithumbRepository: BithumbRepository
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return HomeViewModel(bithumbRepository) as T
        }
    }

}