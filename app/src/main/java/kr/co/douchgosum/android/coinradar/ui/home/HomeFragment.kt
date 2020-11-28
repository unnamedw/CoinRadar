package kr.co.douchgosum.android.coinradar.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.moshi.Moshi
import kr.co.douchgosum.android.coinradar.R
import kr.co.douchgosum.android.coinradar.adapter.HomeViewPagerAdtaper
import kr.co.douchgosum.android.coinradar.adapter.SUB1_INDEX
import kr.co.douchgosum.android.coinradar.adapter.SUB2_INDEX
import kr.co.douchgosum.android.coinradar.adapter.SUB3_INDEX
import kr.co.douchgosum.android.coinradar.databinding.FragmentHomeBinding
import kr.co.douchgosum.android.coinradar.ui.BaseFragment
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment() {
    val viewModel: HomeViewModel by inject()
    lateinit var tabLayout:TabLayout
    lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        tabLayout = binding.tabs
        viewPager = binding.homeViewPager
        viewPager.adapter =
            HomeViewPagerAdtaper(this)

        // 각 Tab 설정
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()

        return binding.root
    }


    private fun getTabTitle(position: Int): String? {
        return when (position) {
            SUB1_INDEX -> getString(R.string.fragment_home_tab1_title)
            SUB2_INDEX -> getString(R.string.fragment_home_tab2_title)
            SUB3_INDEX -> getString(R.string.fragment_home_tab3_title)
            else -> null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        println("1파괴")
    }


}