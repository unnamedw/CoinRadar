package kr.co.douchgosum.android.coinradar.adapter

import android.view.View
import android.widget.*
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
    binding: ItemTickerTopBinding,
    private val topItemListener: TickerListAdapter.OnTopItemListener?
): RecyclerView.ViewHolder(binding.root) {
    init {
        binding.apply {
            setUpExchangeSpinner(spinExchange)
            setUpCurrencySpinner(spinCurrency)
            setUpSearch(etSearch)
            setUpFavorite(ivFavorite)
        }
    }

    fun bind(item: Ticker) {}

    private fun setUpExchangeSpinner(spin: Spinner) {
        ArrayAdapter.createFromResource(
            spin.context,
            R.array.exchanges_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spin.adapter = adapter
        }

        spin.also { spinner ->
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {}

                override fun onItemSelected(adapter: AdapterView<*>?, v: View?, position: Int, id: Long) {
                    topItemListener?.let { listener ->
                        listener.onExchangeChanged(adapter?.getItemAtPosition(position) as String)

                    }
                }
            }
        }
    }

    private fun setUpCurrencySpinner(spin: Spinner) {
        val adapter = ArrayAdapter.createFromResource(
            spin.context,
            R.array.bithumb_currencies_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spin.adapter = adapter
        }

    }

    private fun setUpSearch(et: EditText) {

    }

    private fun setUpFavorite(fav: ImageView) {
        fav.setOnClickListener { view ->
            val iv = view as ImageView
            if (iv.tag == true) {
                iv.setImageResource(R.drawable.ic_baseline_star_border_24)
                iv.tag = false
            }
            else {
                iv.setImageResource(R.drawable.ic_baseline_star_24)
                iv.tag = true
            }
            topItemListener?.onFavoriteClicked(iv.tag as Boolean)
        }
    }
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