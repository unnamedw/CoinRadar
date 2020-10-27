package kr.co.douchgosum.android.coinradar.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import kr.co.douchgosum.android.coinradar.ui.home.TickerFragment
import kr.co.douchgosum.android.coinradar.ui.home.ExchangeListFragment
import kr.co.douchgosum.android.coinradar.ui.home.MarketListFragment

const val SUB1_INDEX = 0
const val SUB2_INDEX = 1
const val SUB3_INDEX = 2

class HomeViewPagerAdtaper(
    fragment: Fragment
): FragmentStateAdapter(fragment) {

    private val fragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        SUB1_INDEX to { TickerFragment() },
        SUB2_INDEX to { ExchangeListFragment() },
        SUB3_INDEX to { MarketListFragment() }
    )

    override fun getItemCount() = fragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return fragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}