package kr.co.douchgosum.android.coinradar.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.douchgosum.android.coinradar.R
import kr.co.douchgosum.android.coinradar.databinding.FragmentExchangeListBinding
import kr.co.douchgosum.android.coinradar.ui.BaseFragment

class ExchangeListFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentExchangeListBinding.inflate(inflater, container, false)
        return binding.root
    }


}