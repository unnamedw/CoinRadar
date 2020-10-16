package kr.co.douchgosum.android.coinradar.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_home.*
import kr.co.douchgosum.android.coinradar.MainActivity
import kr.co.douchgosum.android.coinradar.R
import kr.co.douchgosum.android.coinradar.ui.BaseFragment

class HomeFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button.setOnClickListener {
//            navigateToTest(it, "메롱")
        }

    }

    private fun navigateToTest(view: View, id: String) {
        val directions =
            HomeFragmentDirections.actionHomeToTestFragment(
                id
            )
        view.findNavController().navigate(directions)
    }





}