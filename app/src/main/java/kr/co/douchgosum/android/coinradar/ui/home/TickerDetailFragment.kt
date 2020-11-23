package kr.co.douchgosum.android.coinradar.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import kr.co.douchgosum.android.coinradar.R
import kr.co.douchgosum.android.coinradar.databinding.FragmentTickerDetailBinding
import org.koin.android.ext.android.bind

class TickerDetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTickerDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


}