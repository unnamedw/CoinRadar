package kr.co.douchgosum.android.coinradar.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.channels.ticker
import kr.co.douchgosum.android.coinradar.R
import kr.co.douchgosum.android.coinradar.data.db.Ticker
import kr.co.douchgosum.android.coinradar.data.db.TickerWithSymbolAndThumbnail
import kr.co.douchgosum.android.coinradar.databinding.ItemTickerBinding
import kr.co.douchgosum.android.coinradar.databinding.ItemTickerEmptyBinding
import kr.co.douchgosum.android.coinradar.databinding.ItemTickerHolderBinding
import kr.co.douchgosum.android.coinradar.databinding.ItemTickerTopBinding

class TickerListAdapter: ListAdapter<TickerWithSymbolAndThumbnail, RecyclerView.ViewHolder>(TickerDiffCallback()) {
    /**
     * View Type
     * */
    companion object {
        const val POSITION_TOP = 0
        const val POSITION_HOLDER = 1
        const val POSITION_EMPTY = 2

        const val TYPE_TOP = 100
        const val TYPE_HOLDER = 101
        const val TYPE_EMPTY = 102
        const val TYPE_LIST = 103
    }

    private lateinit var holderView: View

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            POSITION_TOP -> TYPE_TOP
            POSITION_HOLDER -> TYPE_HOLDER
            POSITION_EMPTY -> TYPE_EMPTY
            else -> TYPE_LIST
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            TYPE_TOP -> TickerTopViewHolder(
                ItemTickerTopBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            TYPE_HOLDER -> TickerHolderViewHolder(
                ItemTickerHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            TYPE_EMPTY -> TickerEmptyViewHolder(
                ItemTickerEmptyBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> TickerViewHolder(
                ItemTickerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val ticker = getItem(position)
        when(position) {
            POSITION_TOP -> (holder as TickerTopViewHolder).bind(ticker)
            POSITION_HOLDER -> (holder as TickerHolderViewHolder).bind(ticker)
            POSITION_EMPTY -> (holder as TickerEmptyViewHolder).bind(ticker)
            else -> (holder as TickerViewHolder).bind(ticker)
        }
    }

    /**
     * 받아 온 Item List 에
     * Top, Holder, Empty 포지션을 위한 3개의 더미 아이템을 삽입한다.
     * */
    override fun submitList(list: List<TickerWithSymbolAndThumbnail>?) {
        val newList = mutableListOf<TickerWithSymbolAndThumbnail>()
        list?.let {
            if (list.isEmpty())
                return
            val dummyList = List(3) { list[0] }
            newList.addAll(dummyList)
            newList.addAll(it)
        }
        super.submitList(newList)
    }

    fun getHolderViewOrNull(rv: RecyclerView, position: Int): View? {
        if (position >= POSITION_HOLDER) {
            if (!::holderView.isInitialized) {
                holderView = LayoutInflater.from(rv.context).inflate(R.layout.item_ticker_holder, rv, false)
            }
            return holderView
        }
        return null
    }

    fun isHolder(position: Int): Boolean {
        return position == POSITION_HOLDER
    }
}

class TickerDiffCallback: DiffUtil.ItemCallback<TickerWithSymbolAndThumbnail>() {
    override fun areItemsTheSame(oldItem: TickerWithSymbolAndThumbnail, newItem: TickerWithSymbolAndThumbnail): Boolean {
        return oldItem.ticker.baseSymbol == newItem.ticker.baseSymbol &&
                oldItem.ticker.quoteSymbol == newItem.ticker.quoteSymbol &&
                oldItem.ticker.exchange == newItem.ticker.exchange
    }

    override fun areContentsTheSame(oldItem: TickerWithSymbolAndThumbnail, newItem: TickerWithSymbolAndThumbnail): Boolean {
        return oldItem == newItem
    }
}









