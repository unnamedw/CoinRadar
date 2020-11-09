package kr.co.douchgosum.android.coinradar.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import kr.co.douchgosum.android.coinradar.adapter.TickerListAdapter
import kr.co.douchgosum.android.coinradar.databinding.FragmentTickerBinding
import kr.co.douchgosum.android.coinradar.ui.BaseFragment
import kr.co.douchgosum.android.coinradar.utils.RecyclerSectionItemDecoration
import kotlin.math.log

class TickerFragment : BaseFragment() {
    lateinit var binding: FragmentTickerBinding
    private val viewModel: HomeViewModel by lazy { (parentFragment as HomeFragment).viewModel }
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: TickerListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTickerBinding.inflate(inflater, container, false)
        adapter = TickerListAdapter()
        recyclerView =  binding.rvTickerList
        recyclerView.adapter = adapter
        val sectionItemDecoration = RecyclerSectionItemDecoration(getSectionCallback())
        recyclerView.addItemDecoration(sectionItemDecoration)

        // 아이템 갱신 시 깜빡임 방지
        (recyclerView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false

        subscribeUi(adapter)

        return binding.root
    }

    private fun subscribeUi(adapter: TickerListAdapter) {
        viewModel.tickers.observe(viewLifecycleOwner) { tickers ->
            adapter.submitList(tickers)
        }
    }

    private fun getSectionCallback(): RecyclerSectionItemDecoration.SectionCallback {
        return object : RecyclerSectionItemDecoration.SectionCallback {
            override fun isSection(position: Int): Boolean {
                return adapter.isHolder(position)
            }

            override fun getHeaderLayoutView(list: RecyclerView, position: Int): View? {
                return adapter.getHeaderLayoutView(list, position)
            }
        }
    }

}