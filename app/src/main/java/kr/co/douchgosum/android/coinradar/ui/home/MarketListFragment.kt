package kr.co.douchgosum.android.coinradar.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.douchgosum.android.coinradar.R
import kr.co.douchgosum.android.coinradar.ui.BaseFragment


class MarketListFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_total_value, container, false)
    }


}