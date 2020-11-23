package kr.co.douchgosum.android.coinradar.adapter

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kr.co.douchgosum.android.coinradar.R
import kr.co.douchgosum.android.coinradar.data.db.Ticker
import kr.co.douchgosum.android.coinradar.databinding.ItemTickerBinding
import kr.co.douchgosum.android.coinradar.databinding.ItemTickerEmptyBinding
import kr.co.douchgosum.android.coinradar.databinding.ItemTickerHolderBinding
import kr.co.douchgosum.android.coinradar.databinding.ItemTickerTopBinding

/**
 * View Holders
 * */
class TickerViewHolder(
    private val binding: ItemTickerBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Ticker) {
        binding.apply {
            ticker = item
            executePendingBindings()
        }
    }
}

class TickerTopViewHolder(
    private val binding: ItemTickerTopBinding,
    private val favoriteClickListener: TickerListAdapter.OnFavoriteClickListener?
): RecyclerView.ViewHolder(binding.root) {
    init {
        binding.ivFavorite.setOnClickListener { view ->
            val iv = view as ImageView
            if (iv.tag == true) {
                iv.setImageResource(R.drawable.ic_baseline_star_border_24)
                iv.tag = false
            }
            else {
                iv.setImageResource(R.drawable.ic_baseline_star_24)
                iv.tag = true
            }
            favoriteClickListener?.onFavoriteClicked(iv.tag as Boolean)
        }
    }
    fun bind(item: Ticker) {}
}

class TickerHolderViewHolder(
    private val binding: ItemTickerHolderBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Ticker) {}
}

class TickerEmptyViewHolder(
    private val binding: ItemTickerEmptyBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Ticker) {}
}