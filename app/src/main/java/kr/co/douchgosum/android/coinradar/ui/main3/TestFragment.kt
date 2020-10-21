package kr.co.douchgosum.android.coinradar.ui.main3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.douchgosum.android.coinradar.R
import kr.co.douchgosum.android.coinradar.ui.BaseFragment

class TestFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_test, container, false)
    }

}