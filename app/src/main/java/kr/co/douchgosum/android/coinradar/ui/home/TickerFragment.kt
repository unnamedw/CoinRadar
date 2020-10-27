package kr.co.douchgosum.android.coinradar.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import kr.co.douchgosum.android.coinradar.databinding.FragmentTickerBinding
import kr.co.douchgosum.android.coinradar.ui.BaseFragment

class TickerFragment : BaseFragment() {
    lateinit var binding: FragmentTickerBinding
    lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTickerBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = (parentFragment as HomeFragment).viewModel
        viewModel.tickers.observe(viewLifecycleOwner, Observer {
            println("관찰")
            binding.textView.text = it.toString()
        })
    }
    override fun onDestroy() {
        super.onDestroy()
        println("서브1파괴")
    }


}