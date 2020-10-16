package kr.co.douchgosum.android.coinradar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*
import kr.co.douchgosum.android.coinradar.ui.BaseFragment

class TestFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).bottom_nav.visibility = View.GONE
        val args = arguments?.let {
            TestFragmentArgs.fromBundle(it)
        }
        args?.let {
            showToast(it.id)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as MainActivity).bottom_nav.visibility = View.VISIBLE
    }
}