package kr.co.douchgosum.android.coinradar.ui.home

import android.media.Image
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.item_ticker_top.*
import kotlinx.coroutines.channels.ticker
import kr.co.douchgosum.android.coinradar.adapter.TickerListAdapter
import kr.co.douchgosum.android.coinradar.databinding.FragmentTickerBinding
import kr.co.douchgosum.android.coinradar.ui.BaseFragment
import kr.co.douchgosum.android.coinradar.utils.RecyclerSectionItemDecoration
import org.koin.android.ext.android.inject
import kotlin.math.log

class TickerFragment : BaseFragment(), TickerListAdapter.OnFavoriteClickListener {
    private val viewModel: TickerViewModel by inject()
    private lateinit var binding: FragmentTickerBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var rvAdapter: TickerListAdapter
    private lateinit var fabToTop: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTickerBinding.inflate(inflater, container, false).apply {
            //init
            rvAdapter = TickerListAdapter()
            recyclerView = rvTickerList
            fabToTop = fabNavTop
        }

        setUpRecyclerView(recyclerView)
        subscribeUi(rvAdapter)

        fabToTop.setOnClickListener {
            if (rvAdapter.itemCount>0) {
                recyclerView.scrollToPosition(0)
            }
        }

        return binding.root
    }

    private fun subscribeUi(adapter: TickerListAdapter) {
        viewModel.tickers.observe(viewLifecycleOwner) { tickers ->
            adapter.submitList(tickers)
            println("데이터 $tickers")
        }
    }

    private fun setUpRecyclerView(rv: RecyclerView) {
        val sectionItemDecoration = RecyclerSectionItemDecoration(getSectionCallback())
        rv.adapter = rvAdapter
        rvAdapter.favoriteClickListener = this
        rv.addItemDecoration(sectionItemDecoration)

        // 아이템 갱신 시 깜빡임 방지
        (rv.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
    }

    private fun getSectionCallback(): RecyclerSectionItemDecoration.SectionCallback {
        return object : RecyclerSectionItemDecoration.SectionCallback {
            override fun isSection(position: Int): Boolean {
                return rvAdapter.isHolder(position)
            }

            override fun getHolderLayoutView(list: RecyclerView, position: Int): View? {
                return rvAdapter.getHolderViewOrNull(list, position)
            }
        }
    }

    override fun onFavoriteClicked(isFavorite: Boolean) {
        println("테스트 $isFavorite")
    }

}