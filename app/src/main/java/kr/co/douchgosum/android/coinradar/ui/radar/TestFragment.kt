package kr.co.douchgosum.android.coinradar.ui.radar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.douchgosum.android.coinradar.R
import kr.co.douchgosum.android.coinradar.ui.BaseFragment

class RadarFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_radar, container, false)
    }

}