package kr.co.douchgosum.android.coinradar.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import kr.co.douchgosum.android.coinradar.data.repository.UpbitRepository
import kr.co.douchgosum.android.coinradar.databinding.FragmentExchangeDetailBinding
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent.inject

class ExchangeDetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewBinding = FragmentExchangeDetailBinding.inflate(layoutInflater, container, false)


        return viewBinding.root
    }

}