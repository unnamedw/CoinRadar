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
import kr.co.douchgosum.android.coinradar.databinding.ItemTickerBinding
import kr.co.douchgosum.android.coinradar.databinding.ItemTickerEmptyBinding
import kr.co.douchgosum.android.coinradar.databinding.ItemTickerHolderBinding
import kr.co.douchgosum.android.coinradar.databinding.ItemTickerTopBinding

class TickerListAdapter: ListAdapter<Ticker, RecyclerView.ViewHolder>(TickerDiffCallback()) {
    /**
     * View Type
     * */
    companion object {
        const val TYPE_TOP = 0
        const val TYPE_HOLDER = 1
        const val TYPE_EMPTY = 2
        const val TYPE_LIST = 3
    }

    lateinit var favoriteClickListener: OnFavoriteClickListener

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> TYPE_TOP
            1 -> TYPE_HOLDER
            2 -> TYPE_EMPTY
            else -> TYPE_LIST }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            TYPE_TOP -> TickerTopViewHolder(
                ItemTickerTopBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                favoriteClickListener)
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
            TYPE_TOP -> (holder as TickerTopViewHolder).bind(ticker)
            TYPE_HOLDER -> (holder as TickerHolderViewHolder).bind(ticker)
            TYPE_EMPTY -> (holder as TickerEmptyViewHolder).bind(ticker)
            else -> (holder as TickerViewHolder).bind(ticker)
        }
    }

    /**
     * 받아 온 Item List 에
     * Top, Holder, Empty 포지션을 위한 3개의 더미 아이템을 삽입한다.
     * */
    override fun submitList(list: List<Ticker>?) {
        val newList = mutableListOf<Ticker>()
        list?.let {
            if (list.isEmpty())
                return
            val dummyList = List(3) { list[0] }
            newList.addAll(dummyList)
            newList.addAll(it)
        }
        super.submitList(newList)
    }

    private var holderView: View? = null
    fun getHolderViewOrNull(list: RecyclerView, position: Int): View? {
//        val lastIndex = if (position < itemCount) position else itemCount-1
//        for (index in lastIndex downTo 0) {
//            if (index == TYPE_HOLDER) {
//                return LayoutInflater.from(list.context).inflate(R.layout.item_ticker_holder, list, false)
//            }
//        }
//        return null
        if (position >= TYPE_HOLDER) {
            if (holderView == null) {
                holderView = LayoutInflater.from(list.context).inflate(R.layout.item_ticker_holder, list, false)
            }
        }
        else {
            holderView = null
        }
        return holderView
    }

    fun isHolder(position: Int): Boolean {
        return position == TYPE_HOLDER
    }

    interface OnFavoriteClickListener {
        fun onFavoriteClicked(isFavorite: Boolean)
    }

}

class TickerDiffCallback: DiffUtil.ItemCallback<Ticker>() {
    override fun areItemsTheSame(oldItem: Ticker, newItem: Ticker): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Ticker, newItem: Ticker): Boolean {
        return oldItem == newItem
    }
}









