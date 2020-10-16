package kr.co.douchgosum.android.coinradar

import android.util.Log
import androidx.lifecycle.MutableLiveData
import org.junit.Test

class LiveDataUnitTest {
    @Test
    fun main() {
        val _liveData = MutableLiveData<List<String>>(listOf("미진", "재현"))
        val data = listOf("철수", "영희")
        val newList = _liveData.value!!.toMutableList()
        newList.addAll(data)
        _liveData.value = newList

        Log.d("MyTag", _liveData.value.toString())
    }
}